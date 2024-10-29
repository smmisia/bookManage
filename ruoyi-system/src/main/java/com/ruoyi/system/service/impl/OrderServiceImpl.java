package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderMapper;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.service.IOrderService;

/**
 * 订单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    @Override
    public Order selectOrderById(Long id)
    {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询订单信息列表
     * 
     * @param order 订单信息
     * @return 订单信息
     */
    @Override
    public List<Order> selectOrderList(Order order)
    {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增订单信息
     * 
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int insertOrder(Order order)
    {
        order.setUserId(SecurityUtils.getUserId());
        order.setOrderStatus(0L);
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改订单信息
     * 
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int updateOrder(Order order)
    {
        return orderMapper.updateOrder(order);
    }

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] ids)
    {
        return orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 删除订单信息信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long id)
    {
        return orderMapper.deleteOrderById(id);
    }
}
