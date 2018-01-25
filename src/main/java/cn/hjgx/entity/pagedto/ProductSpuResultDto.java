package cn.hjgx.entity.pagedto;

import cn.hjgx.entity.ProductSpu;

/**
 * Created by alvin on 2018/1/23.
 */
public class ProductSpuResultDto extends ProductSpu {

    private String categoryName;
    private String brandName;
    private String styleName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }
}
