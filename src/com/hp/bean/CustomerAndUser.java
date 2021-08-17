package com.hp.bean;

public class CustomerAndUser {
    private Customer customer;
    private User user;

    @Override
    public String toString() {
        return "CustomerAndUser{" +
                "customer=" + customer +
                ", user=" + user +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
