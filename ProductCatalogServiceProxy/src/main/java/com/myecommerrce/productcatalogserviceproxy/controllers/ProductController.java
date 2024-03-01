package com.myecommerrce.productcatalogserviceproxy.controllers;

import com.myecommerrce.productcatalogserviceproxy.dtos.ProductDto;
import com.myecommerrce.productcatalogserviceproxy.models.Category;
import com.myecommerrce.productcatalogserviceproxy.models.Product;
import com.myecommerrce.productcatalogserviceproxy.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    IProductService productService ;
    

    @GetMapping("")
    public List<Product> getProducts(){
        List<Product> products = productService.getProducts();
        //return "Returning list of all the products" ;
        return products;
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId){
        try {
            if (productId < 1 || productId>50)
                throw new IllegalArgumentException("Product id is Incorrect");
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public Product createProduct(@RequestBody ProductDto productDto){
        Product product = getProduct(productDto);
        return productService.createProduct(product);
    }

    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        return product;
    }

    private Product getProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
//        Category category = new Category();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }
}