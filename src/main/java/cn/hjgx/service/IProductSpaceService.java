package cn.hjgx.service;

import cn.hjgx.entity.ProductSpace;
import cn.hjgx.entity.page.Pager;

import java.util.List;

/**
 * Created by alvin on 2018/1/25.
 */
public interface IProductSpaceService {

    int insertSelective(ProductSpace record);

    int getMaxIdentifyOrder();

    int deleteByPrimaryKey(Integer id);

    List<ProductSpace> getAllProductSpaces();

    public Pager<ProductSpace> getProductSpacePaged(ProductSpace brand);

}
