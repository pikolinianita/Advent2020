/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Parser {
    
    int row, column;
    
    Parser (String s){
        String rowString = s.substring(0, 7).replace("B","1").replace("F", "0");
        String columnString =  s.substring(7, 10).replace("R","1").replace("L", "0");
        row = Integer.parseInt(rowString, 2);
        column = Integer.parseInt(columnString, 2);
    }
    
    int getId(){
        return row * 8 + column;
    }
    
}

public class Day5 {
   
    String[] entries;
    
    Day5(){};
    
    Day5(String path) throws FileNotFoundException{
      try (java.util.Scanner sc = new Scanner(new File(path))) {
          entries = sc.tokens().toArray(String[]::new);
        }
     };
      
    void calculate(){
        System.out.println("Day 5 result:");
        System.out.println("\t part1: " + part1(entries));
        System.out.println("\t part2: " + part2(entries));
    }
    
    int part1(String[] input) {
      return Arrays.stream(input)
              .map(Parser::new)
              .mapToInt(Parser::getId)
              .max()
              .orElse(-1);              
    }

    int part2(String[] input) {
        var list = Arrays.stream(input)
              .map(Parser::new)
              .map(Parser::getId)
              .toList();
        return findSeat(list);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
         new Day5("day5.txt").calculate();
    }

    int findSeat(List<Integer> orgList) {
       var list = new ArrayList<Integer>(orgList);
       Collections.sort(list);
       int i = 0;
       while(true)
       {
            if((list.get(i+1)-list.get(i))==2){
               return list.get(i) + 1;
           }
           i++;
       }
    }
}
