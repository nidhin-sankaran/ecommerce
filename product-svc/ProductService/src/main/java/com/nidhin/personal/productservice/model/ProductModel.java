package com.nidhin.personal.productservice.model;

import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductModel extends BaseModel implements Serializable {
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
