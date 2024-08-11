package com.nidhin.personal.productservice.model;

import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;

}
