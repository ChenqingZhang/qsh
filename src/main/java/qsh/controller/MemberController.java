package qsh.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qsh.model.Member;
import qsh.model.RestResp;
import qsh.model.Role;
import qsh.service.MemberService;
import qsh.service.RoleService;
import qsh.vo.DataTable;
import qsh.vo.MemberVO;
import qsh.vo.RoleVO;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/28 9:50 AM
 */
@RestController
@RequestMapping("/member")
@Api(tags = "会员模块")
public class MemberController {

    @Autowired
    private MemberService memberService;


    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Member member) {
        memberService.add(member);
        return RestResp.ok("添加成功！");
    }

    /**
     * 修改角色
     *
     * @param member
     * @return
     */
    @RequestMapping(method= RequestMethod.PUT)
    public RestResp edit(@RequestBody Member member) {
        memberService.edit(member);
        return RestResp.ok("更新成功！");
    }

    @RequestMapping(value = "/tables", method = RequestMethod.POST)
    public DataTable<Member> tables(@RequestBody MemberVO memberVO) {
        return memberService.tables(memberVO);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public List<Member> tree() {
        return memberService.tree();
    }

    /**
     * get role by id
     *
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    public Member getById(@PathVariable("memberId") Long memberId) {

        return memberService.get(memberId);
    }

    /**
     * 删除角色
     *
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/{memberId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("memberId") Long memberId) {
        memberService.delete(memberId);

        return RestResp.ok("删除成功！");
    }


}

