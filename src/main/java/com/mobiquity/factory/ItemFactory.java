package com.mobiquity.factory;

import com.mobiquity.model.ItemDTO;

public class ItemFactory {

    /** Creates an item
     *
     * @param  id The items ID
     * @param  weight The items weight
     * @param  value The items value
     * @return      ItemDTO
     */
    public static ItemDTO createItem(Integer id, Double weight, Integer value) {return new ItemDTO(id, weight, value);}
}
