package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.model.Category;
import com.nidhin.personal.productservice.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CategorSelfService")
public class CategorSelfService implements CategorySvc {
    private CategoryRepo categoryRepo;

    CategorSelfService(@Qualifier("CategoryRepo") CategoryRepo repo) {
        this.categoryRepo = repo;
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepo.findByCategoryName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }
}
