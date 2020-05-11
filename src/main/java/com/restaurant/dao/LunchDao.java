package com.restaurant.dao;

import com.restaurant.entity.LunchEntity;

import java.util.Map;

/**
 * Lunch DAO class which contains methods interaction with `lunches` table in DB.
 */

public interface LunchDao extends PageableDao<LunchEntity> {
    Map<LunchEntity, Integer> getLunchesByOrderId(Long orderId);
}
