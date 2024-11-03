// UserService.java
package com.byteme.services;

import com.byteme.models.Customer;
import com.byteme.models.VIPMembership;
import java.util.List;

public interface UserService {
    void registerCustomer(Customer customer);
    Customer getCustomer(String customerId);
    void updateCustomer(Customer customer);
    void deleteCustomer(String customerId);
    void addVIPMembership(String customerId);
    List<Customer> getAllCustomers();
    List<Customer> getVIPCustomers();
}