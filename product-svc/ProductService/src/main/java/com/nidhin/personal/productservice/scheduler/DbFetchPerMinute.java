package com.nidhin.personal.productservice.scheduler;

import com.nidhin.personal.productservice.Service.ProductService;
import com.nidhin.personal.productservice.model.ProductModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DbFetchPerMinute {
    private ProductService productService;
    public DbFetchPerMinute(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }
    @Scheduled(cron = "0 * * * * *")
    public  void execute(){
        Optional<ProductModel> product = productService.getProductById(3L);
        System.out.println("product title" + product.get().getTitle());
    }

}
