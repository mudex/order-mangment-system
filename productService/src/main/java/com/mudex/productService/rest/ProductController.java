package com.mudex.productService.rest;

import com.mudex.productService.domain.Product;
import com.mudex.productService.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public List<Product> getProducts(){
        return Optional
                .of(productRepository.findAll()).get();
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        return (Product)productRepository.save(product);
    }

}
