package com.alvaro.springcloud.msvc.catalog.services.category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alvaro.springcloud.msvc.catalog.entities.Category;

public interface CategorySercice {   
    List<Category> getAllCategories();
    Page<Category> getAllCategoriesPage(Pageable pageable);
    Optional<Category> getCategoryById(String id);
    Optional<Category> save(Category request);
    Optional<Category> update(Category request, String id);
    Optional<Category> delete(String id);
}