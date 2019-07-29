package qsh.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qsh.model.Menu;
import qsh.model.Resource;
import qsh.model.RestResp;
import qsh.service.ResourceService;
import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 1:07 PM
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "资源模块")
public class ResourceController {

    /**
     *
     */
    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public List<Menu> menus() {
        return resourceService.menus();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource getById(@PathVariable("id") Long id) {
        return resourceService.get(id);
    }

    @RequestMapping(value = "/allMenus", method = RequestMethod.POST)
    public List<Menu> allMenus() {

        return resourceService.allMenus();
    }

    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Resource resource) {
        resourceService.add(resource);
        return RestResp.ok("添加成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("id") Long id) {
        resourceService.delete(id);

        return RestResp.ok("删除成功");
    }

    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public List<Resource> treeList() {
        return resourceService.treeList();
    }

}

