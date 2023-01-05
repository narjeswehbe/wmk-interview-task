package com.narjes.jwt.services;

import com.narjes.jwt.entity.Category;


import java.util.List;


public interface CategoryService {
    Category create(Category c );
    Category update(Category c);
    void delete(Long id);
    Category get(Long id);
    List<Category> getAll();




}
