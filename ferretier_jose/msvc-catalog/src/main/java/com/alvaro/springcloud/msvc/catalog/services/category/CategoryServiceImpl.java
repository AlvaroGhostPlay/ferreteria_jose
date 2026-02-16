package com.alvaro.springcloud.msvc.catalog.services.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alvaro.springcloud.msvc.catalog.entities.Category;
import com.alvaro.springcloud.msvc.catalog.repositories.CategoriRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategorySercice {

    @Autowired
    private CategoriRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findByCategoryId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> getAllCategoriesPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Optional<Category> save(Category request) {
        Category category = new Category();
        category.setCategory(request.getCategory());
        category.setCategoryId(request.getCategoryId());
        categoryRepository.save(category);
        return Optional.of(category);
    }

    @Transactional
    @Override
    public Optional<Category> update(Category request, String id) {
        Optional<Category> addOptional = categoryRepository.findByCategoryId(id);
        if (addOptional.isPresent()) {
            addOptional.get().setCategory(request.getCategory());
            addOptional.get().setCategoryId(request.getCategoryId());
            return addOptional;
        }
        return Optional.empty();
    }   

    @Transactional
    @Override
    public Optional<Category> delete(String id) {
        Optional<Category> addOptional = categoryRepository.findByCategoryId(id);
        if (addOptional.isPresent()) {
            return categoryRepository.deleteByCategoryId(id);
        }
        return Optional.empty();
    }
}
