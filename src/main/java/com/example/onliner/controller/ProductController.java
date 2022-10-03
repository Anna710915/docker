package com.example.onliner.controller;


import com.example.onliner.controller.dto.ProductDto;
import com.example.onliner.domain.Product;
import com.example.onliner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void create(@Valid @RequestBody ProductDto productDto) {
        productService.create(productBuilder(productDto), productDto.getCategories(), 1);
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAll(){
        return productDtoListBuilder(productService.retrieveAll());
    }
    
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getById(@PathVariable long id) {
        return productDtoBuilder(productService.retrieveById(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        productService.delete(id);
    }

    private List<ProductDto> productDtoListBuilder(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products){
            productDtoList.add(productDtoBuilder(product));
        }
        return productDtoList;
    }

    private Product productBuilder(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        return product;
    }

    private ProductDto productDtoBuilder(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
