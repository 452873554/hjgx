package cn.hjgx.service.impl;

import cn.hjgx.entity.Site;
import cn.hjgx.entity.Supplier;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.SitePageParam;
import cn.hjgx.entity.pagedto.SupplierPageParam;
import cn.hjgx.mapper.SupplierMapper;
import cn.hjgx.service.ISupplierService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public Pager<Supplier> getSupplierPaged(Supplier spp) {

        PageHelper.startPage(spp.getPageOffSet(), spp.getPageSize());
        PageHelper.orderBy("create_time desc");
        List<Supplier> datas = supplierMapper.selectByPageParam(spp);

        Pager<Supplier> pager = new Pager<Supplier>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierMapper.getAllSuppliers();
    }

    @Override
    public int insertSelective(Supplier record) {
        return supplierMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return supplierMapper.deleteByPrimaryKey(id);
    }

}
