package com.mobiquity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
public class ItemDTO {
    @Min(value = 1, message = "Id can't be negative")
    private Integer id;
    @Min(value = 0, message = "Weight can't be negative")
    @Max(value = 100, message = "Weight can't be more than 100")
    private Double weight;
    @Min(value = 0, message = "Value can't be negative")
    @Max(value = 100, message = "Value can't be more than 100")
    private Integer value;
}
