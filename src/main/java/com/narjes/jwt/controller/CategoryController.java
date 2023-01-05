package com.narjes.jwt.controller;

import com.narjes.jwt.entity.Category;
import com.narjes.jwt.services.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/")
    public List<Category> getAll() {
        return categoryService.getAll();
    }
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
      return categoryService.get(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public Category create(@RequestBody Category c) {
        return categoryService.create(c);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public Category update(@RequestBody Category c) {
        return categoryService.update(c);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }


}
