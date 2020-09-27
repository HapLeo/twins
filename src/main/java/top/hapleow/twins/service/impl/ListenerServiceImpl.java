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

    private volatile CuratorCache curatorCache;

    @Override
    public void nodeListenerStart(String path) {
        if (curatorCache == null){
            synchronized (this){
                if (curatorCache == null){
                    curatorCache = CuratorCache.builder(client, path).build();
                    CuratorCacheListener listener = CuratorCacheListener.builder().forNodeCache(new NodeCacheListener() {
                        @Override
                        public void nodeChanged() throws Exception {
                            System.out.println("nodeListener 监听到节点 " + path + "已变化。");
                        }
                    }).build();
                    curatorCache.listenable().addListener(listener);
                    curatorCache.start();
                }
            }
        }

    }

    @Override
    public void nodeListenerClose(String path) {
        if (curatorCache == null){
            return;
        }
        curatorCache.close();
        curatorCache = null;
    }
}