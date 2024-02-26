package com.mslab.serviceproduct.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mslab.serviceproduct.entity.Category;
import com.mslab.serviceproduct.entity.Product;
import com.mslab.serviceproduct.helpers.FormatterHelper;
import com.mslab.serviceproduct.service.ProductService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products;
        if (null==categoryId) {
            products =productService.listAllProduct();
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
        }
        
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> producById(@PathVariable("id") Long id) {
        if (null==id) {
            return ResponseEntity.badRequest().build();
        }        
        Product product = productService.getProduct(id);
        if (null == product) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result){

        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, FormatterHelper.formatMessage(result));
        }

        Product productCreate = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(productCreate);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        product.setId(id);
        Product productUpdated = productService.updateProduct(product);
        if (productUpdated==null) {
            return ResponseEntity.notFound().build();   
        }

        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product productDeleted = productService.deleteProduct(id);
        if (productDeleted==null) {
            return ResponseEntity.notFound().build();   
        }
        return ResponseEntity.ok(productDeleted);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable("id") Long id, @RequestParam(name = "quantity", required = true) Double quantity){
        
        Product productStockUpdated = productService.updateStock(id, quantity);
        if (productStockUpdated==null) {
            return ResponseEntity.notFound().build();   
        }

        return ResponseEntity.ok(productStockUpdated);
    }

}
