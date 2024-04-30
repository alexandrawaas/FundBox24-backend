package com.example.fundbox24backend.api;

import com.example.fundbox24backend.api.model.*;
import com.example.fundbox24backend.api.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
class Seeder {

    private boolean enableSeeder = false;

    private static final Logger log = LoggerFactory.getLogger(Seeder.class);

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, UserRepository userRepository, FoundReportRepository foundReportRepository, LocationRepository locationRepository, ChatRepository chatRepository, LostReportRepository lostReportRepository, MessageRepository messageRepository) {
        if(enableSeeder) {
            return args -> {

                // Seed categories
                log.info("Preloading " + categoryRepository.save(new Category("Wallet", ValueType.HIGH, "images/wallet.jpg")));
                log.info("Preloading " + categoryRepository.save(new Category("Keys", ValueType.HIGH, "images/keys.jpg")));
                log.info("Preloading " + categoryRepository.save(new Category("Clothing", ValueType.LOW, "images/clothing.jpg")));
                log.info("Preloading " + categoryRepository.save(new Category("Bag", ValueType.MEDIUM, "images/bag.jpg")));

                // Seed users
                log.info("Preloading " + userRepository.save(new User("BlauerWal123", "max.muster@test.de", "123456", "user")));
                log.info("Preloading " + userRepository.save(new User("RoterFuchs567", "anna.beispiel@test.de", "123456", "user")));

                // Seed found reports
                User max = userRepository.findById(1L).orElse(null);
                Location location = new Location(53.551086, 9.993682);
                log.info("Preloading " + locationRepository.save(location));
                Location location2 = new Location(53.554686, 9.003682);
                log.info("Preloading " + locationRepository.save(location2));
                log.info("Preloading " + foundReportRepository.save(new FoundReport("Pink handbag with water bottle", "bla", "/images/handbag.jpg", LocalDateTime.of(2024, 4, 26, 15, 24), false, categoryRepository.findById(3L).orElse(null), null, LocalDateTime.of(2024, 4, 26, 15, 52), location, location2, max)));

                // Seed lost reports
                User anna = userRepository.findById(2L).orElse(null);
                Location location3 = new Location(53.5588086, 9.993582);
                log.info("Preloading " + locationRepository.save(location3));
                Location location4 = new Location(53.554386, 9.003672);
                log.info("Preloading " + locationRepository.save(location4));
                log.info("Preloading " + lostReportRepository.save(new LostReport(  "Wallet with blue roses print", "bla", "/images/wallet.jpg", LocalDateTime.of(2024, 4, 24, 10, 0), false, categoryRepository.findById(1L).orElse(null), LocalDateTime.of(2024, 4, 24, 7, 0), location3, location4, 4.0, anna)));

                // Seed chats
                Chat chat = new Chat();
                chat.setReportCreator(max);
                chat.setReportVisitor(anna);
                Message message = new TextMessage(LocalDateTime.of(2024, 4,28, 12, 34), max, "Hello, I think I found your wallet. Can you describe it?");
                messageRepository.save(message);
                Message message2 = new TextMessage(LocalDateTime.now(), anna, "Hi! This would be great. It has a blue roses print and contains 120€.");
                messageRepository.save(message2);
                chat.addMessage(message);
                chat.addMessage(message2);
                LostReport lostReport = lostReportRepository.findById(2L).orElse(null);
                log.info("Preloading " + chatRepository.save(chat));
                if(lostReport != null) {
                    chat.setReport(lostReport);
                    lostReport.addChat(chat);
                    lostReportRepository.save(lostReport);
                    chatRepository.save(chat);
                }

            };
        }
        return args -> {};
    }
}
