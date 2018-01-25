package cn.hjgx.entity;

import cn.hjgx.entity.pagedto.PageDto;

import java.util.Date;

public class ProductStyle extends PageDto {
    private Integer id;

    private String styleName;

    private Integer identifyOrder;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public Integer getIdentifyOrder() {
        return identifyOrder;
    }

    public void setIdentifyOrder(Integer identifyOrder) {
        this.identifyOrder = identifyOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}