package com.myecommerrce.productcatalogserviceproxy.controllers;

import com.myecommerrce.productcatalogserviceproxy.dtos.CategoryDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {
    @GetMapping("/categories")
    public String getCategories(){
        return "Returning list of Categories:";
    }

    @GetMapping("/categories/{id}")
    public String getCategory(@PathVariable("id") String categoryID){
        return "Returning the category with Id : " + categoryID;
    }

    @PostMapping("/categories")
    public String createCategory(@RequestBody CategoryDto categoryDto){
        return "Creating Category : " + categoryDto ;
    }
    @PatchMapping("/categories")
    public String updateCategory(@RequestBody CategoryDto categoryDto){
        return "Updating Category: " + categoryDto;
    }

}
