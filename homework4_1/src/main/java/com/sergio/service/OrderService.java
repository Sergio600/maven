package com.sergio.service;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import com.sergio.domain.User;
import com.sergio.exception.InvalidArgumentException;
import com.sergio.repository.OrderRepository;
import com.sergio.repository.ProductRepository;
import com.sergio.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates order or returns saved order.
     *
     * @param user .
     * @return created order.
     */
    public static Order createOrGetOrder(User user) {
        if (user == null) {
            throw new InvalidArgumentException("Name can't be null");
        }
        Order order = OrderRepository.getOrder(user);
        return order;
    }

    /**
     * Adds product to order.
     *
     * @param order            order id.
     * @param selectedProducts string array of product keys from product map.
     * @return order with saved order.
     */
    public static Order addProducts(Order order, String[] selectedProducts) {
        if (order == null || selectedProducts == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        String productTitle = selectedProducts[0];
        int productId = ProductRepository.getProductIdByTitle(productTitle);

        Product product = new Product();
        product.setId(productId);
        product.setName(productTitle);
        product.setPrice(ProductService.getAllProducts().get(productTitle));

        OrderRepository.addProduct(product, order);
        order.setProducts(OrderRepository.getProductsByOrder(order));
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
    public static Order removeProduct(Order order, int productId) {
        if (order == null) {
            throw new InvalidArgumentException("Arguments cant be null");
        }

        OrderRepository.removeProduct(productId, order);
        order.setProducts(OrderRepository.getProductsByOrder(order));
        updateOrderTotalPrice(order);

        return order;
    }

    /**
     * Calculates and update total price of order.
     *
     * @param order order for calculation.
     * @return total price.
     */
    public static Order updateOrderTotalPrice(Order order) {
        double totalPrice = 0;
        for (Product product : order.getProducts()) {
            totalPrice += product.getPrice();
        }
        OrderRepository.updateTotalPrice(totalPrice, order);

        return order;
    }


//    public static void printProducts(Order order) {
//        System.out.println("Количество продуктов: " + order.getProducts().size());
//        for (Product product : order.getProducts()) {
//            System.out.print(product.getName() + " ");
//        }
//    }
}
