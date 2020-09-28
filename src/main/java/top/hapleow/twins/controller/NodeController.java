package top.hapleow.twins.controller;

import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.twins.common.Result;
import top.hapleow.twins.service.INodeService;

import java.util.List;

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
    public Object create(@RequestParam(value = "createMode") CreateMode createMode,
                         @RequestParam(value = "path") String path,
                         @RequestParam(value = "data") String data) {
        nodeService.create(createMode, path, data);
        return Result.SUCCESS(nodeService.getData(path));
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
        return Result.SUCCESS(path);
    }

    /**
     * 修改节点
     *
     * @param path
     * @return
     */
    @RequestMapping("/setData")
    public Result setData(@RequestParam(value = "path") String path, @RequestParam(value = "data") String data) {
        nodeService.setData(path, data);
        return Result.SUCCESS(nodeService.getData(path));
    }

    /**
     * 获取节点的值
     *
     * @param path
     * @return
     */
    @RequestMapping("/getData")
    public Result getData(@RequestParam(value = "path") String path) {

        return new Result("执行成功！", nodeService.getData(path));
    }


    /**
     * 获取制定节点的子节点列表
     *
     * @param path
     * @return
     */
    @RequestMapping("/getChildren")
    public Result getChildren(@RequestParam String path) {

        List<String> children = nodeService.getChildren(path);
        return new Result("执行成功！", children);

    }

}
