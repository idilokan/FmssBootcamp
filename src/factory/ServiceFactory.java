package factory;

import service.CustomerService;
import service.ProductService;
import service.PublisherService;

public class ServiceFactory {
    private static PublisherService publisherServiceInstance;

    public static CustomerService createCustomerService() {
        return CustomerService.getInstance();
    }

    public static ProductService createProductService(PublisherService publisherService) {
        return ProductService.getInstance(publisherService);
    }

    public static PublisherService createPublisherService() {
        if (publisherServiceInstance == null) {
            publisherServiceInstance = PublisherService.getInstance();
        }
        return publisherServiceInstance;
    }
}
