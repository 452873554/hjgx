package cn.hjgx.entity;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.entity.pagedto.PageDto;
import cn.hjgx.entity.pagedto.WholeDecorationOrderDetailDto;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private String province;

    private String city;

    private String district;

    private String address;

    private String receiver;

    private String receiverCellPhone;

    private String remark;

    private List<WholeDecorationOrderDetailDto> wholeDecorationOrderDetailDtos;

    public List<WholeDecorationOrderDetailDto> getWholeDecorationOrderDetailDtos() {
        return wholeDecorationOrderDetailDtos;
    }

    public void setWholeDecorationOrderDetailDtos(List<WholeDecorationOrderDetailDto> wholeDecorationOrderDetailDtos) {

        for (WholeDecorationOrderDetailDto wholeDecorationOrderDetailDto : wholeDecorationOrderDetailDtos) {

            try {
                Map<String,String> attrMap =
                        JsonUtil.toPOJO(wholeDecorationOrderDetailDto.getProductAttrs(), new TypeReference<java.util.Map>() {
                        });
                wholeDecorationOrderDetailDto.setAttrsMap(attrMap);
            } catch (Exception e) {
                //TODO 暂不处理
            }
        }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverCellPhone() {
        return receiverCellPhone;
    }

    public void setReceiverCellPhone(String receiverCellPhone) {
        this.receiverCellPhone = receiverCellPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}