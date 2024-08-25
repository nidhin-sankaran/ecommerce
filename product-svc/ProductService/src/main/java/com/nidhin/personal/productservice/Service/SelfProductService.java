package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.builder.ProductMapper;
import com.nidhin.personal.productservice.dto.ProductResponseDTO;
import com.nidhin.personal.productservice.model.Category;
import com.nidhin.personal.productservice.model.ProductModel;
import com.nidhin.personal.productservice.repository.CategoryRepo;
import com.nidhin.personal.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
public class SelfProductService implements  ProductService {
    private ProductRepo productRepo;
    private CategorySvc categorySvc;
    SelfProductService(ProductRepo repo, CategorySvc categorySvc){
        this.productRepo = repo;
        this.categorySvc = categorySvc;

    }
    @Override
    public Optional<ProductModel> getProductById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public ProductModel createProduct(String title, String description, String price, String category, String image) {
        Category category1 = categorySvc.getCategoryByName(category);
        if(category == null) {
            category1 = new Category();
            category1.setCategoryName(category);
            category1 = categorySvc.saveCategory(category1);
        }
        ProductModel model = new ProductModel();
        model.setTitle(title);
        model.setCategory(category1);
        model.setPrice(Double.valueOf(price));
        ProductModel response = productRepo.save(model);
//        Category currCategory = new Category();
//        ProductModel model = ProductMapper.toModel()
    return  response;
    }

    @Override
    public List<ProductModel> getAllProducts() {
        List<ProductModel> list = productRepo.findAll();
        return list;
    }
}
