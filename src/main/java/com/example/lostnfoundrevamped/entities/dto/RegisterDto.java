package com.example.lostnfoundrevamped.entities.dto;

public record RegisterDto(String username,
                          String realname,
                          String password,
                          String confirmpassword) { }