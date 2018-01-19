package cn.hjgx.entity;

import cn.hjgx.entity.pagedto.PageDto;

import java.util.Date;

public class Brand extends PageDto {
    private Integer id;

    private String brandName;

    private Integer identifyOrder;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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