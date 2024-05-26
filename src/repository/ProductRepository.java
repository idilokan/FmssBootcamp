package repository;

import model.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductRepository {
    private static ProductRepository instance;
    private Set<Product> productSet;

    public ProductRepository() {
        productSet = new HashSet<>();
    }

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void save(Product product) {
        productSet.add(product);
    }

    public Set<Product> getAll() {
        return productSet;
    }
}
