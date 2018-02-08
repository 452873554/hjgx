package cn.hjgx.mapper;

import cn.hjgx.entity.Supplier;

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