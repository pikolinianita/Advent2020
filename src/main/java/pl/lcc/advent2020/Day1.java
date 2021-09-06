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


public class Day1 {

    String inputPath;
    
    int[] parsedInput;
    
    public Day1(String path) throws FileNotFoundException {
        inputPath = path;
        try (Scanner sc = new Scanner(new File(path))) {
            parsedInput = sc.tokens()
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }
    
    Day1(){};
    
    int DAY1_value = 2020;
    
    int part1(int[] input) {
        var sortedInput = input;
        Arrays.sort(sortedInput);
        int lower = 0;
        int higher = sortedInput.length - 1;
        while (sortedInput[lower] + sortedInput[higher] != 2020) {
            if (sortedInput[lower] + sortedInput[higher] > 2020) {
                higher--;
            } else {
                lower++;
            }
        }

        return sortedInput[lower] * sortedInput[higher];
    }
    
    void calculate(){
        System.out.println("Day 1 result:");
        System.out.println("\t part1: " + part1(parsedInput));
        System.out.println("\t part2: " + part2(parsedInput));
    }

    int part2(int[] input) {
        return -1;
    }
}
