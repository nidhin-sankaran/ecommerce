package com.nidhin.personal.productservice.Service;

import com.nidhin.personal.productservice.builder.ProductMapper;
import com.nidhin.personal.productservice.dto.ProductResponseDTO;
import com.nidhin.personal.productservice.model.Category;
import com.nidhin.personal.productservice.model.ProductModel;
import com.nidhin.personal.productservice.repository.CategoryRepo;
import com.nidhin.personal.productservice.repository.ProductRepo;
import com.nidhin.personal.productservice.repository.projection.ProductProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        if(category1 == null) {
            category1 = new Category();
            category1.setCategoryName(category);
            category1.setCreatedAt(new Date());
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

    @Override
    public List<ProductModel> getAllProductsByCategory(String category) {
        return productRepo.findAllByCategory_CategoryNameEquals(category);
    }

    @Override
    public List<ProductModel> getAllProductsByTitleAndCategory(String title, String category) {
        return productRepo.findAllProductsByTitleAndCategory(title,category);
    }

    @Override
    public ProductModel findProductProjectionByIdTitleAndPrice(Long id, String title, Double price) {
        ProductProjection projection = productRepo.findProductByTitleAndIdAndPrice(title,id,price);
        ProductModel model = new ProductModel();
        model.setPrice(projection.getPrice());
        model.setId(projection.getId());
        model.setTitle(projection.getTitle());
        return model;
    }


}
