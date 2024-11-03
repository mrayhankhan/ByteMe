// UserServiceImpl.java
package com.byteme.services;

import com.byteme.models.Customer;
import com.byteme.models.VIPMembership;
import com.byteme.exceptions.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private Map<String, Customer> customers = new HashMap<>();

    @Override
    public void registerCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new NotFoundException("Customer not found: " + customerId);
        }
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        if (!customers.containsKey(customer.getId())) {
            throw new NotFoundException("Customer not found: " + customer.getId());
        }
        customers.put(customer.getId(), customer);
    }

    @Override
    public void deleteCustomer(String customerId) {
        if (!customers.containsKey(customerId)) {
            throw new NotFoundException("Customer not found: " + customerId);
        }
        customers.remove(customerId);
    }

    @Override
    public void addVIPMembership(String customerId) {
        Customer customer = getCustomer(customerId);
        VIPMembership vipMembership = new VIPMembership(UUID.randomUUID().toString(), customerId);
        customer.setVipMembership(vipMembership);
        updateCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public List<Customer> getVIPCustomers() {
        return customers.values().stream()
                .filter(Customer::isVIP)
                .collect(Collectors.toList());
    }
}