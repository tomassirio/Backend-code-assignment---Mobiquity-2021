package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {
    private Integer id;
    private Double weight;
    private Integer value;
}
