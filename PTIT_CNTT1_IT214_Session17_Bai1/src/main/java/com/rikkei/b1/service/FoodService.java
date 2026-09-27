package com.rikkei.b1.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FoodService {
    private final Map<Long, String> foodDb = new ConcurrentHashMap<>();

    public FoodService() {
        foodDb.put(1L, "Pho Bo");
        foodDb.put(2L, "Bun Cha");
    }

    @Cacheable(value = "menu", key = "#restaurantId")
    public String getMenuByRestaurant(Long restaurantId) {
        return foodDb.getOrDefault(restaurantId, "Empty Menu");
    }
}
