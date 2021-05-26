package com.mobiquity.service;

import com.mobiquity.exception.APIException;

import java.io.File;

public interface FileService {

    /** Opens a File in the given path. If there is now actual file, then it throws an error.
     *
     * @param  path The path where the file is stored
     * @return      The Given File
     */
    File openFile(String path) throws APIException;

    /** Writes the input in the file stored in path.
     *
     * @param  path The path where the file is stored
     * @param  input The path where the file is stored
     * @return      The Given File
     */
    void writeToPath(String path, String input) throws APIException;

}
