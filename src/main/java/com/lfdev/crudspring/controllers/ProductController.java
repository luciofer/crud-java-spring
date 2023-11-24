package com.lfdev.crudspring.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/")
    public ResponseEntity<String> getProducts(){
        var test = "Hello";
        return ResponseEntity.ok().body(test);
    }


}
