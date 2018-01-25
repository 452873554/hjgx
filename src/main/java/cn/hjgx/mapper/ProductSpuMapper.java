package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;

import java.util.List;

public interface ProductSpuMapper {

    List<ProductSpuResultDto> selectByPageParam(ProductSpuResultDto productSpu);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpu record);

    int insertSelective(ProductSpu record);

    ProductSpu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpu record);

    int updateByPrimaryKey(ProductSpu record);

}