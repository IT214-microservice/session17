package com.rikkei.b2;

import com.rikkei.b2.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LazyLoadingCacheApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void testLazyLoadingWithCacheable() {
        assertEquals(0, productService.getFetchCount());

        // First call triggers lazy loading -> fetch from DB
        String detail1 = productService.getProductDetail(500L);
        assertEquals("ProductDetail_500", detail1);
        assertEquals(1, productService.getFetchCount());

        // Second call retrieves cached data -> no DB fetch
        String detail2 = productService.getProductDetail(500L);
        assertEquals("ProductDetail_500", detail2);
        assertEquals(1, productService.getFetchCount());
    }
}
