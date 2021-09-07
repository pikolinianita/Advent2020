/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import static java.util.stream.Collectors.*;

public class Day6 {
   
    String[] groups;
    
    Day6(){}
    
    Day6 (String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n\n");
            groups = sc.tokens().toArray(String[]::new);
        }  
    }
      
    void calculate(){
       Utils.printResult("Day 6",part1(groups),part2(groups));
    }
    
    long part1(String[] input) {
      return  Arrays.stream(input)
                .mapToLong(this::countForP1)
                .sum();
    }

    long part2(String[] input) {
       return Arrays.stream(input)
                .mapToLong(this::countForP2)
                .sum();
    }
    
    long countForP1(String group){
      return group.chars()              
              .filter(x -> (x > 96))
              .distinct()
              .count();
    }
    
     long countForP2(String group){
        String[] pax = group.split("\n");
        if (pax.length == 1) {return pax[0].length();}
        Set<Integer> resultSet = pax[0]
                .chars()
                .filter(x -> (x > 96))
                .boxed()
                .collect(toCollection(HashSet::new));
        for (int i = 1; i<pax.length; i++){
            Set<Integer> testSet = pax[i].chars()
                    .boxed()
                    .collect(toCollection(HashSet::new));
            resultSet.removeIf(el -> !testSet.contains(el));
        }
        return resultSet.size();
     }
     
}
