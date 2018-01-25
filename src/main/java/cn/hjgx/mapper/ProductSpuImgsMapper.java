package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpuImgs;

import java.util.List;

public interface ProductSpuImgsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpuImgs record);

    int batchInsert(List<ProductSpuImgs> records);

    int insertSelective(ProductSpuImgs record);

    ProductSpuImgs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpuImgs record);

    int updateByPrimaryKey(ProductSpuImgs record);
}