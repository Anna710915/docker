package com.example.onliner.service.impl;

import com.example.onliner.domain.Category;
import com.example.onliner.domain.Product;
import com.example.onliner.domain.exception.CustomNotFoundException;
import com.example.onliner.repository.ProductRepository;
import com.example.onliner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void create(Product product, List<Category> categoryList, long userId) {
        long productId = productRepository.create(product, userId);
        insertKeys(productId, categoryList);
    }

    @Override
    @Transactional
    public List<Product> retrieveAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    @Transactional
    public Product retrieveById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isEmpty()){
            throw new CustomNotFoundException("Not found product with id = " + id);
        }
        return optional.get();
    }

    @Override
    @Transactional
    public void delete(long id) {
        if(!productRepository.delete(id)){
            throw new CustomNotFoundException("Not found product with id = " + id);
        }
    }

    private void insertKeys(long productId, List<Category> categoryList){
        for(Category category : categoryList){
            productRepository.insertProductCategoryKeys(productId, category.getId());
        }
    }
}
