package com.mobiquity.service;

import com.mobiquity.model.PackageDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ParseService {

    /** Reads a File and parses the packages into a List of PackageDTO
     *
     * @param  file The path where the file is stored
     * @return      a List of PackageDTO
     */
    List<PackageDTO> parseFile(File file) throws IOException;
}
