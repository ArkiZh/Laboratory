package com.arki.laboratory.snippet.beanvalidation;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

public class Order {
    // 必须不为 null, 大小是 10
    @NotNull
    @Size(min = 10, max = 10)
    private String orderId;
    // 必须不为空
    @NotEmpty
    private String customer;
    // 必须是一个电子信箱地址
    @Email
    private String email;
    // 必须不为空
    @NotEmpty
    private String address;

    @NotNull(message = "adultTax不能为空")
    private Integer adultTax;

    @NotNull(message = "adultTaxType不能为空")
    @Min(value = 0, message = "adultTaxType 的最小值为0")
    @Max(value = 1, message = "adultTaxType 的最大值为1")
    private Integer adultTaxType;

    //订单取消原因
    @NotNull(message = "reason信息不可以为空")
    @Pattern(regexp = "[1-7]{1}", message = "reason的类型值为1-7中的一个类型")
    private String reason;

    // 必须不为 null, 必须是下面四个字符串'created', 'paid', 'shipped', 'closed'其中之一
    // @Status 是一个定制化的 contraint
    @NotNull
    //@Status
    private String status;
    // 必须不为 null
    @NotNull
    private Date createDate;
    // 嵌套验证
    @Valid
    private Product product;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(Integer adultTax) {
        this.adultTax = adultTax;
    }

    public Integer getAdultTaxType() {
        return adultTaxType;
    }

    public void setAdultTaxType(Integer adultTaxType) {
        this.adultTaxType = adultTaxType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
