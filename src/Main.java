import factory.ServiceFactory;
import model.*;
import model.enums.AccountType;
import service.CustomerService;
import service.OrderService;
import service.ProductService;
import service.PublisherService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Create services using factories
        PublisherService publisherService = ServiceFactory.createPublisherService();
        ProductService productService = ServiceFactory.createProductService(publisherService);
        CustomerService customerService = ServiceFactory.createCustomerService();

        // Add publishers
        publisherService.savePublisher("Idil YAYINLARI", LocalDate.of(2014, 5, 26));
        publisherService.savePublisher("Fmss YAYINLARI", LocalDate.of(2023, 5, 26));

        // Add products
        productService.save("Book A", new BigDecimal("100.0"), "Novel", "A thrilling novel", 100, "CAN YAYINLARI");
        productService.save("Book B", new BigDecimal("200.0"), "Science", "A scientific book", 50, "DERGAH YAYINLARI");

        // Add customers
        customerService.save("John", "Doe", "john.doe@example.com", "password123");
        customerService.save("Jane", "Smith", "jane.smith@example.com", "password456");
        customerService.save("Alice", "Johnson", "alice.johnson@example.com", "password789");
        customerService.save("Cem", "Yilmaz", "cem.yilmaz@example.com", "password321");

        // Place orders
        List<Product> productsForJohn = List.of(
                new Product("Book A", new BigDecimal("100.0"), "Novel", "A thrilling novel", 100, publisherService.getByName("CAN YAYINLARI").get()),
                new Product("Book B", new BigDecimal("200.0"), "Science", "A scientific book", 50, publisherService.getByName("DERGAH YAYINLARI").get())
        );
        Order johnOrder = new Order(productsForJohn);
        customerService.placeOrder("john.doe@example.com", johnOrder);

        List<Product> productsForCem = List.of(
                new Product("Book A", new BigDecimal("100.0"), "Novel", "A thrilling novel", 100, publisherService.getByName("CAN YAYINLARI").get())
        );
        Order cemOrder = new Order(productsForCem);
        customerService.placeOrder("cem.yilmaz@example.com", cemOrder);

        // Display customer details
        customerService.getCustomerList().forEach(customer -> {
            System.out.println("Customer email: " + customer.getEmail());
            System.out.println("Customer credit: " + customer.getCredit());
            System.out.println("Customer account type: " + customer.getAccountType());
            System.out.println("Customer orders: " + customer.getOrderList());
            System.out.println("----");
        });

        // Task: Finding customer statistics
        long totalCustomers = customerService.getCustomerList().size();
        long cemProductCount = customerService.getCustomerList().stream()
                .filter(customer -> "Cem".equals(customer.getName()))
                .flatMap(customer -> customer.getOrderList().stream())
                .flatMap(order -> order.getProducts().stream())
                .count();

        BigDecimal cemTotalAmount = customerService.getCustomerList().stream()
                .filter(customer -> "Cem".equals(customer.getName()) && customer.getAge() > 25 && customer.getAge() < 30)
                .flatMap(customer -> customer.getOrderList().stream())
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Order> ordersAbove1500 = customerService.getCustomerList().stream()
                .flatMap(customer -> customer.getOrderList().stream())
                .filter(order -> order.getTotalAmount().compareTo(new BigDecimal("1500")) > 0)
                .toList();

        System.out.println("Total customers: " + totalCustomers);
        System.out.println("Total products ordered by Cem: " + cemProductCount);
        System.out.println("Total amount spent by Cem (age between 25 and 30): " + cemTotalAmount);
        System.out.println("Orders above 1500 TL: " + ordersAbove1500);
      /*  Set<String> names = new HashSet<>();
        names.add("cem");
        names.add("furkan");
        names.add("furkan");
        names.add("Furkan");

        names.forEach(System.out::println);*/

        System.out.println("CUSTOMER LIST\n");

      /*  CustomerService customerService = new CustomerService();

        customerService.save("cem", "dırman", "cem@gmail.com", "password");

        customerService.save("erkam", "veli", "erkam@gmail.com", "password");

        customerService.save("veli", "dırman", "veli@gmail.com", "password");

        //customer1.setAccountType(AccountType.GOLD);

        customerService.changeAccountType("cem@gmail.com", AccountType.GOLD);

        customerService.getCustomerList().forEach(System.out::println);
*/
/*
        //-- product
        System.out.println("\nPUBLISHER LIST\n");

        PublisherService publisherService = new PublisherService();
        publisherService.savePublisher("DERGAH YAYINLARI", LocalDate.now().minusYears(1));
        publisherService.savePublisher("CAN YAYINLARI", LocalDate.now().minusYears(10));
        System.out.println(publisherService.hashCode());

        publisherService.getAllPublishers().forEach(System.out::println);

        System.out.println("\nPRODUCT LIST\n");
*/
       /* ProductService productService = new ProductService(publisherService);

        productService.save("Şeker Portakalı", new BigDecimal("90.20"),
                "Ne güzel bir şeker portakalı fidanıymış bu! Hem bak, dikeni de yok. Pek de kişilik sahibiymiş, şeker portakalı olduğu ta uzaktan belli. Ben senin boyunda olsaydım başka şey istemezdim.”\n" +
                        "“Ama ben büyük bir ağaç istiyordum.", "CAN YAYINLARI");

        productService.save("Saatleri Ayarlama Enstitüsü", new BigDecimal("240.00"),
                "NAhmet Hamdi Tanpınar’ın şiiri sembolist bir ifade üzerine kurulmuştur. Aynı anlatım tarzı romanlarına da zaman zaman sirayet eder. Ancak muhteva açısından metafizik eğilimleri ile estetik endişelerini şiire ayırdığı halde, sosyal temalar için nesri seçmiştir.",
                "CAN YAYINLARI");

        productService.save("Saatleri Ayarlama Enstitüsü", new BigDecimal("240.00"),
                "NAhmet Hamdi Tanpınar’ın şiiri sembolist bir ifade üzerine kurulmuştur. Aynı anlatım tarzı romanlarına da zaman zaman sirayet eder. Ancak muhteva açısından metafizik eğilimleri ile estetik endişelerini şiire ayırdığı halde, sosyal temalar için nesri seçmiştir.",
                "CAN YAYINLARI");

        productService.save("Küçük Prens", new BigDecimal("12.88"), "Ne güzel bir şeker portakalı fidanıymış bu! Hem bak, dikeni de yok. Pek de kişilik sahibiymiş, şeker portakalı olduğu ta uzaktan belli. Ben senin boyunda olsaydım başka şey istemezdim.”\n" +
                "“Ama ben büyük bir ağaç istiyordum.", "DERGAH YAYINLARI");

        productService.save("Küçük Prens", new BigDecimal("12.88"), "Ne güzel bir şeker portakalı fidanıymış bu! Hem bak, dikeni de yok. Pek de kişilik sahibiymiş, şeker portakalı olduğu ta uzaktan belli. Ben senin boyunda olsaydım başka şey istemezdim.”\n" +
                "“Ama ben büyük bir ağaç istiyordum.", "DERGAH YAYINLARI");

        productService.listAll();
*/
        //order
        System.out.println("\n ORDER LIST \n");

        //customer1.setOrderList(List.of(prepareOrder(List.of(product1, product2)))); çözüm 1

        // customer1.getOrderList().add(prepareOrder(List.of(product1, product2))); // çözüm 2

        // ödev email adresi verilen kullanıcının orderlerini getiren method
        //  listOrdersByEmail("cem@gmail.com");
    }

    /* ödev OrderService oluşturulacak
    private static void listOrdersByEmail(String email) {
        List<Order> orderList = customerList.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .flatMap(customer -> customer.getOrderList().stream())
                .toList();

        orderList.forEach(System.out::println);
    }

     */

}
