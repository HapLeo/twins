package top.hapleow.twins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.twins.service.IWatcherService;

/**
 * 监听节点的API
 */
@RestController
@RequestMapping("/watcher")
public class WatcherController {

    @Autowired
    private IWatcherService watcherService;

    /**
     * 监听一个节点是否被新增、修改、删除
     */
    @RequestMapping("/checkExists")
    public String checkExists(@RequestParam String path){
        watcherService.checkExists(path);
        return "SUCCESS";
    }

    /**
     * 监听一个节点是否被修改
     */
    @RequestMapping("/getDataWatch")
    public String getDataWatch(@RequestParam String path){
        watcherService.getDataWatch(path);
        return "SUCCESS";
    }

    /**
     * 监听一个节点的子节点是否被新增、修改、删除
     */
    @RequestMapping("/getChildrenWatch")
    public String getChildrenWatch(@RequestParam String path){
        watcherService.getChildrenWatch(path);
        return "SUCCESS";
    }

}
