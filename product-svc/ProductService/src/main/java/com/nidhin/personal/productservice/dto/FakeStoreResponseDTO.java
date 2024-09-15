package com.nidhin.personal.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FakeStoreResponseDTO implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String price;
    private String image;
    private String category;
}
