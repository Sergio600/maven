package com.sergio.service;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.ProductRepository;
import com.sergio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;



    /**
     * Creates order or returns saved order.
     *
     * @param user .
     * @return created order.
     */
    public Order createOrGetOrder(User user) {
        if (user == null) {
            throw new InvalidArgumentException("Name can't be null");
        }
        Order order = orderRepository.getOrder(user);
        return order;
    }

    /**
     * Adds product to order.
     *
     * @param order            order id.
     * @param selectedProducts string array of product keys from product map.
     * @return order with saved order.
     */
    public Order addProducts(Order order, String[] selectedProducts) {
        if (order == null || selectedProducts == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        String productTitle = selectedProducts[0];
        int productId = productRepository.getProductIdByTitle(productTitle);

        Product product = new Product();
        product.setId(productId);
        product.setName(productTitle);
        product.setPrice(productService.getAllProducts().get(productTitle));

        orderRepository.addProduct(product, order);
        order.setProducts(orderRepository.getProductsByOrder(order));
        updateOrderTotalPrice(order);

        return order;
    }

    /**
     * Removes product from order
     *
     * @param order
     * @param productId
     * @return order with removed product
     */
    public Order removeProduct(Order order, int productId) {
        if (order == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        orderRepository.removeProduct(productId, order);
        order.setProducts(orderRepository.getProductsByOrder(order));
        updateOrderTotalPrice(order);

        return order;
    }

    /**
     * Calculates and update total price of order.
     *
     * @param order order for calculation.
     * @return total price.
     */
    public Order updateOrderTotalPrice(Order order) {
        double totalPrice = 0;
        for (Product product : order.getProducts()) {
            totalPrice += product.getPrice();
        }
        orderRepository.updateTotalPrice(totalPrice, order);

        return order;
    }


//    public static void printProducts(Order order) {
//        System.out.println("Количество продуктов: " + order.getProducts().size());
//        for (Product product : order.getProducts()) {
//            System.out.print(product.getName() + " ");
//        }
//    }
}
