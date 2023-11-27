package com.lfdev.crudspring.controllers;


import com.lfdev.crudspring.domain.product.ProductEntity;
import com.lfdev.crudspring.domain.product.ProductRepository;
import com.lfdev.crudspring.domain.product.RequestProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> ResponseEntity (@RequestBody @Valid RequestProductDTO data){
        ProductEntity product = new ProductEntity(data);
        this.productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok().body(allProducts);
    }



}
