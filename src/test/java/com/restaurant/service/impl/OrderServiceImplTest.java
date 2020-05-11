package com.restaurant.service.impl;

import com.restaurant.dao.OrderDao;
import com.restaurant.dao.Page;
import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.entity.OrderEntity;
import com.restaurant.service.mapper.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {
    private final static Long ID = 1L;
    private final static Page PAGE = new Page(5, 0);
    private final static OrderStatus ORDER_STATUS = OrderStatus.FORMED;
    private static List<OrderEntity> ORDER_ENTITY_LIST;
    private static OrderEntity ORDER_ENTITY = OrderEntity.builder().withId(ID).build();
    private static Order ORDER = Order.builder().withId(ID).build();

    @Mock
    private OrderDao orderDao;
    @Mock
    private Mapper<OrderEntity, Order> orderMapper;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setUp() {
        ORDER_ENTITY_LIST = new ArrayList<>();
        ORDER_ENTITY_LIST.add(ORDER_ENTITY);
    }

    @Test
    public void getOrdersByUserId() {
        when(orderDao.getOrdersByUserId(ID)).thenReturn(ORDER_ENTITY_LIST);
        when(orderMapper.mapEntityToDomain(any())).thenReturn(ORDER);

        final List<Order> orderList = orderService.getOrdersByUserId(ID);

        assertTrue(orderList.size() > 0);
        verify(orderDao).getOrdersByUserId(ID);
        verify(orderMapper).mapEntityToDomain(any());
    }

    @Test
    public void getOrderByStatusAndUserId() {
        when(orderDao.getOrderByStatusAndUserId(eq(ORDER_STATUS.name()), anyLong())).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapEntityToDomain(any())).thenReturn(ORDER);

        final Optional<Order> order = orderService.getOrderByStatusAndUserId(ORDER_STATUS, ID);

        assertEquals(Optional.of(ORDER), order);
        verify(orderDao).getOrderByStatusAndUserId(eq(ORDER_STATUS.name()), anyLong());
        verify(orderMapper).mapEntityToDomain(any());
    }

    @Test
    public void addOrder() {
        when(orderDao.save(any())).thenReturn(true);
        orderService.addOrder(ORDER);
        verify(orderDao).save(any());
    }

    @Test
    public void addDishToOrder() {
        doNothing().when(orderDao).addDishToOrder(anyLong(), anyLong());
        orderService.addDishToOrder(ID, ID);
        verify(orderDao).addDishToOrder(anyLong(), anyLong());
    }

    @Test
    public void deleteOrdersDishById() {
        doNothing().when(orderDao).deleteOrderDishById(anyLong(), anyLong(), any());
        orderService.deleteOrderDishById(ID, ID, 1);
        verify(orderDao).deleteOrderDishById(anyLong(), anyLong(), any());
    }

    @Test
    public void getOrdersExceptFormed() {
        when(orderDao.findAll(ID, PAGE)).thenReturn(ORDER_ENTITY_LIST);
        when(orderMapper.mapEntityToDomain(any())).thenReturn(ORDER);

        final List<Order> orderList = orderService.getOrdersExceptFormed(ID, PAGE);

        assertTrue(orderList.size() > 0);
        verify(orderDao).findAll(ID, PAGE);
        verify(orderMapper).mapEntityToDomain(any());
    }

    @Test
    public void updateOrderStatus() {
        doNothing().when(orderDao).updateOrderStatus(anyLong(), eq(ORDER_STATUS.name()));
        orderService.updateOrderStatus(ID, ORDER_STATUS);
        verify(orderDao).updateOrderStatus(anyLong(), eq(ORDER_STATUS.name()));
    }

    @Test
    public void updateOrderCostAndStatus() {
        doNothing().when(orderDao).updateOrderCostAndStatus(anyInt(), anyLong());
        orderService.updateOrderCostAndStatus(123, ID);
        verify(orderDao).updateOrderCostAndStatus(anyInt(), anyLong());
    }

    @Test
    public void count() {
        when(orderDao.count(ID)).thenReturn(10);

        assertTrue(orderService.count(ID) > 0);

        verify(orderDao).count(ID);
    }

    @Test
    public void getOrderById() {
        when(orderDao.findById(ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderMapper.mapEntityToDomain(any())).thenReturn(ORDER);

        final Optional<Order> order = orderService.getOrderById(ID);

        assertEquals(Optional.of(ORDER), order);
        verify(orderDao).findById(ID);
        verify(orderMapper).mapEntityToDomain(any());
    }

    @Test
    public void getOrdersByStatus() {
        when(orderDao.getOrdersByStatus(eq(ORDER_STATUS.name()), any())).thenReturn(ORDER_ENTITY_LIST);
        when(orderMapper.mapEntityToDomain(any())).thenReturn(ORDER);

        final List<Order> orderList = orderService.getOrdersByStatus(ORDER_STATUS, PAGE);

        assertTrue(orderList.size() > 0);
        verify(orderDao).getOrdersByStatus(eq(ORDER_STATUS.name()), any());
        verify(orderMapper).mapEntityToDomain(any());
    }

    @Test
    public void countOrdersByStatus() {
        when(orderDao.countByStatus(ORDER_STATUS.name())).thenReturn(10);
        assertTrue(orderService.countOrdersByStatus(ORDER_STATUS) > 0);
        verify(orderDao).countByStatus(ORDER_STATUS.name());
    }

    @Test
    public void addLunchToOrder() {
        doNothing().when(orderDao).addLunchToOrder(anyLong(), anyLong());
        orderService.addLunchToOrder(ID, ID);
        verify(orderDao).addLunchToOrder(anyLong(), anyLong());
    }

    @Test
    public void deleteOrderLunchById() {
        doNothing().when(orderDao).deleteOrderLunchById(anyLong(), anyLong(), anyInt());
        orderService.deleteOrderLunchById(ID, ID, 1);
        verify(orderDao).deleteOrderLunchById(anyLong(), anyLong(), anyInt());
    }
}