package top.hapleow.twins.controller;

import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.twins.service.INodeService;

/**
 * 操作节点的API
 */
@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private INodeService nodeService;


    /**
     * 创建节点
     *
     * @param createMode
     * @param path
     * @return
     */
    @RequestMapping("/create")
    public Object create(@RequestParam(value = "createMode") CreateMode createMode, @RequestParam(value = "path") String path) {
        nodeService.create(createMode, path);
        return "SUCCESS";
    }

    /**
     * 删除节点
     *
     * @param path
     * @return
     */
    @RequestMapping("/delete")
    public Object delete(@RequestParam(value = "path") String path) {
        nodeService.delete(path);
        return "SUCCESS";
    }

    /**
     * 修改节点
     *
     * @param path
     * @return
     */
    @RequestMapping("/setData")
    public Object setData(@RequestParam(value = "path") String path, @RequestParam(value = "value") String value) {
        nodeService.setData(path, value);
        return "SUCCESS";
    }

    /**
     * 获取节点的值
     *
     * @param path
     * @return
     */
    @RequestMapping("/getData")
    public Object getData(@RequestParam(value = "path") String path) {

        return nodeService.getData(path);
    }


    /**
     * 获取制定节点的子节点列表
     *
     * @param path
     * @return
     */
    @RequestMapping("/getChildren")
    public Object getChildren(@RequestParam String path) {
        return nodeService.getChildren(path);
    }

}
