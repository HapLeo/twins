package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
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
    public void cacheListenerStart(String path) {

        String key = path + "_cache";

        CuratorCache curatorCache = initCuratorCache(path);
        CuratorCacheListener cacheListener = cacheListenerMap.get(key);
        if (cacheListener == null) {
            synchronized (this) {
                if (cacheListenerMap.get(key) == null) {
                    cacheListener = CuratorCacheListener.builder()
                            .forCreates(node -> System.out.println(String.format("Node created: [%s]", node)))
                            .forChanges((oldNode, node) -> System.out.println(String.format("Node changed. Old: [%s] New: [%s]", oldNode, node)))
                            .forDeletes(oldNode -> System.out.println(String.format("Node deleted. Old value: [%s]", oldNode)))
                            .forInitialized(() -> System.out.println("Cache initialized"))
                            .build();
                    cacheListenerMap.put(key, cacheListener);
                }
            }
        }
        // register the listener
        curatorCache.listenable().addListener(cacheListenerMap.get(key));
        System.out.println("CacheListener -> path:" + path);
    }


    /**
     * 状态监听启动
     *
     */
    @Override
    public void stateListenerStart() {
        client.getConnectionStateListenable().addListener((curatorFramework, connectionState)
                -> System.out.println("stateListener 监听到状态变化：" + connectionState));
        System.out.println("stateListener -> 监听器开启");
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