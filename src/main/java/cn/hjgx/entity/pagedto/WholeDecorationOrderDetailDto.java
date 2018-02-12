package cn.hjgx.entity.pagedto;

import cn.hjgx.entity.WholeDecorationOrderDetail;

/**
 * Created by alvin on 2018/2/11.
 */
public class WholeDecorationOrderDetailDto extends WholeDecorationOrderDetail {

    private String previewImgUrl;

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }
}
