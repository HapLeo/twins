package top.hapleow.twins.service;

import org.apache.zookeeper.CreateMode;

import java.util.List;

public interface INodeService {


    /**
     * 创建节点
     *  @param createMode
     * @param path
     * @param data
     */
    void create(CreateMode createMode, String path, String data);

    /**
     * 获取指定节点的子节点列表
     *
     * @param path
     * @return
     */
    List<String> getChildren(String path);

    /**
     * 删除指定节点
     *
     * @param path
     */
    void delete(String path);

    /**
     * 修改节点（赋值）
     *
     * @param path
     * @param data
     */
    void setData(String path, String data);

    /**
     * 获取节点的值
     * @param path
     */
    String getData(String path);
}
