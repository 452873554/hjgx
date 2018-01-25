package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpuAttrsTemplate;

import java.util.List;

public interface ProductSpuAttrsTemplateMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpuAttrsTemplate record);

    int insertSelective(ProductSpuAttrsTemplate record);

    ProductSpuAttrsTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpuAttrsTemplate record);

    int updateByPrimaryKey(ProductSpuAttrsTemplate record);

    List<ProductSpuAttrsTemplate> getAllTemplate();

    List<ProductSpuAttrsTemplate> selectByPageParam(ProductSpuAttrsTemplate productSpuAttrsTemplate);

}