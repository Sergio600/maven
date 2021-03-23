package com.sergio.repository;

import com.sergio.domain.Order;
import com.sergio.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Repository class that emulate data base and has some repository methods.
 */
@Repository
@Transactional
public class OrderRepository {

    private LocalSessionFactoryBean localSessionFactoryBean;

    @Autowired
    public OrderRepository(LocalSessionFactoryBean localSessionFactoryBean){
        this.localSessionFactoryBean = localSessionFactoryBean;
    }

    public Order save (Order order){
        localSessionFactoryBean.getObject().getCurrentSession().save(order);
        return order;
    }

    public void updateOrder (Order order){
        localSessionFactoryBean.getObject().getCurrentSession().update(order);
    }
}