package service;

import model.Customer;
import model.Order;
import model.enums.AccountType;
import repository.CustomerRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class CustomerService {

    private static CustomerService instance;
    private CustomerRepository customerRepository;

    private CustomerService() {
        customerRepository = CustomerRepository.getInstance();
    }

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void save(String name, String surname, String email, String password) {
        if (emailExists(email)) {
            throw new IllegalArgumentException("Email already in use: " + email);
        }
        String hashedPassword = hashPassword(password);
        Customer customer1 = new Customer(name, surname, email, hashedPassword);
        customerRepository.createCustomer(customer1);
    }

    public void changeAccountTypeByCredit(String email) {
        Optional<Customer> foundCustomer = getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            Customer customer = foundCustomer.get();
            int credit = customer.getCredit();

            if (credit >= 4000) {
                customer.setAccountType(AccountType.PLATINUM);
            } else if (credit >= 2000) {
                customer.setAccountType(AccountType.GOLD);
            } else if (credit >= 1000) {
                customer.setAccountType(AccountType.SILVER);
            } else {
                customer.setAccountType(AccountType.STANDARD);
            }
        }
    }

    public void placeOrder(String email, Order order) {
        Optional<Customer> foundCustomer = getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            Customer customer = foundCustomer.get();
            int currentCredit = customer.getCredit() != null ? customer.getCredit() : 0;
            int pointsEarned = order.calculatePoints();

            customer.setCredit(currentCredit + pointsEarned);
            customer.addOrder(order);
            changeAccountTypeByCredit(email);
        } else {
            throw new IllegalArgumentException("Customer not found with email: " + email);
        }
    }

    private boolean emailExists(String email) {
        return getCustomerList().stream()
                .anyMatch(customer -> customer.getEmail().equals(email));
    }

    private String hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            sr.nextBytes(salt);

            // Combine the salt and the password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedBytes = md.digest(password.getBytes());

            // Apply SHA-512 for additional security
            md = MessageDigest.getInstance("SHA-512");
            hashedBytes = md.digest(hashedBytes);

            // Convert salt and hashed password to Base64 for storage
            String saltBase64 = Base64.getEncoder().encodeToString(salt);
            String hashedPasswordBase64 = Base64.getEncoder().encodeToString(hashedBytes);

            // Combine salt and hashed password for storage
            return saltBase64 + ":" + hashedPasswordBase64;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public List<Customer> getCustomerList() {
        return customerRepository.getCustomerList();
    }

    public void changeAccountType(String email, AccountType accountType) {
        Optional<Customer> foundCustomer = getCustomerList()
                .stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findFirst();

        if (foundCustomer.isPresent()) {
            foundCustomer.get().setAccountType(accountType);
        }
    }
}
