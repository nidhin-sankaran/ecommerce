package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategorySvc {
    public Optional<Category> getCategoryById(Long id);
    public  Category getCategoryByName(String name);
    public List<Category> getAllCategories();
    public Category saveCategory(Category category);
}
