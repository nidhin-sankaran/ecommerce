package com.nidhin.personal.productservice.repository;

import com.nidhin.personal.productservice.model.Category;
import com.nidhin.personal.productservice.model.ProductModel;
import com.nidhin.personal.productservice.repository.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel, Long> {
    List<ProductModel> findAllById(Iterable<Long> longs);

    ProductModel findById(long id);

    ProductModel save(ProductModel productModel);

    List<ProductModel> findAllByCategory_CategoryNameEquals(String category);

    @Query("select p from ProductModel p join Category c on p.category.id = c.id where p.title = :title and c.categoryName = :category")
    List<ProductModel> findAllProductsByTitleAndCategory(String title, String category);

    @Query("select p.id, p.title, p.description,p.price from ProductModel  p where p.title = :title and p.price = :price and p.id = :id")
    ProductProjection findProductByTitleAndIdAndPrice(String title, Long id, Double price);
}
