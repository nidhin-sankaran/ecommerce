package com.nidhin.personal.productservice.repository;

import com.nidhin.personal.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CategoryRepo")
public interface CategoryRepo extends JpaRepository<Category,Long> {
    List<Category> findAll();
    Category findByCategoryName(String name);
    Category save(Category category);
}
