package model;

import java.math.BigDecimal;

public class Bill {
    private Order order;
    private BigDecimal totalAmount;

    public Bill(Order order) {
        this.order = order;
        this.totalAmount = order.getTotalAmount();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "totalAmount=" + totalAmount +
                '}';
    }
}
