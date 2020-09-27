package top.hapleow.twins.service;

/**
 * Listener监听服务
 */
public interface IListenerService {

    /**
     * 持续监听节点信息
     * NodeCacheListener会监听指定节点和该节点的所有子子孙孙节点的增删改操作
     *
     * @param path
     */
    void listenerStart(String path);

    /**
     * 取消节点监听
     * @param path
     */
    void listenerRemove(String path);
}
