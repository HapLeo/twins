package top.hapleow.twins.service;

/**
 * Listener监听服务
 */
public interface IListenerService {

    /**
     * 监听节点变化
     *
     * @param path
     */
    void curatorCacheListener(String path);
}
