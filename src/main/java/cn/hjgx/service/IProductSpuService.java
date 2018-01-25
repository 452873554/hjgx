package cn.hjgx.service;

import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ProductSpuResultDto;


public interface IProductSpuService {

    int insertSelective(ProductSpu record);

    public Pager<ProductSpuResultDto> getProductSpuPaged(ProductSpuResultDto productSpu);


}
