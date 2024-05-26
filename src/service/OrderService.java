package service;

import model.Order;
import model.Product;

import java.util.List;

public class OrderService {
    private static OrderService instance;

    private OrderService() {
        // private constructor to prevent instantiation
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    public Order createOrder(List<Product> products) {
        return new Order(products);
    }

    public void addProductToOrder(Order order, Product product, int quantity) {
        order.addProduct(product, quantity);
    }

    public void printOrderDetails(Order order) {
        System.out.println("Order Details: " + order);
        System.out.println("Bill: " + order.getBill());
    }
}
