package com.tsb.TrabSistemasWeb.infra.seeder;

import com.tsb.TrabSistemasWeb.domain.entities.*;
import com.tsb.TrabSistemasWeb.domain.enums.OrderStatus;
import com.tsb.TrabSistemasWeb.repository.*;
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
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Clear existing data
//        orderRepository.deleteAll();
//        userRepository.deleteAll();

        // Users
        User u1 = new User();
        u1.setName("John Doe");
        u1.setEmail("john@email.com");
        u1.setPassword(passwordEncoder.encode("password"));
        u1.setPhone("123456789");

        User u2 = new User();
        u2.setName("Jane Smith");
        u2.setEmail("jane@example.com");
        u2.setPassword(passwordEncoder.encode("password"));
        u2.setPhone("987654321");

        // Orders
        Order o1 = new Order();
        o1.setMoment(Instant.now());
        o1.setClient(u1);
        o1.setOrderStatus(OrderStatus.PAID);

        Order o2 = new Order();
        o2.setMoment(Instant.now().plusSeconds(3600)); // 1 hour later
        o2.setClient(u1);
        o2.setOrderStatus(OrderStatus.CANCELLED);

        Order o3 = new Order();
        o3.setMoment(Instant.now().plusSeconds(7200)); // 2 hours later
        o3.setClient(u2);
        o3.setOrderStatus(OrderStatus.SHIPPED);

        // Categories
        Category cat1 = new Category();
        cat1.setName("Electronics");

        Category cat2 = new Category();
        cat2.setName("Books");

        Category cat3 = new Category();
        cat3.setName("Computers");

        // Products
        Product p1 = new Product();
        p1.setName("The Lord of the Rings");
        p1.setDescription("Lorem ipsum dolor sit amet, consectetur.");
        p1.setPrice(90.5);
        p1.setImgUrl("");

        Product p2 = new Product();
        p2.setName("Smart TV");
        p2.setDescription("Nulla eu imperdiet purus. Maecenas ante.");
        p2.setPrice(2190.0);
        p2.setImgUrl("");

        Product p3 = new Product();
        p3.setName("Macbook Pro");
        p3.setDescription("Nam eleifend maximus tortor, at mollis.");
        p3.setPrice(1250.0);
        p3.setImgUrl("");

        Product p4 = new Product();
        p4.setName("PC Gamer");
        p4.setDescription("Donec aliquet odio ac rhoncus cursus.");
        p4.setPrice(1200.0);
        p4.setImgUrl("");

        Product p5 = new Product();
        p5.setName("Rails for Dummies");
        p5.setDescription("Cras fringilla convallis sem vel faucibus.");
        p5.setPrice(100.99);
        p5.setImgUrl("");

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p3.getCategories().add(cat1);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        // Order
        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        // Payment
        Payment pay1 = new Payment();
        pay1.setDate(Instant.now().plusSeconds(3600));
        pay1.setOrder(o1);
        o1.setPayment(pay1);

        // Save
//        userRepository.save(u1);
//        userRepository.save(u2);
//        categoryRepository.saveAll(List.of(cat1, cat2, cat3));
//        orderRepository.saveAll(List.of(o1, o2, o3));
//        productRepository.saveAll(List.of(p1, p2, p3, p4, p5));
//        orderItemRepository.saveAll(List.of(oi1, oi2, oi3, oi4));
//        orderRepository.saveAll(List.of(o1));
    }
}
