package cn.hjgx.entity;

import cn.hjgx.entity.pagedto.PageDto;
import cn.hjgx.entity.pagedto.WholeDecorationOrderDetailDto;

import java.util.Date;
import java.util.List;

public class WholeDecorationOrder extends PageDto {

    private Integer id;

    private String orderNo;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Double paymentAmount;

    private String username;

    private String wholeDecorationName;

    private Integer wholeDecorationId;

    private List<WholeDecorationOrderDetailDto> wholeDecorationOrderDetailDtos;

    public List<WholeDecorationOrderDetailDto> getWholeDecorationOrderDetailDtos() {
        return wholeDecorationOrderDetailDtos;
    }

    public void setWholeDecorationOrderDetailDtos(List<WholeDecorationOrderDetailDto> wholeDecorationOrderDetailDtos) {
        this.wholeDecorationOrderDetailDtos = wholeDecorationOrderDetailDtos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWholeDecorationName() {
        return wholeDecorationName;
    }

    public void setWholeDecorationName(String wholeDecorationName) {
        this.wholeDecorationName = wholeDecorationName;
    }

    public Integer getWholeDecorationId() {
        return wholeDecorationId;
    }

    public void setWholeDecorationId(Integer wholeDecorationId) {
        this.wholeDecorationId = wholeDecorationId;
    }
}