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
                log.info("Preloading " + categoryRepository.save(new Category("Geldbörse", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Schlüssel", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Kleidung", ValueType.LOW)));
                log.info("Preloading " + categoryRepository.save(new Category("Tasche", ValueType.LOW)));
                log.info("Preloading " + categoryRepository.save(new Category("Gerät", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Schmuck", ValueType.HIGH)));
                log.info("Preloading " + categoryRepository.save(new Category("Spielzeug", ValueType.LOW)));
                log.info("Preloading " + categoryRepository.save(new Category("Sonstiges", ValueType.LOW)));

                // Seed users
                User testUser1 = new User(
                        "LachenderLurch",
                        "lurchi@quakmail.de",
                        passwordEncoder.encode("lurch123")
                );

                User testUser2 = new User(
                        "WhopperBomber",
                        "whopwhop@bk.com",
                        passwordEncoder.encode("whopper123")
                );

                User max = userRepository.save(testUser1);
                User anna = userRepository.save(testUser2);

                // Seed found reports
                log.info("Preloading " + foundReportRepository.save(new FoundReport("Rosa Handtasche mit Wasserflasche", "Eine kleine Handtasche mit Schulterriemen aus rosa gefärbtem Kunstleder. Enthalten sind eine halbvolle Wasserflasche mit Holzdeckel sowie weitere kleine Gegenstände (z.B. Haarbürste).", "/images/handbag.jpg", false, categoryRepository.findAll().get(3), LocalDateTime.of(2024, 4, 26, 15, 52), new Location(53.551086, 9.993682), new Location(53.554686, 9.003682), max)));

                foundReportRepository.save(new FoundReport("Schwarzer Geldbeutel mit 120€", "Ich habe einen klassischen Geldbeutel gefunden, der 120€ enthält und unten etwas abgenutzt ist. Ansonsten sind noch ein blauer Einkaufschip und ein Essensgutschein über 30€ enthalten.", "/images/wallet.jpg", false, categoryRepository.findAll().getFirst(), LocalDateTime.of(2024, 4, 24, 7, 0), new Location(53.554686, 9.002382), new Location(51.5542386, 9.403682), max));

                // Seed lost report and chat
                Location location3 = new Location(53.5588086, 9.993582);
                Location location4 = new Location(53.554386, 9.003672);
                LostReport lostReport = lostReportRepository.save(new LostReport(  "Geldbörse mit blauem Blumenprint", "Ein Damengeldbeutel, der mit hellblauen und dunkelblauen Rosen bedruckt ist. Enthält verschiedene Stempelkarten und ca. 60 Euro.", "/images/wallet2.jpg", false, categoryRepository.findAll().getFirst(), LocalDateTime.of(2024, 4, 24, 7, 0), location3, location4, 4, anna));

                Chat chat = new Chat();
                chat.setReport(lostReport);
                chat.setReportCreator(max);
                chat.setReportVisitor(anna);
                Message message = new TextMessage(LocalDateTime.of(2024, 5,7, 12, 34), max, "Hallo, ich glaube, ich habe deinen Geldbeutel gefunden. Kannst du ihn näher beschreiben?");
                Message message2 = new TextMessage(LocalDateTime.of(2024, 5, 9, 10, 17), anna, "Hi! Das wäre ja toll! Der Geldbeutel ist mit blauen Rosen bedruckt, hat innen eine kleine Macke auf der rechten Seite und enthält eine Stempelkarte vom Café Meier in Hamburg.");
                chat.addMessage(message);
                chat.addMessage(message2);
                lostReport.addChat(chat);
                lostReportRepository.save(lostReport);

            };
        }
        return args -> {};
    }
}
