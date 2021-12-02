/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
   
    String[] inputLines;
    
    Day2(){}
    
    public Day2(String path) throws FileNotFoundException{
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            inputLines = sc
                    .tokens()
                    .toArray(String[]::new);
        }
    }
      
    void calculate(){
        Utils.printResult("Day 2",part1(inputLines),part2(inputLines));
    }
    
    int part1(String[] input) {
      return (int) Arrays.stream(input)
              .map(PassVerifier::new)
              .filter(PassVerifier::isOK)
              .count();
    }

    int part2(String[] input) {
         return (int) Arrays.stream(input)
              .map(PassVerifier::new)
              .filter(PassVerifier::isOKpart2)
              .count();
    }
    
    static class PassVerifier{

    String splitPattern = " |-|: ";
    int min;
    int max;
    String letter;
    String pax;
    
    public PassVerifier(String line) {
        var splittedInput = line.split(splitPattern);
        min = Integer.parseInt(splittedInput[0]);
        max = Integer.parseInt(splittedInput[1]);
        letter = splittedInput[2];
        pax = splittedInput[3];
    }

    @Override
    public String toString() {
        return "PassVerifier{" +" OK? "+ isOK() + ", min=" + min + ", max=" + max + ", letter=" + letter + ", pax=" + pax + '}';
    }
    
    public boolean isOK(){        
        var count = pax.split(letter).length-1;  
        return (count >= min && count <= max);
    }
    
     public boolean isOKpart2(){
         return (pax.charAt(min-1) == letter.charAt(0)) ^ (pax.charAt(max-1) == letter.charAt(0));
     }
  }
}
