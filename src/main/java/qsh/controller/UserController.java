package qsh.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qsh.jwt.AuthTokenDetails;
import qsh.jwt.JsonWebTokenUtility;
import qsh.model.AuthToken;
import qsh.model.RestResp;
import qsh.model.User;
import qsh.vo.DataTable;
import qsh.service.UserService;
import qsh.util.SessionUtil;
import qsh.vo.UserVO;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author zl
 * @date 2019/7/26 2:18 PM
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

    /**
     * 用户登录
     *
     * @param u 用户对象
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthToken login(@RequestBody User u) {
        AuthToken authToken = null;

        User user = userService.login(u);

        if (user != null) {


            AuthTokenDetails authTokenDetails = new AuthTokenDetails();
            authTokenDetails.setId(user.getId());
            authTokenDetails.setUsername(user.getUsername());
            authTokenDetails.setExpirationDate(buildExpirationDate());
            authTokenDetails.setRoleNames(userService.getUserRoleNames(user.getId()));

            // Create auth token
            String jwt = tokenService.createJsonWebToken(authTokenDetails);
            if (jwt != null) {
                authToken = new AuthToken();
                authToken.setToken(jwt);
                authToken.setUserId(user.getId());
                authToken.setName(user.getName());
                authToken.setResourceList(userService.resourceList(user.getId()));
            }
        } else {
            throw new RuntimeException("用户名或密码错误");
        }

        return authToken;
    }


    private Date buildExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime();
    }


    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        userService.add(user);
        return user;
    }


    /**
     * 修改用户
     */
    @RequestMapping(method = RequestMethod.PUT)
    public User edit(@RequestBody User user) {
        userService.edit(user);
        return user;
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public DataTable<User> tables(@RequestBody UserVO userVO) {
        return userService.tables(userVO);
    }


    /**
     * 用户详情
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("userId") Long userId) {
        User user = userService.get(userId);
        user.setPassword(null);
        return user;
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("userId") Long userId) {
        Long currUid = SessionUtil.getCurrUid();
        if (Objects.equals(userId, currUid)) {// 不能删除自己
            return RestResp.error(RestResp.ERROR, "不能删除自己");
        }

        userService.delete(userId);

        return RestResp.ok("删除成功");
    }

    /**
     * 批量删除用户
     *
     * @param ids ('0','1','2')
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.DELETE)
    public void batchDelete(String ids) {
        if (ids != null && ids.length() > 0) {
            for (String id : ids.split(",")) {
                this.delete(Long.valueOf(id));
            }
        }
    }

    /**
     * 用户授权
     */
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ResponseBody
    public RestResp grant(@RequestBody User user) {
        userService.grant(user);
        return RestResp.ok("授权成功！");
    }


    /**
     * 编辑用户密码
     *
     * @param user
     * @return
     */
    @RequestMapping("/editPwd")
    public RestResp editPwd(@RequestBody User user) {
        userService.editPwd(user);
        return RestResp.ok("编辑成功");
    }


    /**
     * 修改自己的密码
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editCurrentUserPwd", method = RequestMethod.POST)
    @ResponseBody
    public RestResp editCurrentUserPwd(@RequestBody User user) {
        Long currUid = SessionUtil.getCurrUid();

        if (currUid != null) {
            if (!userService.editCurrentUserPwd(currUid, user.getOldPassword(), user.getPassword())) {
                throw new RuntimeException("原密码错误！");
            }
        } else {
            throw new RuntimeException("登录超时，请重新登录！");
        }

        return RestResp.ok("修改成功");
    }
}
