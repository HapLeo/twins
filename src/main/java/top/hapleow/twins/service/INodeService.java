package top.hapleow.twins.service;

import java.util.List;

public interface INodeService {


    /**
     * 创建节点
     *
     * @param path
     */
    void create(String path);

    /**
     * 获取指定节点的子节点列表
     *
     * @param path
     * @return
     */
    List<String> getChildren(String path);
}
