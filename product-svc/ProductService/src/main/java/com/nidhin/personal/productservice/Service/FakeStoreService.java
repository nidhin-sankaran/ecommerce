package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import com.nidhin.personal.productservice.model.Category;
import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreService implements  ProductService {

    @Autowired
    private RestTemplate restTemp;

    @Override
    public ProductModel getProductById(Long id) {
        ResponseEntity<FakeStoreResponseDTO> response = restTemp.getForEntity("https://fakestoreapi.com/products/" + id,
                                                                              FakeStoreResponseDTO.class);
        FakeStoreResponseDTO dto = response.getBody();
        return  toModel(dto);

    }

    @Override
    public void createProduct() {

    }

    public  ProductModel toModel(FakeStoreResponseDTO dto) {
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
