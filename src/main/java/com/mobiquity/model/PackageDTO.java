package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PackageDTO {
    private Integer capacity;
    private List<ItemDTO> items;
}
