package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.builder.ProductMapper;
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
        return  ProductMapper.toModel(dto);

    }

    @Override
    public ProductModel createProduct(String title,
                                      String description,
                                      String price,
                                      String category,
                                      String image) {
        FakeStoreResponseDTO requestBody = new FakeStoreResponseDTO();
        requestBody.setCategory(category);
        requestBody.setDescription(description);
        requestBody.setTitle(title);
        requestBody.setPrice(price);
        requestBody.setImage(image);

        FakeStoreResponseDTO response = restTemp.postForObject("https://fakestoreapi.com/products",
                                                                   requestBody,
                                                                   FakeStoreResponseDTO.class);
        ProductModel model = ProductMapper.toModel(response);
        return  model;

    }


}
