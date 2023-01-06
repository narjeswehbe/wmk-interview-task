package com.narjes.jwt.controller;


import com.narjes.jwt.entity.Product;
import com.narjes.jwt.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    //get products in a specific category
    @GetMapping("/{id}")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
      return productService.getAllByCategory(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Product create(@RequestBody Product p) {
        return productService.create(p);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public Product update(@RequestBody Product p) {
        return productService.update(p);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }


}
