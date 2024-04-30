package com.example.fundbox24backend.api.controller.exceptions;

public class ChatNotFoundException extends RuntimeException
{
    public ChatNotFoundException()
    {
        super("Could not find chat");
    }
}
