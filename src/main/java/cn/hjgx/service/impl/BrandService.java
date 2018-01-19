package cn.hjgx.service.impl;

import cn.hjgx.entity.Brand;
import cn.hjgx.entity.Supplier;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.BrandMapper;
import cn.hjgx.service.IBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/11.
 */
@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public Pager<Brand> getBrandPaged(Brand brand) {

        PageHelper.startPage(brand.getPageOffSet(), brand.getPageSize());
        PageHelper.orderBy("identify_order desc");
        List<Brand> datas = brandMapper.selectByPageParam(brand);

        Pager<Brand> pager = new Pager<Brand>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandMapper.getAllBrands();
    }

    @Override
    public int getMaxIdentifyOrder() {
        return brandMapper.getMaxIdentifyOrder();
    }

    @Override
    public int insertSelective(Brand record) {
        return brandMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

}
