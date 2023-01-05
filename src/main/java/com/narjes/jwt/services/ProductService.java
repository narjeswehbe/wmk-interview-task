package com.narjes.jwt.services;

import com.narjes.jwt.entity.Product;


import java.util.List;

public interface ProductService {
    Product create(Product p );
    Product update(Product p);
    void delete(Long id);
    Product get(Long id);
    List<Product> getAllByCategory(Long id); //category id




}
