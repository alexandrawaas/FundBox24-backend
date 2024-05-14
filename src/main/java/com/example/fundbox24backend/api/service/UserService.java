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
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final LostReportConverter lostReportConverter;
    private final FoundReportConverter foundReportConverter;
    private final ChatConverter chatConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter, LostReportConverter lostReportConverter, FoundReportConverter foundReportConverter, ChatConverter chatConverter)
    {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.lostReportConverter = lostReportConverter;
        this.foundReportConverter = foundReportConverter;
        this.chatConverter = chatConverter;
    }

    public UserDtoResponse getCurrentUser()
    {
        // TODO: implement getCurrentUser
        return userConverter.convertToDtoResponse(userRepository.findAll().getFirst());
    }

    public User getCurrentUserEntity()
    {
        // TODO: implement getCurrentUser
        return userRepository.findAll().getFirst();
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

    public UserDtoResponse register(AuthDtoRequest authDtoRequest)
    {
        // TODO: implement authentication
        return userConverter.convertToDtoResponse(
                userRepository.save(
                        new User(null, authDtoRequest.getEmail(), authDtoRequest.getPassword())
                )
        );
    }

    public UserDtoResponse login(AuthDtoRequest authDtoRequest)
    {
        // TODO: implement authentication
        return getCurrentUser();
    }

    public void logout()
    {
        // TODO: implement authentication
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
        return List.of(chats1, chats2).stream().flatMap(List::stream).toList();
    }
}
