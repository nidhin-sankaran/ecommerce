package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.stereotype.Service;

public interface ProductService {
    public ProductModel getProductById(Long id);
    public void createProduct();
}
