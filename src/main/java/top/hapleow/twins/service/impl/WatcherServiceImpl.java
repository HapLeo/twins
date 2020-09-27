package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hapleow.twins.service.IWatcherService;

/**
 * 操作节点的API
 */
@Service
@Slf4j
public class WatcherServiceImpl implements IWatcherService {

    @Autowired
    private CuratorFramework client;

    @Override
    public void checkExists(String path) {
        try {
            client.checkExists().usingWatcher((CuratorWatcher) watchedEvent ->
                    System.out.println("checkExists=>Path:"+ watchedEvent.getPath() +",state:"+ watchedEvent.getState() + ",type:"+watchedEvent.getType()))
                    .forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getDataWatch(String path) {
        try {
            client.getData().usingWatcher((CuratorWatcher) watchedEvent ->
                    System.out.println("getDataWatch=>Path:"+ watchedEvent.getPath() +",state:"+ watchedEvent.getState() + ",type:"+watchedEvent.getType()))
                    .forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getChildrenWatch(String path) {
        try {
            client.getChildren().usingWatcher((CuratorWatcher) watchedEvent ->
                    System.out.println("getChildrenWatch=>Path:"+ watchedEvent.getPath() +",state:"+ watchedEvent.getState() + ",type:"+watchedEvent.getType()))
                    .forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
