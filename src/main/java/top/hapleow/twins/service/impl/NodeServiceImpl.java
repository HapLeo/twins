package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hapleow.twins.service.INodeService;

import java.util.List;

@Service
@Slf4j
public class NodeServiceImpl implements INodeService {

    @Autowired
    private CuratorFramework client;


    @Override
    public void create(String path) {
        try {
            client.create().withMode(CreateMode.PERSISTENT).forPath(path);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getChildren(String path) {

        try {
            return client.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
