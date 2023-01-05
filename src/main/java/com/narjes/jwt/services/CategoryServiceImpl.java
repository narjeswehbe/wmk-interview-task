package com.narjes.jwt.services;

import com.narjes.jwt.entity.Category;
import com.narjes.jwt.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public Category create(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public Category update(Category c) {
        return categoryRepository.save(c);
    }

    @Override
    public void delete(Long id) {
        Category c = categoryRepository.getById(id);
        categoryRepository.delete(c);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.getById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
