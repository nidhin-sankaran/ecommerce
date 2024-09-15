package com.nidhin.personal.productservice.controller;

import com.nidhin.personal.productservice.Service.FakeStoreService;
import com.nidhin.personal.productservice.Service.ProductService;
import com.nidhin.personal.productservice.builder.ProductMapper;
import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import com.nidhin.personal.productservice.dto.ProductResponseDTO;
import com.nidhin.personal.productservice.exceptions.InvalidProductIdException;
import com.nidhin.personal.productservice.exceptions.ProductNotFoundException;
import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private ProductService svc;

    public ProductController(@Qualifier("SelfProductService") ProductService svc) {
        this.svc = svc;
    }

    @GetMapping("/product/{id}")
    @Cacheable(value = "product", key = "#id")
    public ResponseEntity<?> getProudctById(@PathVariable(name = "id") Long id) throws InvalidProductIdException, ProductNotFoundException {
        if (id == null) {
            throw new InvalidProductIdException("Pdoduct id is null");
        }
        Optional<ProductModel> response = svc.getProductById(id);
        if (!response.isPresent()) {
            throw new ProductNotFoundException("not found");
        }
        ProductResponseDTO dto = ProductMapper.toProductResponseDTO(response.get());
        return new ResponseEntity<ProductResponseDTO>(dto, HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductModel> products = svc.getAllProducts();
        List<ProductResponseDTO> result = products.stream().map(ProductMapper::toProductResponseDTO).collect(Collectors.toList());
        return new ResponseEntity<List<ProductResponseDTO>>(result, HttpStatus.OK);
    }

    @GetMapping("/products/category/{name}")
    public ResponseEntity<?> getAllProductsByCategory(@PathVariable(name = "name") String name) {
        List<ProductModel> products = svc.getAllProductsByCategory(name);
        List<ProductResponseDTO> result = products.stream().map(ProductMapper::toProductResponseDTO).collect(Collectors.toList());
        return new ResponseEntity<List<ProductResponseDTO>>(result, HttpStatus.OK);
    }

    @GetMapping("/products/{title}")
    public ResponseEntity<?> getAllProductsByTitleAndCategory(@PathVariable(name = "title") String title, @RequestParam(name = "category") String category) {
        List<ProductModel> products = svc.getAllProductsByTitleAndCategory(title, category);
        List<ProductResponseDTO> result = products.stream().map(ProductMapper::toProductResponseDTO).collect(Collectors.toList());
        return new ResponseEntity<List<ProductResponseDTO>>(result, HttpStatus.OK);
    }

    @GetMapping("product/projection/{id}")
    public ResponseEntity<?> getProductByTitleDescriptionAndId(@PathVariable(name = "id") Long id, @RequestParam(name = "title") String title, @RequestParam(name = "price") Double price) throws ProductNotFoundException {
        ProductModel model = svc.findProductProjectionByIdTitleAndPrice(id, title, price);
        if (model == null) {
            throw new ProductNotFoundException("not found");
        }
        ProductResponseDTO dto = ProductMapper.toProductResponseDTO(model);
        return new ResponseEntity<ProductResponseDTO>(dto, HttpStatus.OK);

    }

    @PostMapping("/product")
    @CachePut(value = "product", key = "#result.body.id", unless = "#result.body.id == null")
    public ResponseEntity<?> create(@RequestBody FakeStoreResponseDTO dto) {
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

    @GetMapping("/products/{page}/{size}")
    public ResponseEntity<?> getPaginatedProducts(@PathVariable(name = "page") Integer page,
                                                  @PathVariable(name = "size") Integer size) {
        Page<ProductModel> response = svc.findProductsByPageNumberAndSize(page,size);
        List<ProductModel> products = response.getContent();
        List<ProductResponseDTO> res = products.stream()
                                               .map(ProductMapper::toProductResponseDTO)
                                               .collect(Collectors.toList());
        return  new ResponseEntity<List<ProductResponseDTO>> (res,HttpStatus.OK);

    }

    @PutMapping("/product/{id}")
    public void update(@PathVariable(name = "id") Long id) {

    }

    @DeleteMapping("product/{id}")
    @CacheEvict(value = "product", key = "id")
    public void delete(@PathVariable(name = "id") Long id) {

    }
}
