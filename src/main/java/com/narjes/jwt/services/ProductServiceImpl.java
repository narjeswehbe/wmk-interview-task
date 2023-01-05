package com.narjes.jwt.services;

import com.narjes.jwt.entity.Product;
import com.narjes.jwt.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    @Override
    public Product create(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product update(Product p) {
        return productRepository.save(p);
    }

    @Override
    public void delete(Long id) {
        Product p = productRepository.getById(id);
        productRepository.delete(p);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> getAllByCategory(Long id) {
        return productRepository.findByCategories_Id(id);
    }
}
