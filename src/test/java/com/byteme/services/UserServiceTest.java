// UserServiceTest.java
package com.byteme.services;

import com.byteme.models.Customer;
import com.byteme.models.VIPMembership;
import com.byteme.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserServiceTest {
    private UserService userService;
    private Customer testCustomer;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl();
        testCustomer = new Customer("test1", "Test User", "test@example.com", "password");
        userService.registerCustomer(testCustomer);
    }

    @Test
    void testRegisterCustomer() {
        Customer newCustomer = new Customer("test2", "New User", "new@example.com", "password");
        userService.registerCustomer(newCustomer);
        Customer retrieved = userService.getCustomer("test2");
        assertEquals(newCustomer.getName(), retrieved.getName());
    }

    @Test
    void testGetCustomer() {
        Customer retrieved = userService.getCustomer("test1");
        assertNotNull(retrieved);
        assertEquals(testCustomer.getName(), retrieved.getName());
    }

    @Test
    void testGetCustomerNotFound() {
        assertThrows(NotFoundException.class, () -> {
            userService.getCustomer("nonexistent");
        });
    }

    @Test
    void testUpdateCustomer() {
        testCustomer.setName("Updated Name");
        userService.updateCustomer(testCustomer);
        Customer retrieved = userService.getCustomer("test1");
        assertEquals("Updated Name", retrieved.getName());
    }

    @Test
    void testDeleteCustomer() {
        userService.deleteCustomer("test1");
        assertThrows(NotFoundException.class, () -> {
            userService.getCustomer("test1");
        });
    }

    @Test
    void testAddVIPMembership() {
        userService.addVIPMembership("test1");
        Customer customer = userService.getCustomer("test1");
        assertTrue(customer.isVIP());
    }

    @Test
    void testGetAllCustomers() {
        Customer secondCustomer = new Customer("test2", "Second User", "second@example.com", "password");
        userService.registerCustomer(secondCustomer);
        List<Customer> customers = userService.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    void testGetVIPCustomers() {
        Customer vipCustomer = new Customer("vip1", "VIP User", "vip@example.com", "password");
        userService.registerCustomer(vipCustomer);
        userService.addVIPMembership("vip1");
        
        List<Customer> vipCustomers = userService.getVIPCustomers();
        assertEquals(1, vipCustomers.size());
        assertTrue(vipCustomers.get(0).isVIP());
    }
}