package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpu;

public interface ProductSpuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpu record);

    int insertSelective(ProductSpu record);

    ProductSpu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpu record);

    int updateByPrimaryKey(ProductSpu record);
}