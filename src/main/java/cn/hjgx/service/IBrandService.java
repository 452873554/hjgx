package cn.hjgx.service;

import cn.hjgx.entity.Brand;
import cn.hjgx.entity.page.Pager;

import java.util.List;

/**
 * Created by alvin on 2018/1/11.
 */
public interface IBrandService {

    public Pager<Brand> getBrandPaged(Brand brand);

    public List<Brand> getAllBrands();

    public int getMaxIdentifyOrder();

    int insertSelective(Brand record);

    public int deleteByPrimaryKey(Integer id);

}
