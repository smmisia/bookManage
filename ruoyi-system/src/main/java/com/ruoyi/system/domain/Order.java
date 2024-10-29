package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单信息对象 order
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 总价格 */
    @Excel(name = "总价格")
    private Long totalPrice;

    /** 用户希望的送货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用户希望的送货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 订单状态,0未支付,1取消，2完成 */
    @Excel(name = "订单状态,0未支付,1取消，2完成")
    private Long orderStatus;

    /** 订单中的商品信息 */
    @Excel(name = "订单中的商品信息")
    private String items;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTotalPrice(Long totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public Long getTotalPrice() 
    {
        return totalPrice;
    }
    public void setDeliveryDate(Date deliveryDate) 
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    public void setOrderStatus(Long orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Long getOrderStatus() 
    {
        return orderStatus;
    }
    public void setItems(String items) 
    {
        this.items = items;
    }

    public String getItems() 
    {
        return items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("totalPrice", getTotalPrice())
            .append("deliveryDate", getDeliveryDate())
            .append("orderStatus", getOrderStatus())
            .append("items", getItems())
            .toString();
    }
}
