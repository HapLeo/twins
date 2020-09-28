package top.hapleow.twins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.twins.common.Result;
import top.hapleow.twins.service.IListenerService;

/**
 * 监听节点的API
 */
@RestController
@RequestMapping("/listener")
public class ListenerController {

    @Autowired
    private IListenerService listenerService;

    /**
     * 启动监听节点操作
     */
    @RequestMapping("/nodeListenerStart")
    public Result nodeListenerStart(@RequestParam String path) {
        listenerService.nodeListenerStart(path);
        return new Result("nodeListener开始监听" + path, null);
    }

    /**
     * 启动监听路径操作
     */
    @RequestMapping("/pathListenerStart")
    public Result pathListenerStart(@RequestParam String path) {
        listenerService.pathListenerStart(path);
        return new Result("pathListener开始监听" + path, null);
    }

    /**
     * 取消监听路径操作
     */
    @RequestMapping("/listenerRemove")
    public Result listenerRemove(@RequestParam("path") String path, @RequestParam("type") String type) {
        listenerService.listenerRemove(path, type);
        return new Result("取消" + type + "监听,path=" + path, null);
    }
}
