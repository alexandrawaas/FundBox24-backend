package com.example.fundbox24backend.api.datatransfer.user;

import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDtoRequest
{
    @JsonProperty("homeLocation") private Location homeLocation;
    @JsonProperty("homeRadius") private int homeRadius;
    @JsonProperty("useCurrentLocation") private boolean useCurrentLocation;
    @JsonProperty("receiveNotifications") private boolean receiveNotifications;
}
