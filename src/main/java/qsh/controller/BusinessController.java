package qsh.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qsh.model.Business;
import qsh.model.RestResp;
import qsh.service.BusinessService;
import qsh.vo.BusinessVO;
import qsh.vo.DataTable;
import qsh.vo.MemberVO;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/28 9:55 AM
 */
@RestController
@RequestMapping("/business")
@Api(tags = "事务模块")
public class BusinessController {

    @Autowired
    private BusinessService businessService;


    /**
     * 添加事务
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Business business) {
        businessService.add(business);
        return RestResp.ok("添加成功！");
    }

    /**
     * 修改事务
     *
     * @param business
     * @return
     */
    @RequestMapping(method= RequestMethod.PUT)
    public RestResp edit(@RequestBody Business business) {
        businessService.edit(business);
        return RestResp.ok("更新成功！");
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public DataTable<Business> tables(@RequestBody BusinessVO businessVO) {
        return businessService.tables(businessVO);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<Business> tree() {
        return businessService.tree();
    }

    /**
     * get business by id
     *
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/{businessId}", method = RequestMethod.GET)
    public Business getById(@PathVariable("businessId") Long businessId) {

        return businessService.get(businessId);
    }

    /**
     * 删除事务
     *
     * @param businessId
     * @return
     */
    @RequestMapping(value = "/{businessId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("businessId") Long businessId) {
        businessService.delete(businessId);

        return RestResp.ok("删除成功！");
    }


}
