package cn.hjgx.service.impl;

import cn.hjgx.entity.ProductSpuImgs;
import cn.hjgx.mapper.ProductSpuImgsMapper;
import cn.hjgx.service.IProductSpuImgsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/24.
 */
@Service
public class ProductSpuImgsService implements IProductSpuImgsService {

    @Autowired
    private ProductSpuImgsMapper productSpuImgsMapper;

    @Override
    public int insertSelective(ProductSpuImgs record) {
        return productSpuImgsMapper.insertSelective(record);
    }

    @Override
    public int batchInsert(List<ProductSpuImgs> records) {
        return productSpuImgsMapper.batchInsert(records);
    }
}
