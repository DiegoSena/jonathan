package com.example.jonathan.service;

import com.example.jonathan.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void shouldCreateProduct() {
        Product product = new Product();
        product.setSku("SKU-001");
        product.setName("Notebook");
        product.setDescription("Notebook 16GB RAM");
        product.setEan(7891234567895L);
        product.setPrice(4999.90);
        product.setStock(10);

        Product saved = productService.create(product);

        assertNotNull(saved);
    }
}
