package cn.hjgx.service;

import cn.hjgx.entity.ProductSpuImgs;

import java.util.List;

/**
 * Created by alvin on 2018/1/24.
 */
public interface IProductSpuImgsService {

    int insertSelective(ProductSpuImgs record);

    int batchInsert(List<ProductSpuImgs> records);

}
