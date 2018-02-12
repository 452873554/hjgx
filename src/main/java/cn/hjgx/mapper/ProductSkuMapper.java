package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSku;
import cn.hjgx.entity.paramDto.ProductSpuDto;

import java.util.List;

public interface ProductSkuMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSku record);

    int insertSelective(ProductSku record);

    ProductSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSku record);

    int updateByPrimaryKey(ProductSku record);

    int batchInsert(List<ProductSku> records);

    List<ProductSku> selectBySpu(String spu);

    ProductSku selectBySku(String sku);

}