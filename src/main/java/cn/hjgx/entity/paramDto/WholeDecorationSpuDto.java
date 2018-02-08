package cn.hjgx.entity.paramDto;

import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.WholeDecorationItem;
import cn.hjgx.entity.WholeDecorationSpu;

import java.util.List;

/**
 * Created by alvin on 2018/1/30.
 */
public class WholeDecorationSpuDto extends ProductSpu {

    private String imageUrl;

    private Integer itemId;

    private Integer qty;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
