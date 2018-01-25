package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpuAttrs;

public interface ProductSpuAttrsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpuAttrs record);

    int insertSelective(ProductSpuAttrs record);

    ProductSpuAttrs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpuAttrs record);

    int updateByPrimaryKey(ProductSpuAttrs record);
}