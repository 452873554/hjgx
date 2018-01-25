package cn.hjgx.service;

import cn.hjgx.entity.ProductSpuAttrsTemplate;
import cn.hjgx.entity.page.Pager;

import java.util.List;

/**
 * Created by alvin on 2018/1/23.
 */
public interface IProductSpuAttrsTemplateService {

    int insertSelective(ProductSpuAttrsTemplate record);

    Pager<ProductSpuAttrsTemplate> getTemplatePaged(ProductSpuAttrsTemplate record);

    int deleteByPrimaryKey(Integer id);

    List<ProductSpuAttrsTemplate> getAllTemplate();

}
