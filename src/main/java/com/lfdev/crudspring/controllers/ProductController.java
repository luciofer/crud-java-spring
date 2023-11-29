package com.lfdev.crudspring.controllers;


import com.lfdev.crudspring.domain.product.ProductEntity;
import com.lfdev.crudspring.domain.product.ProductRepository;
import com.lfdev.crudspring.domain.product.RequestProductDTO;
import com.lfdev.crudspring.domain.product.ResponseProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.OK).body(allProducts);
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody @Valid RequestProductDTO requestProductDTO){
        ProductEntity product = new ProductEntity(requestProductDTO);
        this.productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/")
    public ResponseEntity<Object> update(@RequestBody @Valid ResponseProductDTO responseProductDTO){
        Optional<ProductEntity> productOptional= this.productRepository.findById(responseProductDTO.getId());

        if(productOptional.isPresent()) {
            ProductEntity product = productOptional.get();

            product.setName(responseProductDTO.getName());
            product.setPrice_in_cents(responseProductDTO.getPrice_in_cents());

            var updatedProduct = this.productRepository.save(product);

            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){

        var product = this.productRepository.findById(id).orElse(null);

        if (product == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");
        }

        this.productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");

    }

}
