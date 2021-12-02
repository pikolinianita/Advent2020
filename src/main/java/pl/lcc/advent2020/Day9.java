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

public class Day9 {    
    
    private static final long P2VALUE = 1639024365;
    int mask;
    long[] input;
    
    Day9(long[] inputArray, int mask) {
        input = inputArray;
        this.mask = mask;
    }
    
    public Day9(String path) throws FileNotFoundException {
        mask = 25;
        try ( Scanner sc = new Scanner(new File(path))) {           
            input = sc
                    .tokens()
                    .mapToLong(Long::parseLong)
                    .toArray();
        }
    }
    
    void calculate() {
        Utils.printResult("Day 9", part1(), part2(P2VALUE));
    }
    
    long part1() {
        for (int i = 0; i < input.length - mask; i++) {
            if (!verify(i)) {
                return input[i + mask];
            }
        }        
        return -1;
    }
    
    long part2(long sumValue) {
        int value = skipLarger(sumValue);
        while(true){
            var result = verifyRange(value,sumValue);
            if (result > 0) {
                return result;
            }
            value--;
        }        
    }
    
    long verifyRange(int initialPosition, long sumValue){
        int position = initialPosition;
        long sum = input[initialPosition];
        do {
            position--;
            sum+= input[position];            
        }
        while(sum < sumValue);
        return (sum == sumValue) ? processResults(position, initialPosition): -1;
    }
    
    private long processResults(int low, int high) {
        long min = input[low];
        long max = input[high]; 
        int counter = low;
        while(counter < high){
            counter++;
            if (input[counter] < min) {
                min = input[counter] ;
            } else if (input[counter] > max) {
                max = input[counter] ;
            }
        }
       return min + max;
    }
    
    boolean verify(int startPos) {
        long suspectedSum = input[mask + startPos];
        var subArray = Arrays.copyOfRange(input, startPos, startPos + mask);
        for (int i = 0; i < subArray.length; i++) {
            for (int j = 0; j < subArray.length; j++) {
                if (subArray[i] + subArray[j] == suspectedSum && i != j) {
                    return true;
                }
            }
        }        
        return false;        
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        new Day9("day9.txt").calculate();
    }

    int skipLarger(long sumValue) {
       int i = input.length-1;
       while(input[i]>sumValue){
           i--;
       }
       return i;
    }

    
}
