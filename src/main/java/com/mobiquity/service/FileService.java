package com.mobiquity.service;

import java.io.File;

public interface FileService {

    File openFile(String path);

    void writeToPath(String path, String input);

}
