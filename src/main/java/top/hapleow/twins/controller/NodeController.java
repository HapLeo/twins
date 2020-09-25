package top.hapleow.twins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hapleow.twins.service.INodeService;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private INodeService nodeService;


    @RequestMapping("/create")
    public Object create(@RequestParam String path){
        nodeService.create(path);
        return "SUCCESS";
    }


    @RequestMapping("/getChildren")
    public Object getChildren(@RequestParam String path){
        return nodeService.getChildren(path);
    }

}
