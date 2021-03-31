package com.grocery.catalog.controller;

import com.grocery.catalog.Product;
import com.grocery.catalog.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/consumer",consumes = "application/json",produces = "application/json")
public class CatalogController {

    private ProductRepository productRepository;

    @Autowired
    public CatalogController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping( "/products")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> productList = new ArrayList<>();
        productRepository.findAll().forEach(productList::add);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/product")
    public Product saveProducts(@RequestBody  Product product){
        return productRepository.save(product);
    }

    @PostMapping("/products")
    public ResponseEntity<List<Product>> saveBulkProducts(@RequestBody List<Product> productsList){
        List<Product> productList = new ArrayList<>();
        productRepository.saveAll(productsList).forEach(productList::add);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId){
        Optional<Product> product = productRepository.findProductsByProductId(productId);
        return product.isPresent() ?
                new ResponseEntity<>(product.get(),HttpStatus.OK):
                new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
