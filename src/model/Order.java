package model;

import model.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDateTime createDate;
    private List<Product> products;
    private String orderCode;
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private Bill bill;

    public Order(List<Product> productList) {
        this.createDate = LocalDateTime.now();
        this.products = new ArrayList<>(productList);
        this.orderCode = UniqueCodeGenerator.generateOrderCode();
        this.orderStatus = OrderStatus.INITIAL;
        this.totalAmount = BigDecimal.ZERO;
        recalculateTotalAmount();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
        recalculateTotalAmount();
    }

    private void recalculateTotalAmount() {
        this.totalAmount = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        this.bill = new Bill(this); // Create bill with updated total amount
    }

    public int calculatePoints() {
        return totalAmount.multiply(BigDecimal.valueOf(0.02)).intValue();
    }

    public Bill getBill() {
        return bill;
    }

    @Override
    public String toString() {
        return "Order{" +
                "createDate=" + createDate +
                ", products=" + products +
                ", orderCode='" + orderCode + '\'' +
                ", orderStatus=" + orderStatus +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
