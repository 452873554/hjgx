package cn.hjgx.entity.paramDto;

import cn.hjgx.entity.ProductSku;
import cn.hjgx.entity.ProductSpu;

import java.util.List;

/**
 * Created by alvin on 2018/2/5.
 */
public class ProductSpuDto extends ProductSpu {

    private String attrs;

    private String imageUrl;

    private List<ProductSku> skus;

    public String getAttrs() {
        return attrs;
    }

    public void setAttrs(String attrs) {
        this.attrs = attrs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<ProductSku> getSkus() {
        return skus;
    }

    public void setSkus(List<ProductSku> skus) {
        this.skus = skus;
    }
}
