package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hapleow.twins.service.IListenerService;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作节点的API
 */
@Service
@Slf4j
public class ListenerServiceImpl implements IListenerService {

    @Autowired
    private CuratorFramework client;

    private volatile Map<String, CuratorCache> curatorCacheMap = new HashMap<>();

    private volatile Map<String, CuratorCacheListener> cacheListenerMap = new HashMap<>();

    private CuratorCache initCuratorCache(String path) {

        CuratorCache curatorCache = curatorCacheMap.get(path);
        if (curatorCache == null) {
            synchronized (this) {
                if (curatorCacheMap.get(path) == null) {
                    curatorCache = CuratorCache.builder(client, path).build();
                    curatorCacheMap.put(path, curatorCache);
                }
                curatorCache = curatorCacheMap.get(path);
                curatorCache.start();
                System.out.println("Cache已启动，path:" + path);
            }
        }
        return curatorCache;
    }

    @Override
    public void nodeListenerStart(String path) {

        String key = path + "_node";

        CuratorCache curatorCache = initCuratorCache(path);

        CuratorCacheListener cacheListener = cacheListenerMap.get(key);
        if (cacheListener == null) {
            synchronized (this) {
                if (cacheListenerMap.get(key) == null) {
                    cacheListener = CuratorCacheListener.builder().forNodeCache(new NodeCacheListener() {
                        @Override
                        public void nodeChanged() throws Exception {
                            System.out.println("nodeCacheListener 监听到节点 " + path + "已变化。");
                        }
                    }).build();
                    cacheListenerMap.put(key, cacheListener);
                }
            }
        }
        curatorCache.listenable().addListener(cacheListenerMap.get(key));
        System.out.println("Node监听已开启，监听path:" + path);
    }


    /**
     * 路径监听启动
     *
     * @param path
     */
    @Override
    public void pathListenerStart(String path) {
        String key = path + "_path";
        CuratorCache curatorCache = initCuratorCache(path);

        CuratorCacheListener cacheListener = cacheListenerMap.get(key);
        if (cacheListener == null) {
            synchronized (this) {
                if (cacheListenerMap.get(key) == null) {
                    cacheListener = CuratorCacheListener.builder().forPathChildrenCache(path, client, new PathChildrenCacheListener() {
                        @Override
                        public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                            System.out.println("pathChildrenCacheListener 监听到节点" + path + "变化：类型=" + pathChildrenCacheEvent.getType() + "data=" + pathChildrenCacheEvent.getData());
                        }
                    }).build();
                }
                cacheListenerMap.put(key, cacheListener);
            }
        }
        curatorCache.listenable().addListener(cacheListenerMap.get(key));
        System.out.println("Path监听已开启，监听path:" + path);
    }

    @Override
    public void listenerRemove(String path, String type) {

        String key = path + "_" + type;

        CuratorCache nodeCuratorCache = curatorCacheMap.get(path);
        CuratorCacheListener nodeCacheListener = cacheListenerMap.get(key);

        if (nodeCuratorCache == null || nodeCacheListener == null) {
            return;
        }
        nodeCuratorCache.listenable().removeListener(nodeCacheListener);
        System.out.println(type + "监听已移除，path:" + path);
    }

}