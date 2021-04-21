package com.sergio.service;

import com.sergio.domain.Product;
import com.sergio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = productRepository.getProducts();
        return products;
    }
}
