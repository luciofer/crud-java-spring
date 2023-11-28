package com.lfdev.crudspring.controllers;


import com.lfdev.crudspring.domain.product.ProductEntity;
import com.lfdev.crudspring.domain.product.ProductRepository;
import com.lfdev.crudspring.domain.product.RequestProductDTO;
import com.lfdev.crudspring.domain.product.ResponseProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/")
    public ResponseEntity<Object> getProducts(){
        var allProducts = productRepository.findAll();
        return ResponseEntity.ok().body(allProducts);
    }

    @PostMapping("/")
    public ResponseEntity<Object> ResponseEntity (@RequestBody @Valid RequestProductDTO requestProductDTO){
        ProductEntity product = new ProductEntity(requestProductDTO);
        this.productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity<Object> update(@RequestBody @Valid ResponseProductDTO responseProductDTO){
        Optional<ProductEntity> productOptional= this.productRepository.findById(responseProductDTO.getId());

        if(productOptional.isPresent()) {
            ProductEntity product = productOptional.get();

            product.setName(responseProductDTO.getName());
            product.setPrice_in_cents(responseProductDTO.getPrice_in_cents());

            var updatedProduct = this.productRepository.save(product);

            return ResponseEntity.ok().body(updatedProduct);
        }

        return ResponseEntity.badRequest().body("id not found");
    }


}
