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
    void nodeListenerStart(String path);


    /**
     * 路径监听启动
     * PathListener只监听指定节点的子子孙孙节点，不监听自己
     *
     * @param path
     */
    void pathListenerStart(String path);

    /**
     * 监听取消
     *
     * @param path
     */
    void listenerRemove(String path, String type);

}
