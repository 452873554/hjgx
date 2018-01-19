package cn.hjgx.mapper;

import cn.hjgx.entity.Brand;

import java.util.List;

public interface BrandMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    int getMaxIdentifyOrder();

    Brand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> selectByPageParam(Brand brand);

    public List<Brand> getAllBrands();

}