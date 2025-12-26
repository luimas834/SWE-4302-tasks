package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order=new Order();
    @Test
    void addProductTest() {
        Product p=new Muffin();
        order.addProduct(p);
        assertEquals(1,order.products.size());
    }

    @Test
    void removeProduct() {
    }

    @Test
    void order_items() {
    }

    @Test
    void getTotal_cost() {
    }
}