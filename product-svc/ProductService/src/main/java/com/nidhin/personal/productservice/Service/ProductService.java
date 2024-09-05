package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.model.ProductModel;
import com.nidhin.personal.productservice.repository.projection.ProductProjection;
import org.springframework.data.domain.Page;

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

    public List<ProductModel> getAllProductsByCategory(String category);

    public List<ProductModel> getAllProductsByTitleAndCategory(String title, String category);

    public ProductModel findProductProjectionByIdTitleAndPrice(Long id, String title, Double price);
    public Page<ProductModel> findProductsByPageNumberAndSize(int pageNumber, int pageSize);
}
