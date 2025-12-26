package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer customer =new Customer();

    @Test
    void add_productTest() {
        assertEquals(1, customer.product_list.size());
    }
}