package com.example.fundbox24backend.api;

import com.example.fundbox24backend.api.model.*;
import com.example.fundbox24backend.api.repository.*;
import org.hibernate.boot.model.relational.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
class Seeder {

    private boolean enableSeeder = true;
    // TODO: Drop Database if enableSeeder is true

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Bean
    CommandLineRunner initDatabase(
            CategoryRepository categoryRepository,
            UserRepository userRepository,
            FoundReportRepository foundReportRepository,
            LocationRepository locationRepository,
            ChatRepository chatRepository,
            LostReportRepository lostReportRepository,
            MessageRepository messageRepository,
            PasswordEncoder passwordEncoder
    ) {
        if(enableSeeder) {
            return args -> {

                // Clear tables
                foundReportRepository.deleteAll();
                lostReportRepository.deleteAll();
                messageRepository.deleteAll();
                userRepository.deleteAll();
                categoryRepository.deleteAll();

                // Seed categories
                log.info("Preloading " + categoryRepository.save(new Category("Wallet", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Keys", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Clothing", ValueType.LOW)));
                log.info("Preloading " + categoryRepository.save(new Category("Bag", ValueType.LOW)));
                log.info("Preloading " + categoryRepository.save(new Category("Device", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Jewelry", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Toy", ValueType.LOW)));
                log.info("Preloading " + categoryRepository.save(new Category("Other", ValueType.LOW)));

                // Seed users
                User testUser1 = new User(
                        "BlauerWal123",
                        "max.muster@test.de",
                        passwordEncoder.encode("123456")
                );

                User testUser2 = new User(
                        "RoterFuchs567",
                        "anna.beispiel@test.de",
                        passwordEncoder.encode("123456")
                );

                User max = userRepository.save(testUser1);
                User anna = userRepository.save(testUser2);

                // Seed found reports
                log.info("Preloading " + foundReportRepository.save(new FoundReport("Pink handbag with water bottle", "bla", "/images/handbag.jpg", false, categoryRepository.findAll().get(2), LocalDateTime.of(2024, 4, 26, 15, 52), new Location(53.551086, 9.993682), new Location(53.554686, 9.003682), max)));

                foundReportRepository.save(new FoundReport("Black wallet with 120€", "bla", "/images/wallet.jpg", false, categoryRepository.findAll().getFirst(), LocalDateTime.of(2024, 4, 24, 7, 0), new Location(53.554686, 9.002382), new Location(51.5542386, 9.403682), max));

                // Seed lost report and chat
                Location location3 = new Location(53.5588086, 9.993582);
                Location location4 = new Location(53.554386, 9.003672);
                LostReport lostReport = lostReportRepository.save(new LostReport(  "Wallet with blue roses print", "bla", "/images/wallet.jpg", false, categoryRepository.findAll().getFirst(), LocalDateTime.of(2024, 4, 24, 7, 0), location3, location4, 4, anna));

                Chat chat = new Chat();
                chat.setReport(lostReport);
                chat.setReportCreator(max);
                chat.setReportVisitor(anna);
                Message message = new TextMessage(LocalDateTime.of(2024, 4,28, 12, 34), max, "Hello, I think I found your wallet. Can you describe it?");
                Message message2 = new TextMessage(LocalDateTime.now(), anna, "Hi! This would be great. It has a blue roses print and contains 120€.");
                chat.addMessage(message);
                chat.addMessage(message2);
                lostReport.addChat(chat);
                lostReportRepository.save(lostReport);

            };
        }
        return args -> {};
    }
}
