package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportConverter;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoResponse;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportConverter;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoResponse;
import com.example.fundbox24backend.api.datatransfer.user.AuthDtoRequest;
import com.example.fundbox24backend.api.datatransfer.user.UserConverter;
import com.example.fundbox24backend.api.datatransfer.user.UserDtoRequest;
import com.example.fundbox24backend.api.datatransfer.user.UserDtoResponse;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.model.LostReport;
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final LostReportConverter lostReportConverter;
    private final FoundReportConverter foundReportConverter;
    private final ChatConverter chatConverter;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter userConverter, LostReportConverter lostReportConverter, FoundReportConverter foundReportConverter, ChatConverter chatConverter, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.lostReportConverter = lostReportConverter;
        this.foundReportConverter = foundReportConverter;
        this.chatConverter = chatConverter;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDtoResponse getCurrentUser() {
        User user = getCurrentUserEntity();

        return userConverter.convertToDtoResponse(user);
    }

    public User getCurrentUserEntity()
    {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email);
    }

    public UserDtoResponse updateUser(UserDtoRequest userDtoRequest)
    {
        User user = getCurrentUserEntity();
        user.setHomeLocation(userDtoRequest.getHomeLocation());
        user.setHomeRadius(userDtoRequest.getHomeRadius());
        user.setUseCurrentLocation(userDtoRequest.isUseCurrentLocation());
        user.setReceiveNotifications(userDtoRequest.isReceiveNotifications());
        return userConverter.convertToDtoResponse(userRepository.save(user));
    }

    public String generateRandomUsername() {
        Faker faker = new Faker(
                Locale.of("de")
        );

        return faker.rickAndMorty().character();
    }

    public UserDtoResponse register(AuthDtoRequest authDtoRequest)
    {
        String passwordHash = passwordEncoder.encode(
                authDtoRequest.getPassword()
        );

        User newUser = new User(
                generateRandomUsername(),
                authDtoRequest.getEmail(),
                passwordHash
        );

        User createdUser = userRepository.save(newUser);

        return userConverter.convertToDtoResponse(createdUser);
    }

    public UserDtoResponse login()
    {
        return getCurrentUser();
    }

    public List<LostReportDtoResponse> getUserLostReports()
    {
        return getCurrentUserEntity().getLostReports().stream().map(lostReportConverter::convertToDtoResponse).toList();
    }

    public List<FoundReportDtoResponse> getUserFoundReports()
    {
        return getCurrentUserEntity().getFoundReports().stream().map(foundReportConverter::convertToDtoResponse).toList();
    }

    public List<ChatDtoResponse> getUserChats()
    {
        List<ChatDtoResponse> chats1 = getCurrentUserEntity().getInitiatedChats().stream().map(chatConverter::convertToDtoResponse).toList();
        List<ChatDtoResponse> chats2 = getCurrentUserEntity().getOwnChats().stream().map(chatConverter::convertToDtoResponse).toList();
        return Stream.of(chats1, chats2).flatMap(List::stream).toList();
    }

    public void addFoundReport(FoundReport report) {
        User user = getCurrentUserEntity();
        user.getFoundReports().add(report);
        userRepository.save(user);
    }

    public void addLostReport(LostReport report) {
        User user = getCurrentUserEntity();
        user.getLostReports().add(report);
        userRepository.save(user);
    }
}
