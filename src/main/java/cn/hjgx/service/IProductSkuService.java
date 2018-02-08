package cn.hjgx.service;

import cn.hjgx.entity.ProductSku;
import cn.hjgx.entity.paramDto.ProductSpuDto;

import java.util.List;

/**
 * Created by alvin on 2018/1/23.
 */
public interface IProductSkuService {

    int insertSelective(ProductSku record);

    int batchInsert(List<ProductSku> records);

    List<ProductSku> selectBySpu(String spu);



}
