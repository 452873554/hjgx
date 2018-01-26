package cn.hjgx.service.impl;

import cn.hjgx.entity.ProductSpace;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.ProductSpaceMapper;
import cn.hjgx.service.IProductSpaceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/25.
 */
@Service
public class ProductSpaceService implements IProductSpaceService {

    @Autowired
    private ProductSpaceMapper productSpaceMapper;

    @Override
    public int insertSelective(ProductSpace record) {
        return productSpaceMapper.insertSelective(record);
    }

    @Override
    public int getMaxIdentifyOrder() {
        return productSpaceMapper.getMaxIdentifyOrder();
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return productSpaceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<ProductSpace> getAllProductSpaces() {
        return productSpaceMapper.getAllProductSpaces();
    }

    @Override
    public Pager<ProductSpace> getProductSpacePaged(ProductSpace productSpace) {
        PageHelper.startPage(productSpace.getPageOffSet(), productSpace.getPageSize());
        PageHelper.orderBy("identify_order desc");
        List<ProductSpace> datas = productSpaceMapper.selectByPageParam(productSpace);

        Pager<ProductSpace> pager = new Pager<ProductSpace>();
        pager.setDatas(datas);

        return pager;
    }
}
