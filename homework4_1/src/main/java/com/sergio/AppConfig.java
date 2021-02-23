package com.sergio;

import com.sergio.repository.OrderRepository;
import com.sergio.repository.ProductRepository;
import com.sergio.repository.UserRepository;
import com.sergio.service.OrderService;
import com.sergio.service.ProductService;
import com.sergio.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.sergio"})
public class AppConfig {
    @Bean
    OrderRepository orderRepository() {return new OrderRepository();}

    @Bean
    ProductRepository productRepository() {return new ProductRepository();}

    @Bean
    UserRepository userRepository() {return new UserRepository();}

    @Bean
    OrderService orderService(){return new OrderService();}

    @Bean
    UserService userService(){return new UserService();}

    @Bean
    ProductService productService(){return new ProductService();}

}






