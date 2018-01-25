package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.ProductStyle;

import java.util.List;

public interface ProductStyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductStyle record);

    int insertSelective(ProductStyle record);

    ProductStyle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductStyle record);

    int updateByPrimaryKey(ProductStyle record);

    int getMaxIdentifyOrder();

    List<ProductStyle> selectByPageParam(ProductStyle productStyle);

    List<ProductStyle> getAllStyle();

}