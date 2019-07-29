package qsh.service;

import qsh.model.Member;
import qsh.vo.DataTable;
import qsh.vo.MemberVO;

import java.util.List;

public interface MemberService {

    /**
     * 保存成员
     *
     * @param member
     */
    void add(Member member);

    /**
     * 获得成员
     *
     * @param id
     * @return
     */
    Member get(Long id);

    /**
     * 编辑成员
     *
     * @param Member
     */
    void edit(Member Member);


    /**
     * 删除成员
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 获得成员
     *
     * @return
     */
    List<Member> members();

    /**
     * 获得成员
     *
     * @return
     */
    List<Member> allMember();
    

    DataTable<Member> tables(MemberVO MemberVO);

    List<Member> tree();
}