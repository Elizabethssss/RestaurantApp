package com.restaurant.service.mapper;

import com.restaurant.domain.Order;
import com.restaurant.domain.OrderStatus;
import com.restaurant.domain.User;
import com.restaurant.entity.OrderEntity;
import com.restaurant.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;

@RunWith(MockitoJUnitRunner.class)
public class OrderMapperTest {
    private static final Long ID = 1L;
    private static final OrderStatus ORDER_STATUS = OrderStatus.SENT;
    private static final int COST = 123;
    private static final LocalDate CREATED_AT = LocalDate.now();
    private static final Long USER_ID = 1L;
    private static final User USER = User.builder().withId(USER_ID).build();
    private static final UserEntity USER_ENTITY = UserEntity.builder().withId(USER_ID).build();


    @InjectMocks
    private OrderMapper orderMapper;

    @Test
    public void mapEntityToDomainWithNullUser() {
        final OrderEntity orderEntity = OrderEntity.builder()
                .withId(ID)
                .withStatus(ORDER_STATUS)
                .withCost(COST)
                .withCreatedAt(CREATED_AT)
                .build();

        final Order order = orderMapper.mapEntityToDomain(orderEntity);
        assertThat("Mapping id is failed", order.getId(), is(ID));
        assertThat("Mapping order status is failed", order.getStatus(), is(ORDER_STATUS));
        assertThat("Mapping cost is failed", order.getCost(), is(COST));
        assertThat("Mapping created at is failed", order.getCreatedAt(), is(CREATED_AT));
    }

    @Test
    public void mapEntityToDomainWithUser() {
        final OrderEntity orderEntity = OrderEntity.builder()
                .withId(ID)
                .withStatus(ORDER_STATUS)
                .withCost(COST)
                .withCreatedAt(CREATED_AT)
                .withUserEntity(USER_ENTITY)
                .build();

        final Order order = orderMapper.mapEntityToDomain(orderEntity);
        assertThat("Mapping id is failed", order.getId(), is(ID));
        assertThat("Mapping order status is failed", order.getStatus(), is(ORDER_STATUS));
        assertThat("Mapping cost is failed", order.getCost(), is(COST));
        assertThat("Mapping created at is failed", order.getCreatedAt(), is(CREATED_AT));
        assertThat("Mapping user is failed", order.getUser(), isA(User.class));
    }

    @Test
    public void mapDomainToEntityWithNullUser() {
        final Order order = Order.builder()
                .withId(ID)
                .withStatus(ORDER_STATUS)
                .withCost(COST)
                .withCreatedAt(CREATED_AT)
                .build();

        final OrderEntity orderEntity = orderMapper.mapDomainToEntity(order);
        assertThat("Mapping id is failed", orderEntity.getId(), is(ID));
        assertThat("Mapping order status is failed", orderEntity.getStatus(), is(ORDER_STATUS));
        assertThat("Mapping cost is failed", orderEntity.getCost(), is(COST));
        assertThat("Mapping created at is failed", orderEntity.getCreatedAt(), is(CREATED_AT));
    }

    @Test
    public void mapDomainToEntityWithUser() {
        final Order order = Order.builder()
                .withId(ID)
                .withStatus(ORDER_STATUS)
                .withCost(COST)
                .withCreatedAt(CREATED_AT)
                .withUser(USER)
                .build();

        final OrderEntity orderEntity = orderMapper.mapDomainToEntity(order);
        assertThat("Mapping id is failed", orderEntity.getId(), is(ID));
        assertThat("Mapping order status is failed", orderEntity.getStatus(), is(ORDER_STATUS));
        assertThat("Mapping cost is failed", orderEntity.getCost(), is(COST));
        assertThat("Mapping created at is failed", orderEntity.getCreatedAt(), is(CREATED_AT));
        assertThat("Mapping user entity is failed", orderEntity.getUserEntity(), isA(UserEntity.class));
    }
}