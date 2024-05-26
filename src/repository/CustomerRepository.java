package repository;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static CustomerRepository instance;
    private List<Customer> customerList;

    private CustomerRepository() {
        customerList = new ArrayList<>();
    }

    public static CustomerRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRepository();
        }
        return instance;
    }

    public void createCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }
}
