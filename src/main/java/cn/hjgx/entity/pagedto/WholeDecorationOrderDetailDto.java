package cn.hjgx.entity.pagedto;

import cn.hjgx.entity.WholeDecorationOrderDetail;

import java.util.Map;

/**
 * Created by alvin on 2018/2/11.
 */
public class WholeDecorationOrderDetailDto extends WholeDecorationOrderDetail {

    private String previewImgUrl;

    private Map<String, String> attrsMap;

    public String getPreviewImgUrl() {
        return previewImgUrl;
    }

    public void setPreviewImgUrl(String previewImgUrl) {
        this.previewImgUrl = previewImgUrl;
    }

    public Map<String, String> getAttrsMap() {
        return attrsMap;
    }

    public void setAttrsMap(Map<String, String> attrsMap) {
        this.attrsMap = attrsMap;
    }
}
