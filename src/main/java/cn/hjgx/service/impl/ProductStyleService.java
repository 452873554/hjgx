package cn.hjgx.service.impl;

import cn.hjgx.entity.ProductStyle;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.ProductStyleMapper;
import cn.hjgx.service.IProductStyleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/22.
 */
@Service
public class ProductStyleService implements IProductStyleService {

    @Autowired
    private ProductStyleMapper productStyleMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productStyleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(ProductStyle style) {
        return productStyleMapper.insertSelective(style);
    }

    @Override
    public int getMaxIdentifyOrder() {
        return productStyleMapper.getMaxIdentifyOrder();
    }

    @Override
    public Pager<ProductStyle> getProductStylePaged(ProductStyle productStyle) {

        PageHelper.startPage(productStyle.getPageOffSet(), productStyle.getPageSize());
        PageHelper.orderBy("identify_order desc");
        List<ProductStyle> datas = productStyleMapper.selectByPageParam(productStyle);

        Pager<ProductStyle> pager = new Pager<ProductStyle>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public List<ProductStyle> getAllStyle() {
        return productStyleMapper.getAllStyle();
    }
}
