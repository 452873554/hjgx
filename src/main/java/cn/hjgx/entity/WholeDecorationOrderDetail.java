package cn.hjgx.entity;

public class WholeDecorationOrderDetail {
    private Integer id;

    private Integer wdSpaceId;

    private Integer wdItemId;

    private String spu;

    private String sku;

    private Integer qty;

    private String productName;

    private String productAttrs;

    private Integer orderId;

    private String orderNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWdSpaceId() {
        return wdSpaceId;
    }

    public void setWdSpaceId(Integer wdSpaceId) {
        this.wdSpaceId = wdSpaceId;
    }

    public Integer getWdItemId() {
        return wdItemId;
    }

    public void setWdItemId(Integer wdItemId) {
        this.wdItemId = wdItemId;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAttrs() {
        return productAttrs;
    }

    public void setProductAttrs(String productAttrs) {
        this.productAttrs = productAttrs;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}