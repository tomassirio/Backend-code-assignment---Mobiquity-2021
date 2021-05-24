package com.mobiquity.service;

import com.mobiquity.factory.ItemFactory;
import com.mobiquity.factory.PackageFactory;
import com.mobiquity.model.ItemDTO;
import com.mobiquity.model.PackageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParseServiceImpl implements ParseService{

    private static Logger log = LoggerFactory.getLogger(ParseServiceImpl.class);

    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static ParseServiceImpl instance;

    private ParseServiceImpl(){}

    //static block initialization for exception handling
    static{
        try{
            instance = new ParseServiceImpl();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static ParseServiceImpl getInstance(){
        return instance;
    }

    @Override
    public final List<PackageDTO> parseFile(File file) throws IOException {
        log.info("Parsing file {}", file);
        List<PackageDTO> packages = new ArrayList<>();
        try (Scanner scanner =  new Scanner(file, ENCODING)){
            while (scanner.hasNextLine()){
                packages.add(processLine(scanner.nextLine()));
            }
        }
        return packages;
    }

    protected PackageDTO processLine(String line){
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
            }
            else {
                log.info("Empty or invalid line. Unable to process.");
            }
        }
        return packageDTO;
    }

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
}
