package qsh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qsh.mapper.MemberMapper;
import qsh.model.Member;
import qsh.service.MemberService;
import qsh.util.SessionUtil;
import qsh.util.id.IdUtil;
import qsh.vo.DataTable;
import qsh.vo.MemberVO;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zl
 * @date 2019/7/28 10:10 AM
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper MemberMapper;

    @Override
    public void add(Member member) {
        member.setId(IdUtil.generateId());
        MemberMapper.save(member);
    }

    @Override
    public Member get(Long memberId) {
        Member Member = MemberMapper.getById(memberId);
        return Member;
    }

    @Override
    public void edit(Member member) {
        MemberMapper.update(member);
    }


    @Override
    public void delete(Long id) {
        MemberMapper.deleteById(id);
    }


    @Override
    public List<Member> members() {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的角色
        }


        return MemberMapper.getMemberList(params);
    }

    @Override
    public List<Member> allMember() {
        return MemberMapper.getMemberList(new HashMap<>());
    }


    @Override
    public DataTable<Member> tables(MemberVO MemberVO) {
        PageHelper.offsetPage(MemberVO.getStart(), MemberVO.getLength());

        List<Member> Members = MemberMapper.getMemberList(new HashMap<>());
        DataTable<Member> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) Members).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(MemberVO.getDraw());
        tables.setData(Members);
        return tables;
    }

    @Override
    public List<Member> tree() {

        Map<String, Object> params = new HashMap<>();

        List<Member> Members = MemberMapper.getMemberList(params);



        return Members;
    }

}
