package com.example.lostnfoundrevamped.entities.dto;

import com.example.lostnfoundrevamped.entities.enums.ItemStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class InsertItemDto {
    private String itemname;
    private String itemdescription;
    private String itemlocation;
    private String itemcontact;
    private ItemStatus itemstatus;
    private Integer categoryId;
}