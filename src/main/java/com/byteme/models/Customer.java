// Customer.java
package com.byteme.models;

public class Customer extends User {
    private VIPMembership vipMembership;
    private Cart cart;

    public Customer(String id, String name, String email, String password) {
        super(id, name, email, password);
        this.cart = new Cart(id);
    }

    public boolean isVIP() {
        return vipMembership != null && vipMembership.isActive();
    }

    // Getters and setters
    public VIPMembership getVipMembership() { return vipMembership; }
    public void setVipMembership(VIPMembership vipMembership) { this.vipMembership = vipMembership; }
    public Cart getCart() { return cart; }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', email='" + email + "', isVIP=" + isVIP() + "}";
    }
}