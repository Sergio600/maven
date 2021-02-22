package com.sergio.service;

import com.sergio.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductService {

    public static Map<String, Double> getAllProducts(){
        Map<String, Double>products = ProductRepository.getProducts();
        return products;
    }
}
