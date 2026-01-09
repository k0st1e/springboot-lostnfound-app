package com.example.lostnfoundrevamped.entities.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {
    private String username;
    private String realname;
    private String password;
    private String role;
}