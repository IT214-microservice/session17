package com.rikkei.b2.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductService {
    private final AtomicInteger fetchCount = new AtomicInteger(0);

    @Cacheable(value = "products", key = "#id")
    public String getProductDetail(Long id) {
        fetchCount.incrementAndGet();
        return "ProductDetail_" + id;
    }

    public int getFetchCount() {
        return fetchCount.get();
    }
}
