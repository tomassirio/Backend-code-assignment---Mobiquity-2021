package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.ItemDTO;
import com.mobiquity.model.PackageDTO;
import com.mobiquity.service.FileServiceImpl;
import com.mobiquity.service.ParseServiceImpl;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;

@NoArgsConstructor
public class Packer {

  private static final Integer GRAMS_IN_KILO = 100;

  private static Logger log = LoggerFactory.getLogger(Packer.class);
  private static FileServiceImpl fileService = FileServiceImpl.getInstance();
  private static ParseServiceImpl parseService = ParseServiceImpl.getInstance();

  /** Receives a file with packages and items. Returns the most beneficial combination
   *
   * @param  filePath The path where the file is stored
   * @return The solution
   */
  public static String pack(String filePath) throws APIException{
    log.info("Packing {}", filePath);
    StringBuilder sb = new StringBuilder();
    try{
      List<PackageDTO> packages = preparePackages(filePath);
      for(PackageDTO packageDTO : packages){
        List<Integer> packDPSolution = packDPInit(packageDTO);
        sb = parseEmptySolution(sb, packDPSolution);
      }
    }catch (APIException e) {
      throw e;
    }
    String res = trimSolution(sb);
    fileService.writeToPath(filePath, res);
    return res;
  }

  /** Opens file and parses it into a List of Packages to be sorted
    *
    * @param filePath path where the file is located
    *  @return A list with the Packages
   */
  private static List<PackageDTO> preparePackages(String filePath) throws APIException{
    List<PackageDTO> packageDTOS = null;
    try {
      File input = fileService.openFile(filePath);
      packageDTOS = parseService.parseFile(input);
    }catch (IOException e) {
      throw new APIException("File not found", e);
    }
    return packageDTOS;
  }

  /**
    * https://en.wikipedia.org/wiki/Knapsack_problem#0-1_knapsack_problem
    *
    * This problem is a variation of the 0-1 knapsack problem.
    * It runs on pseudo polynomial time if it's solved through dynamic programming.
    *
    * It's complexity is O(n*W) both on space and time, being n the amount of items and W the capacity on the package
    * @param packageDTO package to be packed
   *  @return A list with the indexes of the best items
  */
  private static List<Integer> packDPInit(PackageDTO packageDTO) {
    // Declare the table dynamically
    Integer itemsSize = packageDTO.getItems().size();
    Integer capacityGrams = packageDTO.getCapacity() * GRAMS_IN_KILO;
    Integer mem[][] = new Integer[itemsSize + 1][capacityGrams + 1];

    //I need to re-instance my list as an Integer List to use The values on my memoization structure
    List<Integer> weights = new ArrayList<>();
    for(ItemDTO item : packageDTO.getItems()){
      Double weightGrams = item.getWeight() * GRAMS_IN_KILO;
      weights.add(weightGrams.intValue());
    }

    // Loop to initially filled the
    // table with -1
    for(int i = 0; i < itemsSize + 1; i++)
      for(int j = 0; j < capacityGrams + 1; j++)
        mem[i][j] = -1;

    List<Integer> optimalChoice = new ArrayList<>();
    packDPRec(capacityGrams, weights, packageDTO.getItems(), weights.size(), mem, optimalChoice);
    return optimalChoice;
  }

  /** A recursive Top-Down continuation on the previous init function
    *
    * @param capacity: The capacity of the package
    * @param weights: The items weights (same order as items)
    * @param items: The items list
    * @param n: The size of items
    * @param mem: memoization structure
    * @param optimalChoice = A list where the optimal solution will be stored
   */
  private static Integer packDPRec(Integer capacity, List<Integer> weights, List<ItemDTO> items, Integer n, Integer[][] mem, List<Integer> optimalChoice) {
    // Base condition
    if (n == 0 || capacity <= 0)
      return 0;

    if (mem[n][capacity] != -1) {
      return mem[n][capacity];
    }

    if (weights.get(n - 1) > capacity) {
      // Store the value of function call
      // stack in table before return
      List<Integer> subOptimalChoice = new ArrayList<>();
      mem[n][capacity] = packDPRec(capacity, weights, items, n - 1, mem, subOptimalChoice);
      optimalChoice.addAll(subOptimalChoice);
      return mem[n][capacity];
    } else {
      // Return value of table after storing
      List<Integer> optimalChoiceIncluded = new ArrayList<>();
      List<Integer> optimalChoiceExcluded = new ArrayList<>();
      Integer valueIncluded = items.get(n - 1).getValue() + packDPRec(capacity - weights.get(n - 1), weights, items, n - 1, mem, optimalChoiceIncluded);
      Integer valueExcluded = packDPRec(capacity, weights, items, n - 1, mem, optimalChoiceExcluded);
      if (valueIncluded > valueExcluded) {
        optimalChoice.addAll(optimalChoiceIncluded);
        optimalChoice.add(items.get(n - 1).getId());
        mem[n][capacity] = valueIncluded;
      }else{
        optimalChoice.addAll(optimalChoiceExcluded);
        mem[n][capacity] = valueExcluded;
      }
      return mem[n][capacity];
    }
  }

  /** Replaces certain characters that shouldn't be displayed in the solution
   *
   * @param sb: StringBuilder with the solution about to be returned
   * @return The problem's solution trimmed
   */
  private static String trimSolution(StringBuilder sb) {
      return sb.toString().replace("[", "").replace("]","").trim();
  }

  /** Replaces empty solutions with a '-' char
   *
   * @param sb: StringBuilder with some of the solutions
   * @return The problem's solution without '-'
   */
  private static StringBuilder parseEmptySolution(StringBuilder sb, List<Integer> packDPSolution) {
    sb.append(packDPSolution.size() != 0 ? packDPSolution.toString() : "-");
    sb.append("\n");
    return sb;
  }
}
