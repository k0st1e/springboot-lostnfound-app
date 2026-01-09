package com.example.lostnfoundrevamped.entities.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class NewAlertDto {
    private String alertMessage;
    private Boolean isSeen;
    private String itemStatus;
    private Integer itemCategory;
}