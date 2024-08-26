package com.nidhin.personal.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String image;
    private String category;
}
