package cn.hjgx.service.impl;


import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;
import cn.hjgx.mapper.ProductSpuMapper;
import cn.hjgx.service.IProductSpuService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpuService implements IProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;

    @Override
    public int insertSelective(ProductSpu record) {
        return productSpuMapper.insertSelective(record);
    }

    @Override
    public Pager<ProductSpuResultDto> getProductSpuPaged(ProductSpuResultDto productSpu) {
        PageHelper.startPage(productSpu.getPageOffSet(), productSpu.getPageSize());
        PageHelper.orderBy("identify_order desc");
        List<ProductSpuResultDto> datas = productSpuMapper.selectByPageParam(productSpu);

        Pager<ProductSpuResultDto> pager = new Pager<ProductSpuResultDto>();
        pager.setDatas(datas);

        return pager;
    }
}