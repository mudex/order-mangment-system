package com.mudex.productService.rest;

import com.mudex.productService.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(
                productService
                        .getAllProducts(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                productService.getProductByID(id),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PATCH)
    public ResponseEntity<?> updateProduct(@RequestBody Product product,@PathVariable("id") Long id){
        productService.updateProduct(product,id);
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        productService.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
