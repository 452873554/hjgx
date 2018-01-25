package cn.hjgx.service;

import cn.hjgx.entity.ProductStyle;
import cn.hjgx.entity.page.Pager;

import java.util.List;

/**
 * Created by alvin on 2018/1/22.
 */
public interface IProductStyleService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ProductStyle style);

    int getMaxIdentifyOrder();

    public Pager<ProductStyle> getProductStylePaged(ProductStyle style);

    List<ProductStyle> getAllStyle();

}
