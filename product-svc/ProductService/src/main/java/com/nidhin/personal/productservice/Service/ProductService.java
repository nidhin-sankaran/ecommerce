package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Optional<ProductModel> getProductById(Long id);
    public ProductModel createProduct(String title,
                                      String description,
                                      String price,
                                      String category,
                                      String image);
    public List<ProductModel> getAllProducts();

}
