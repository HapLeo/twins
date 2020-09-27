package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hapleow.twins.service.IListenerService;

/**
 * 操作节点的API
 */
@Service
@Slf4j
public class ListenerServiceImpl implements IListenerService {

    @Autowired
    private CuratorFramework client;

    @Override
    public void curatorCacheListener(String path) {

        CuratorCache cache = CuratorCache.build(client, path);
        CuratorCacheListener curatorCacheListener = CuratorCacheListener.builder().forNodeCache(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点修改");
            }
        }).build();
        cache.listenable().addListener(curatorCacheListener);
        cache.start();
    }
}