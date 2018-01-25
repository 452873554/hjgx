package cn.hjgx.service.impl;

import cn.hjgx.entity.ProductSpuAttrs;
import cn.hjgx.mapper.ProductSpuAttrsMapper;
import cn.hjgx.service.IProductSpuAttrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alvin on 2018/1/23.
 */
@Service
public class ProductSpuAttrsService implements IProductSpuAttrsService {

    @Autowired
    private ProductSpuAttrsMapper productSpuAttrsMapper;

    @Override
    public int insertSelective(ProductSpuAttrs record) {
        return productSpuAttrsMapper.insertSelective(record);
    }
}
