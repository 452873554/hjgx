package cn.hjgx.mapper;

import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;
import cn.hjgx.entity.paramDto.ProductSpuDto;

import java.util.List;

public interface ProductSpuMapper {

    ProductSpuDto getProductSpuDetail(String spu);

    List<ProductSpuResultDto> selectByPageParam(ProductSpuResultDto productSpu);

    List<ProductSpuResultDto> selectByPageParamWithOutPageHelper(ProductSpuResultDto productSpu);

    int selectByPageParamWithOutPageHelperCount(ProductSpuResultDto productSpu);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductSpu record);

    int insertSelective(ProductSpu record);

    ProductSpu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductSpu record);

    int updateByPrimaryKey(ProductSpu record);

}