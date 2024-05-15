package com.example.fundbox24backend.api.datatransfer.user;

import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDtoResponse
{
    @JsonProperty("id") private Long id;
    @JsonProperty("username") private String username;
    @JsonProperty("email") private String email;
    @JsonProperty("homeLocation") private Location homeLocation;
    @JsonProperty("homeRadius") private double homeRadius;
    @JsonProperty("useCurrentLocation") private boolean useCurrentLocation;
    @JsonProperty("receiveNotifications") private boolean receiveNotifications;
}
