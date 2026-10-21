package com.example.jonathan.service;

import com.example.jonathan.model.Product;
import com.example.jonathan.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> searchByName(String name) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getName().contains(name))
                .collect(Collectors.toList());
    }

    public Product create(Product product) {
        System.out.println("Creating product: " + product);

        Product existing = productRepository.findBySku(product.getSku());
        if (existing != null) {
            throw new RuntimeException("SKU already exists");
        }

        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product existing = findById(id);

        existing.setSku(product.getSku());
        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setEan(product.getEan());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());

        return productRepository.save(existing);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product addStock(Long id, int quantity) {
        Product product = findById(id);
        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }
}
