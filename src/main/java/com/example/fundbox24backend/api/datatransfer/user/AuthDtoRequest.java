package com.example.fundbox24backend.api.datatransfer.user;

import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthDtoRequest
{
    @JsonProperty("email") private String email;
    @JsonProperty("password") private String password;
}
