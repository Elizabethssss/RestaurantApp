package com.restaurant.service.mapper;

import com.restaurant.domain.Lunch;
import com.restaurant.entity.LunchEntity;

public class LunchMapper implements Mapper<LunchEntity, Lunch> {
    @Override
    public Lunch mapEntityToDomain(LunchEntity entity) {
        return Lunch.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withDescription(entity.getDescription())
                .withTimeFrom(entity.getTimeFrom())
                .withTimeTo(entity.getTimeTo())
                .withImg(entity.getImg())
                .withLunchType(entity.getLunchType())
                .build();
    }

    @Override
    public LunchEntity mapDomainToEntity(Lunch item) {
        return LunchEntity.builder()
                .withId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .withTimeFrom(item.getTimeFrom())
                .withTimeTo(item.getTimeTo())
                .withImg(item.getImg())
                .withLunchType(item.getLunchType())
                .build();
    }
}
