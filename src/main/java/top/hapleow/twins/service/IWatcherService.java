package top.hapleow.twins.service;

/**
 * 监听服务
 */
public interface IWatcherService {


    /**
     * 监听一个节点是否被新增、修改、删除
     */
    void checkExists(String path);

    /**
     * 监听一个节点是否被修改
     */
    void getDataWatch(String path);

    /**
     * 监听一个节点的子节点是否被新增、删除,不会监听子节点值的修改
     */
    void getChildrenWatch(String path);
}
