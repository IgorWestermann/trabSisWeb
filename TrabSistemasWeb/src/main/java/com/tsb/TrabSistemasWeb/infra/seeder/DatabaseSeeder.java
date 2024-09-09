package com.tsb.TrabSistemasWeb.infra.seeder;

import com.tsb.TrabSistemasWeb.domain.entities.Category;
import com.tsb.TrabSistemasWeb.domain.entities.Order;
import com.tsb.TrabSistemasWeb.domain.entities.Product;
import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.domain.enums.OrderStatus;
import com.tsb.TrabSistemasWeb.repository.CategoryRepository;
import com.tsb.TrabSistemasWeb.repository.OrderRepository;
import com.tsb.TrabSistemasWeb.repository.ProductRepository;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
//        orderRepository.deleteAll();
//        userRepository.deleteAll();

        // Create some users
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john@email.com");
        user1.setPassword(passwordEncoder.encode("password"));
        user1.setPhone("123456789");

        User user2 = new User();
        user2.setName("Jane Smith");
        user2.setEmail("jane@example.com");
        user2.setPassword(passwordEncoder.encode("password"));
        user2.setPhone("987654321");
        // Save users
//        userRepository.save(user1);
//        userRepository.save(user2);

        Order order1 = new Order();
        order1.setMoment(Instant.now());
        order1.setClient(user1);
        order1.setOrderStatus(OrderStatus.PAID.getCode());

        Order order2 = new Order();
        order2.setMoment(Instant.now().plusSeconds(3600)); // 1 hour later
        order2.setClient(user1);
        order2.setOrderStatus(OrderStatus.CANCELLED.getCode());

        Order order3 = new Order();
        order3.setMoment(Instant.now().plusSeconds(7200)); // 2 hours later
        order3.setClient(user2);
        order3.setOrderStatus(OrderStatus.SHIPPED.getCode());
        // Save orders
//        orderRepository.saveAll(List.of(order1, order2, order3));

        Category cat1 = new Category();
        cat1.setName("Electronics");

        Category cat2 = new Category();
        cat2.setName("Books");

        Category cat3 = new Category();
        cat3.setName("Computers");
        // Save categories
//        categoryRepository.saveAll(List.of(cat1, cat2, cat3));

        Product p1 = new Product();
        p1.setName("The Lord of the Rings");
        p1.setDescription("Lorem ipsum dolor sit amet, consectetur.");
        p1.setPrice(90.5);
        p1.setIngUrl("");

        Product p2 = new Product();
        p2.setName("Smart TV");
        p2.setDescription("Nulla eu imperdiet purus. Maecenas ante.");
        p2.setPrice(2190.0);
        p2.setIngUrl("");

        Product p3 = new Product();
        p3.setName("Macbook Pro");
        p3.setDescription("Nam eleifend maximus tortor, at mollis.");
        p3.setPrice(1250.0);
        p3.setIngUrl("");

        Product p4 = new Product();
        p4.setName("PC Gamer");
        p4.setDescription("Donec aliquet odio ac rhoncus cursus.");
        p4.setPrice(1200.0);
        p4.setIngUrl("");

        Product p5 = new Product();
        p5.setName("Rails for Dummies");
        p5.setDescription("Cras fringilla convallis sem vel faucibus.");
        p5.setPrice(100.99);
        p5.setIngUrl("");
        // Save products
        productRepository.saveAll(List.of(p1, p2, p3, p4, p5));

    }
}
