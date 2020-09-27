package top.hapleow.twins.service;

/**
 * Listener监听服务
 */
public interface IListenerService {

    /**
     * 持续监听节点信息
     *
     * @param path
     */
    void nodeListenerStart(String path);

    /**
     * 取消节点监听
     * @param path
     */
    void nodeListenerRemove(String path);
}
