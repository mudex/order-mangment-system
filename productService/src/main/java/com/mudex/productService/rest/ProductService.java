package com.mudex.productService.rest;

import com.mudex.productService.domain.Product;
import com.mudex.productService.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository
                .findAll();
    }

    public Product getProductByID(Long productId) {
        return productRepository
                .findById(productId)
                .orElse(null);
    }

    public void updateProduct(Product product , Long id){
        Optional<Product> cached = productRepository.findById(id);
        if(cached.isPresent()){
            productRepository.save(mapChangedFields(cached.get(),product));
        }
        else
            throw new CustomRestClientException(
                    String.format("ID %d NOT FOUND",id.intValue()),
                    HttpStatus.NOT_FOUND);
    }

    public void createProduct(Product product) {
        productRepository.save(product);
    }

    private Product mapChangedFields(Product dst, Product src) {

        if(!StringUtils.isEmpty(src.getDescription())){
            dst.setDescription(src.getDescription());
        }
        if(!StringUtils.isEmpty(src.getName())){
            dst.setName(src.getName());
        }
        if(!StringUtils.isEmpty(src.getSku())){
            dst.setSku(src.getSku());
        }

        return dst;
    }
}
