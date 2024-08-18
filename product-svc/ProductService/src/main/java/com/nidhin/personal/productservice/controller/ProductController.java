package com.nidhin.personal.productservice.controller;

import com.nidhin.personal.productservice.Service.FakeStoreService;
import com.nidhin.personal.productservice.builder.ProductMapper;
import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import com.nidhin.personal.productservice.dto.ProductResponseDTO;
import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private FakeStoreService svc;

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProudctById(@PathVariable(name = "id") Long id) {
        ProductModel response = svc.getProductById(id);
        ProductResponseDTO dto = ProductMapper.toProductResponseDTO(response);
        return  new ResponseEntity<ProductResponseDTO>(dto, HttpStatus.OK);

    }

    @GetMapping("/products")
    public  void getAllProducts() {

    }

    @PostMapping("/product")
    public  ResponseEntity<?> create(@RequestBody FakeStoreResponseDTO dto){
        ProductModel model =
        svc.createProduct(dto.getTitle(),
                          dto.getDescription(),
                          dto.getPrice(),
                          dto.getCategory(),
                          dto.getImage());
        ProductResponseDTO response = ProductMapper.toProductResponseDTO(model);
        return new ResponseEntity<ProductResponseDTO>(response,
                                                      HttpStatus.CREATED);

    }
    @PutMapping("/product/{id}")
    public  void update(@PathVariable(name = "id") Long id){

    }
    @DeleteMapping("product/{id}")
    public  void delete(@PathVariable(name = "id") Long id) {

    }
}
