package com.nidhin.personal.productservice.builder;

import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import com.nidhin.personal.productservice.dto.ProductResponseDTO;
import com.nidhin.personal.productservice.model.Category;
import com.nidhin.personal.productservice.model.ProductModel;

public class ProductMapper {
    public static ProductResponseDTO toProductResponseDTO(ProductModel product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setCategory(product.getCategory());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        return dto;
    }

    public static ProductModel toModel(FakeStoreResponseDTO dto) {
        Category category = new Category();
        ProductModel model = new ProductModel();
        category.setCategoryName(dto.getCategory());
        model.setId(dto.getId());
        model.setCategory(category);
        model.setDescription(dto.getDescription());
        model.setTitle(dto.getTitle());
        model.setPrice(Double.valueOf(dto.getPrice()));
        model.setImageUrl(dto.getImage());
        return model;
    }
}
