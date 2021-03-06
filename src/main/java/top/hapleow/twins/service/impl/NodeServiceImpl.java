package top.hapleow.twins.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hapleow.twins.exception.NoNodeException;
import top.hapleow.twins.service.INodeService;

import java.util.List;

/**
 * 操作节点的API
 */
@Service
@Slf4j
public class NodeServiceImpl implements INodeService {

    @Autowired
    private CuratorFramework client;


    @Override
    public void create(CreateMode createMode, String path, String data) {
        try {
            client.create().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            throw new NoNodeException("节点不存在!" + e.getMessage());
        }
    }

    @Override
    public void setData(String path, String data) {
        if (data == null) {
            return;
        }
        try {
            client.setData().forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getData(String path) {
        try {
            byte[] bytes = client.getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getChildren(String path) {

        try {
            return client.getChildren().forPath(path);
        } catch (Exception e) {
            throw new NoNodeException("节点不存在！" + path);
        }
    }
}
