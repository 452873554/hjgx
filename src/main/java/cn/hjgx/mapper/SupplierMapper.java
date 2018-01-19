package cn.hjgx.mapper;

import cn.hjgx.entity.Site;
import cn.hjgx.entity.Supplier;
import cn.hjgx.entity.pagedto.SitePageParam;
import cn.hjgx.entity.pagedto.SupplierPageParam;

import java.util.List;

public interface SupplierMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    List<Supplier> selectByPageParam(Supplier spp);

    List<Supplier> getAllSuppliers();


}