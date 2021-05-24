package com.mobiquity.factory;

import com.mobiquity.model.ItemDTO;

public class ItemFactory {

    public static ItemDTO createItem(Integer id, Double weight, Integer value) {return new ItemDTO(id, weight, value);}
}
