package com.example.lostnfoundrevamped.entities.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class InsertMessageDto {
    private String messagecontent;
    private Integer item_entity_itemid;
    private String user_entity_username;
}