/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Day1 {
    
   
    int[] sortedInput;
    
    public Day1(String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path))) {
            sortedInput = sc.tokens()
                    .mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();
        }
    }
    
    Day1(){}
    
    static final int DAY1_VALUE = 2020;
    
    int part1() {                 
        return findPair( DAY1_VALUE);
    }
    
    int part1(int[] testedInput) {  
        sortedInput = testedInput;
        return part1();
    }
   
    int findPair(int desiredValue) {
        int lower = 0;
        int higher = sortedInput.length - 1;
        while (notCorrectSum(lower, higher, desiredValue)) {
            if (sortedInput[lower] + sortedInput[higher] > desiredValue) {
                higher--;
            } else {
                lower++;
            } 
            if( lower >= higher) {
                return -1;
            }
        }     
        return sortedInput[lower] * sortedInput[higher];
    }

    private boolean notCorrectSum(int lower, int higher, int desiredValue) {
        return sortedInput[lower] + sortedInput[higher] != desiredValue;
    }
    
    void calculate(){        
        Utils.printResult("Day 1", part1(), part2());  
    }

     int part2(int[] testedInput) {  
        sortedInput = testedInput;
        return part2();
    }
    
    int part2() {
        int third = -1;
        int twoMultipled = -1;
        while(twoMultipled<0){
           third++;
           twoMultipled = findPair(DAY1_VALUE-sortedInput[third]);            
        }       
        return twoMultipled * sortedInput[third];
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        new  Day1("day1.txt").calculate();
    }
    
}
