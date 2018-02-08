package cn.hjgx.service.impl;

import cn.hjgx.entity.ProductSku;
import cn.hjgx.mapper.ProductSkuMapper;
import cn.hjgx.service.IProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/23.
 */
@Service
public class ProductSkuService implements IProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public int insertSelective(ProductSku record) {
        return productSkuMapper.insertSelective(record);
    }

    @Override
    public int batchInsert(List<ProductSku> records) {
        return productSkuMapper.batchInsert(records);
    }

    @Override
    public List<ProductSku> selectBySpu(String spu) {
        return productSkuMapper.selectBySpu(spu);
    }
}
