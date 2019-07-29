package qsh.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import qsh.model.Member;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    /**
     * 保存成员
     * @param member 成员
     */
    void save(Member member);

    /**
     * ID查成员
     * @param id ID
     * @return 成员
     */
    Member getById(Long id);

    /**
     * 更新成员
     * @param member 成员
     */
    void update(Member member);

    /**
     * 成员列表
     * @param params 参数
     * @return 成员列表
     */
    List<Member> getMemberList(Map<String, Object> params);

    /**
     * 删除成员
     * @param id ID
     */
    void deleteById(Long id);

    /**
     * 删除成员资源
     * @param id 成员ID
     */
    void deleteMemberResources(Long id);

    /**
     * 保存成员的资源
     * @param id 成员ID
     * @param resourceIds 资源ID
     */
    void saveMemberResources(@Param("id") Long id, @Param("resourceIds") List<Long> resourceIds);
}
