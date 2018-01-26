package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpace;

import java.util.List;

public interface ProductSpaceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpace record);

    int insertSelective(ProductSpace record);

    ProductSpace selectByPrimaryKey(Integer id);

    List<ProductSpace> getAllProductSpaces();

    List<ProductSpace> selectByPageParam(ProductSpace brand);

    int updateByPrimaryKeySelective(ProductSpace record);

    int updateByPrimaryKey(ProductSpace record);

    int getMaxIdentifyOrder();

}