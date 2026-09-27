package com.rikkei.b1;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import com.rikkei.b1.service.FoodService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConfigApplicationTests {

    @MockBean
    private RedisConnectionFactory redisConnectionFactory;

    @MockBean
    private CacheManager cacheManager;

    @Autowired
    private FoodService foodService;

    @Test
    void testFoodService() {
        Cache mockCache = Mockito.mock(Cache.class);
        Mockito.when(cacheManager.getCache("menu")).thenReturn(mockCache);

        String menu = foodService.getMenuByRestaurant(1L);
        assertEquals("Pho Bo", menu);
    }
}
