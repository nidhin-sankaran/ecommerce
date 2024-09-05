package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.builder.ProductMapper;
import com.nidhin.personal.productservice.dto.FakeStoreResponseDTO;
import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("FakeStoreService")
public class FakeStoreService implements ProductService {

    @Autowired
    private RestTemplate restTemp;

    @Override
    public Optional<ProductModel> getProductById(Long id) {
        ResponseEntity<FakeStoreResponseDTO> response = restTemp.getForEntity("https://fakestoreapi.com/products/" + id,
                FakeStoreResponseDTO.class);
        FakeStoreResponseDTO dto = response.getBody();
        if (dto == null) {
            return null;
        }
        return Optional.of(ProductMapper.toModel(dto));

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
        return model;

    }

    @Override
    public List<ProductModel> getAllProducts() {
        ResponseEntity<FakeStoreResponseDTO[]> productsDTOs = restTemp.getForEntity("https://fakestoreapi.com/products", FakeStoreResponseDTO[].class);
        List<FakeStoreResponseDTO> results = List.of(productsDTOs.getBody());
        List<ProductModel> products = results.stream().map(ProductMapper::toModel).collect(Collectors.toList());
        return products;
    }

    @Override
    public List<ProductModel> getAllProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public List<ProductModel> getAllProductsByTitleAndCategory(String title, String category) {
        return List.of();
    }

    @Override
    public ProductModel findProductProjectionByIdTitleAndPrice(Long id, String title, Double price) {
        return null;
    }

    @Override
    public Page<ProductModel> findProductsByPageNumberAndSize(int pageNumber, int pageSize) {

        return null;
    }


}
