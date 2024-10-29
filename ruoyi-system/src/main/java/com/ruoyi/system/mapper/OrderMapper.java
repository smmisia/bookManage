package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Order;

/**
 * 订单信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-29
 */
public interface OrderMapper 
{
    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    public Order selectOrderById(Long id);

    /**
     * 查询订单信息列表
     * 
     * @param order 订单信息
     * @return 订单信息集合
     */
    public List<Order> selectOrderList(Order order);

    /**
     * 新增订单信息
     * 
     * @param order 订单信息
     * @return 结果
     */
    public int insertOrder(Order order);

    /**
     * 修改订单信息
     * 
     * @param order 订单信息
     * @return 结果
     */
    public int updateOrder(Order order);

    /**
     * 删除订单信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    public int deleteOrderById(Long id);

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderByIds(Long[] ids);
}
