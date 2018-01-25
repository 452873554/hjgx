package cn.hjgx.service.impl;

import cn.hjgx.entity.ProductSpuAttrsTemplate;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.ProductSpuAttrsTemplateMapper;
import cn.hjgx.service.IProductSpuAttrsTemplateService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/24.
 */

@Service
public class ProductSpuAttrsTemplateService implements IProductSpuAttrsTemplateService {

    @Autowired
    private ProductSpuAttrsTemplateMapper productSpuAttrsTemplateMapper;

    @Override
    public int insertSelective(ProductSpuAttrsTemplate record) {
        return productSpuAttrsTemplateMapper.insertSelective(record);
    }

    @Override
    public Pager<ProductSpuAttrsTemplate> getTemplatePaged(ProductSpuAttrsTemplate productSpuAttrsTemplate) {

        PageHelper.startPage(productSpuAttrsTemplate.getPageOffSet(), productSpuAttrsTemplate.getPageSize());
        List<ProductSpuAttrsTemplate> datas = productSpuAttrsTemplateMapper.selectByPageParam(productSpuAttrsTemplate);

        Pager<ProductSpuAttrsTemplate> pager = new Pager<ProductSpuAttrsTemplate>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productSpuAttrsTemplateMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProductSpuAttrsTemplate> getAllTemplate() {
        return productSpuAttrsTemplateMapper.getAllTemplate();
    }

}
