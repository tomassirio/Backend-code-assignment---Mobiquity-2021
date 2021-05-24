package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class PackageDTO {
    @Min(value = 0, message = "Capacity can't be negative")
    @Max(value = 100, message = "Capacity can't be more than 100")
    private Integer capacity;
    @Size(max = 15)
    private List<ItemDTO> items;
}
