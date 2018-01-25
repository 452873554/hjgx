package cn.hjgx.service;

import cn.hjgx.entity.Supplier;
import cn.hjgx.entity.page.Pager;

import java.util.List;

/**
 * Created by alvin on 2018/1/11.
 */
public interface ISupplierService {

    /**
     * 列表查询场地数据
     * @param spp
     * @return
     */
    public Pager<Supplier> getSupplierPaged(Supplier spp);

    public List<Supplier> getAllSuppliers();

    public int insertSelective(Supplier record);

    public int deleteByPrimaryKey(Integer id);


}
