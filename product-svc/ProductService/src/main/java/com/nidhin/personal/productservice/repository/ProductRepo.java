package com.nidhin.personal.productservice.repository;

import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductModel,Long> {
    List<ProductModel> findAllById(Iterable<Long> longs);
    ProductModel findById(long id);
    ProductModel save(ProductModel productModel);
}
