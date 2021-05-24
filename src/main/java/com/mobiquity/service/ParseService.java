package com.mobiquity.service;

import com.mobiquity.model.PackageDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ParseService {

    List<PackageDTO> parseFile(File file) throws IOException;
}
