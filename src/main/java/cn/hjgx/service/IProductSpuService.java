package cn.hjgx.service;

import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;
import cn.hjgx.entity.paramDto.ProductSpuDto;


public interface IProductSpuService {

    int insertSelective(ProductSpu record);

    ProductSpuDto getProductSpuDetail(String spu);

    Pager<ProductSpuResultDto> getProductSpuPaged(ProductSpuResultDto productSpu);

    Pager<ProductSpuResultDto> getProductSpuWithOutPageHelper(ProductSpuResultDto productSpu);


}
