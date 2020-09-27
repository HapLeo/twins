package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
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

    private volatile Map<String, CuratorCacheListener> nodeCacheListenerMap = new HashMap<>();

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
            }
        }
        return curatorCache;
    }

    @Override
    public void listenerStart(String path) {

        CuratorCache curatorCache = initCuratorCache(path);

        CuratorCacheListener nodeCacheListener = nodeCacheListenerMap.get(path);
        if (nodeCacheListener == null) {
            synchronized (this) {
                if (nodeCacheListenerMap.get(path) == null) {
                    nodeCacheListener = CuratorCacheListener.builder().forNodeCache(new NodeCacheListener() {
                        @Override
                        public void nodeChanged() throws Exception {
                            System.out.println("nodeListener 监听到节点 " + path + "已变化。");
                        }
                    }).build();
                    nodeCacheListenerMap.put(path, nodeCacheListener);
                }
            }
        }
        curatorCache.listenable().addListener(nodeCacheListenerMap.get(path));
    }

    @Override
    public void listenerRemove(String path) {

        CuratorCache nodeCuratorCache = curatorCacheMap.get(path);
        CuratorCacheListener nodeCacheListener = nodeCacheListenerMap.get(path);

        if (nodeCuratorCache == null || nodeCacheListener == null) {
            return;
        }
        nodeCuratorCache.listenable().removeListener(nodeCacheListener);
    }
}