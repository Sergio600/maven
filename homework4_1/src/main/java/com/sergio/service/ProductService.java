package com.sergio.service;

import com.sergio.repository.ProductRepository;
import java.util.Map;

public class ProductService {

    public static Map<String, Double> getAllProducts(){
        Map<String, Double>products = ProductRepository.getProducts();
        return products;
    }
}
