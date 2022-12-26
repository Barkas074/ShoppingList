package org.example.shoppinglist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.example.shoppinglist.form.ProductForm;
import org.example.shoppinglist.model.Product;
import org.example.shoppinglist.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class RESTController {
    private final ProductService productService;
    
    public RESTController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping({"/", ""})
    private Iterable<Product> list() {
        return productService.getAllProducts();
    }
    
    @GetMapping("/{id}")
    private Optional<Product> getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }
    
    @PostMapping("/remove/{id}")
    private void delete(@PathVariable("id") Long id) {
        productService.remove(id);
    }
    
    @PostMapping("/update/{id}")
    private void update(@PathVariable("id") Long id) {
        productService.getById(id).ifPresent(product -> {
            product.setBought(!product.isBought());
            productService.save(product);
        });
    }
    
    @PostMapping("/add")
    private Product addProduct(@RequestBody ProductForm form) {
        return productService.add(form);
    }
}