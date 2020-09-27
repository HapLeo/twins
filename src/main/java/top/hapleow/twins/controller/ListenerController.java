package top.hapleow.twins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public String nodeListenerStart(@RequestParam String path) {
        listenerService.nodeListenerStart(path);
        return "SUCCESS";
    }

    /**
     * 取消监听节点操作
     */
    @RequestMapping("/nodeListenerClose")
    public String nodeListenerClose(@RequestParam String path) {
        listenerService.nodeListenerClose(path);
        return "SUCCESS";
    }
}
