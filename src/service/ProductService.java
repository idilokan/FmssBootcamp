package service;

import model.Product;
import model.Publisher;
import repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class ProductService {
    private static ProductService instance;
    private ProductRepository productRepository;
    private PublisherService publisherService;

    private ProductService(PublisherService publisherService) {
        this.productRepository = new ProductRepository();
        this.publisherService = publisherService;
    }

    public static ProductService getInstance(PublisherService publisherService) {
        if (instance == null) {
            instance = new ProductService(publisherService);
        }
        return instance;
    }

    public void save(String name, BigDecimal amount, String category, String description, int stock, String publisherName) {
        Optional<Publisher> publisher = publisherService.getByName(publisherName);

        if (publisher.isEmpty()) {
            throw new RuntimeException("Publisher not found: " + publisherName);
        }

        Product product = new Product(name, amount, category, description, stock, publisher.get());
        productRepository.save(product);
    }

    public Set<Product> getAll() {
        return productRepository.getAll();
    }

    public void listAll() {
        getAll().forEach(System.out::println);
    }
}
