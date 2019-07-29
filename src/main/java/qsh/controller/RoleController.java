package qsh.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qsh.model.RestResp;
import qsh.model.Role;
import qsh.vo.DataTable;
import qsh.service.RoleService;
import qsh.vo.RoleVO;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/26 2:13 PM
 */
@RestController
@RequestMapping("/role")
@Api(tags = "权限模块")
public class RoleController {

    @Autowired(required = false)
    private RoleService roleService;


    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Role role) {
        roleService.add(role);
        return RestResp.ok("添加成功！");
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Role role) {
        roleService.edit(role);
        return RestResp.ok("更新成功！");
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public DataTable<Role> tables(@RequestBody RoleVO roleVO) {
        return roleService.tables(roleVO);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<Role> tree() {
        return roleService.tree();
    }

    /**
     * get role by id
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Role getById(@PathVariable("roleId") Long roleId) {

        return roleService.get(roleId);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("roleId") Long roleId) {
        roleService.delete(roleId);

        return RestResp.ok("删除成功！");
    }


    /**
     * 授权
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    public RestResp grant(@RequestBody Role role) {

        roleService.grant(role);

        return RestResp.ok("授权成功！");
    }


}
