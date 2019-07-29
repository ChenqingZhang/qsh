package qsh.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import qsh.model.Business;

import java.util.List;
import java.util.Map;

@Mapper
public interface BusinessMapper extends BaseMapper<Business> {
    /**
     * 保存事务
     * @param business 事务
     */
    void save(Business business);

    /**
     * ID查事务
     * @param id ID
     * @return 事务
     */
    Business getById(Long id);

    /**
     * 更新事务
     * @param business 事务
     */
    void update(Business business);

    /**
     * 事务列表
     * @param params 参数
     * @return 事务列表
     */
    List<Business> getBusinessList(Map<String, Object> params);

    /**
     * 删除事务
     * @param id ID
     */
    void deleteById(Long id);
    
    
}
