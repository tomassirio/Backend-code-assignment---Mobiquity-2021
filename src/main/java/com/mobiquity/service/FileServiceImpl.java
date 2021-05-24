package com.mobiquity.service;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

@NoArgsConstructor
public class FileServiceImpl implements FileService{

    private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
    private static FileServiceImpl instance;

    /**static block initialization for exception handling*/
    static{
        try{
            instance = new FileServiceImpl();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    /** Opens a File in the given path. If there is now actual file, then it throws an error.
     *
     * @param  path The path where the file is stored
     * @return      The Given File
     */
    @Override
    public File openFile(String path) {
        log.info("Opening file on path {}", path);
        File file = null;
        try {
            file = new File(path);
            FileInputStream fis = new FileInputStream(file);     //opens a connection to an actual file
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return file;
    }

    /** Writes the input in the file stored in path.
     *
     * @param  path The path where the file is stored
     * @param  input The path where the file is stored
     * @return      The Given File
     */
    @Override
    public void writeToPath(String path, String input){
        try {
            String outputPath = createOutputPath(path);
            log.info("Writing to file on path: {}", outputPath);
            FileWriter myWriter = new FileWriter(outputPath);
            myWriter.write(input);
            myWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Replaces the '_input' substring on the path for a '_output'. If there was no substring, then it creates it
     *
     * @param  path The path where the file is stored
     * @return      The Output Path
     */
    private String createOutputPath(String path) {
        if (path.contains("_input")) {
            return path.replace("_input", "_output");
        } else {
            return path + "_output";
        }
    }

    /** Returns the Singleton instance for this class
     *
     * @return      FileServiceImpl's instance
     */
    public static FileServiceImpl getInstance(){
        return instance;
    }

}
