package qsh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qsh.mapper.BusinessMapper;
import qsh.model.Business;
import qsh.service.BusinessService;
import qsh.util.SessionUtil;
import qsh.util.id.IdUtil;
import qsh.vo.BusinessVO;
import qsh.vo.DataTable;
import qsh.vo.MemberVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zl
 * @date 2019/7/28 3:43 PM
 */
@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Override
    public void add(Business business) {
        business.setId(IdUtil.generateId());
        businessMapper.save(business);
    }

    @Override
    public Business get(Long businessId) {
        Business business = businessMapper.getById(businessId);
        return business;
    }

    @Override
    public void edit(Business business) {
        businessMapper.update(business);
    }


    @Override
    public void delete(Long id) {
        businessMapper.deleteById(id);
    }


    @Override
    public List<Business> businesses() {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的角色
        }


        return businessMapper.getBusinessList(params);
    }

    @Override
    public List<Business> allBusiness() {
        return businessMapper.getBusinessList(new HashMap<>());
    }


    @Override
    public DataTable<Business> tables(BusinessVO businessVO) {
        PageHelper.offsetPage(businessVO.getStart(), businessVO.getLength());

        List<Business> businesses = businessMapper.getBusinessList(new HashMap<>());
        DataTable<Business> tables = new DataTable<>();
        tables.setRecordsTotal(((Page) businesses).getTotal());
        tables.setRecordsFiltered(tables.getRecordsTotal());
        tables.setDraw(businessVO.getDraw());
        tables.setData(businesses);
        return tables;
    }

    @Override
    public List<Business> tree() {

        Map<String, Object> params = new HashMap<>();

        List<Business> businesses = businessMapper.getBusinessList(params);



        return businesses;
    }

}
