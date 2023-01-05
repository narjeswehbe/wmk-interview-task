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

    @GetMapping("/{id}")
    public List<Product> getProductsByCategory(@PathVariable Long id) {
      return productService.getAllByCategory(id);
    }
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/create")
    public Product create(@RequestBody Product p) {
        System.out.println("creating product");
        return productService.create(p);
    }
    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping("/update")
    public Product update(@RequestBody Product p) {
        return productService.update(p);
    }
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }


}
