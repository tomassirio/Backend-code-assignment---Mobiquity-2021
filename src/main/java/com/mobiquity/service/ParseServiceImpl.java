package com.mobiquity.service;

import com.mobiquity.exception.APIException;
import com.mobiquity.factory.ItemFactory;
import com.mobiquity.factory.PackageFactory;
import com.mobiquity.model.ItemDTO;
import com.mobiquity.model.PackageDTO;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@NoArgsConstructor
public class ParseServiceImpl implements ParseService{

    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static Logger log = LoggerFactory.getLogger(ParseServiceImpl.class);
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();
    private static ParseServiceImpl instance;

    /**static block initialization for exception handling*/
    static{
        try{
            instance = new ParseServiceImpl();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    /** Reads a File and parses the packages into a List of PackageDTO
     *
     * @param  file The path where the file is stored
     * @return      a List of PackageDTO
     */
    @Override
    public final List<PackageDTO> parseFile(File file) throws APIException {
        log.info("Parsing file {}", file);
        List<PackageDTO> packages = new ArrayList<>();
        try (Scanner scanner =  new Scanner(file, ENCODING)){
            while (scanner.hasNextLine()){
                packages.add(processLine(scanner.nextLine()));
            }
        }catch (IOException e) {
            throw new APIException("Error parsing file", e);
        }
        return packages;
    }

    /** Process a line of a file and returns the corresponding package
     *
     * @param  line The file's line
     * @return      a PackageDTO
     */
    private PackageDTO processLine(String line) throws APIException{
        //use a second Scanner to parse the content of each line
        PackageDTO packageDTO = null;
        try(Scanner scanner = new Scanner(line)){
            scanner.useDelimiter(" :");
            if (scanner.hasNext()){
                //assumes the line has a certain structure
                String capacity = scanner.next();
                String items = scanner.next();
                log.info("Capacity is : " + capacity.trim() + ", and Items are : " + items.trim());
                packageDTO = PackageFactory.createPackage(Integer.valueOf(capacity), processItems(items));
                inputConstraints(packageDTO);
            }
            else {
                log.info("Empty or invalid line. Unable to process.");
            }
        } catch (Exception e) {
            throw new APIException("Error reading line", e);
        }
        return packageDTO;
    }

    /** Process the items on the given line
     *
     * @param  items The line's items
     * @return      a list of ItemDTO
     */
    private List<ItemDTO> processItems(String items){
        items = items.replaceAll("€", "")
                .replaceAll(" ", "")
                .replaceAll("\\)", "")
                .replaceFirst("\\(", "");

        String[] parts = items.split("\\(");

        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (String part : parts){
            String [] itemParts = part.split(",");
            ItemDTO item = ItemFactory.createItem(
                    Integer.valueOf(itemParts[0]),
                    Double.valueOf(itemParts[1]),
                    Integer.valueOf(itemParts[2]));
            itemDTOList.add(item);
        }
        return itemDTOList;
    }

    /** Checks the constraints on everyDTO
     *
     * @param  packageDTO the package to validate
     */
    private void inputConstraints(PackageDTO packageDTO) throws APIException {
        Set<ConstraintViolation<PackageDTO>> violations = validator.validate(packageDTO);
        for (ConstraintViolation<PackageDTO> violation : violations) {
            throw new APIException("Constraints issue: " + violation.getMessage());
        }
    }

    /** Returns the Singleton instance for this class
     *
     * @return      ParseServiceImpl's instance
     */
    public static ParseServiceImpl getInstance(){
        return instance;
    }
}

