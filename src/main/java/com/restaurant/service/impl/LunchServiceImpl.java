package com.restaurant.service.impl;

import com.restaurant.dao.LunchDao;
import com.restaurant.dao.Page;
import com.restaurant.domain.Lunch;
import com.restaurant.entity.LunchEntity;
import com.restaurant.service.LunchService;
import com.restaurant.service.mapper.Mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LunchServiceImpl implements LunchService {
    private final LunchDao lunchDao;
    private final Mapper<LunchEntity, Lunch> lunchMapper;

    public LunchServiceImpl(LunchDao lunchDao, Mapper<LunchEntity, Lunch> lunchMapper) {
        this.lunchDao = lunchDao;
        this.lunchMapper = lunchMapper;
    }


    @Override
    public List<Lunch> getLunchesByType(String lunchType, Page page) {
        return lunchDao.findAll(lunchType, page).stream()
                .map(lunchMapper::mapEntityToDomain).collect(Collectors.toList());
    }

    @Override
    public int count(String lunchType) {
        return lunchDao.count(lunchType);
    }

    @Override
    public Optional<Lunch> getLunchById(Long lunchId) {
        return lunchDao.findById(lunchId).map(lunchMapper::mapEntityToDomain);
    }

    @Override
    public Map<Lunch, Integer> getLunchesByOrderId(Long orderId) {
        Map<LunchEntity, Integer> entityMap = lunchDao.getLunchesByOrderId(orderId);
        Map<Lunch, Integer> lunchMap = new LinkedHashMap<>();
        for (Map.Entry<LunchEntity, Integer> entry : entityMap.entrySet()) {
            lunchMap.put(lunchMapper.mapEntityToDomain(entry.getKey()), entry.getValue());
        }
        return lunchMap;
    }
}
