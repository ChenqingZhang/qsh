package qsh.service;

import qsh.model.Business;
import qsh.vo.DataTable;
import qsh.vo.BusinessVO;

import java.util.List;

/**
 * @author zl
 * @date 2019/7/28 3:42 PM
 */
public interface BusinessService {

    /**
     * 保存事务
     *
     * @param business
     */
    void add(Business business);

    /**
     * 获得事务
     *
     * @param id
     * @return
     */
    Business get(Long id);

    /**
     * 编辑事务
     *
     * @param business
     */
    void edit(Business business);


    /**
     * 删除事务
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 获得事务
     *
     * @return
     */
    List<Business> businesses();

    /**
     * 获得事务
     *
     * @return
     */
    List<Business> allBusiness();


    DataTable<Business> tables(BusinessVO MemberVO);

    List<Business> tree();
}
