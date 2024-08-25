package com.nidhin.personal.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseModel implements Serializable {
    private  String categoryName;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<ProductModel> products;
}
