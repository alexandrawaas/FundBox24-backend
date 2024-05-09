package com.example.fundbox24backend.api.datatransfer.user;

import com.example.fundbox24backend.api.model.User;

public class UserConverter
{
    public UserDtoResponse convertToDtoResponse(User user)
    {
        return new UserDtoResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getHomeLocation(),
                user.getHomeRadius(),
                user.isUseCurrentLocation(),
                user.isReceiveNotifications()
        );
    }
}
