package com.mobiquity.factory;

import com.mobiquity.model.ItemDTO;
import com.mobiquity.model.PackageDTO;

import java.util.List;

public class PackageFactory {

    public static PackageDTO createPackage(Integer capacity, List<ItemDTO> items) {return new PackageDTO(capacity,items);}

}
