package com.mobiquity.factory;

import com.mobiquity.model.ItemDTO;
import com.mobiquity.model.PackageDTO;

import java.util.List;

public class PackageFactory {

    /** Creates a Package
     *
     * @param  capacity The package capacity
     * @param  items The package items
     * @return      PackageDTO
     */
    public static PackageDTO createPackage(Integer capacity, List<ItemDTO> items) {return new PackageDTO(capacity,items);}

}
