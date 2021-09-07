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
import java.util.Scanner;

class AdapterManager {

    int[] adapters;

    int ones, threes, twos;

    AdapterManager(int[] inputSmall) {
        adapters = inputSmall;
        Arrays.sort(adapters);
        threes = 1;  //from you to charger
    }

    int threes() {
        return threes;
    }

    int twos() {
        return twos;
    }

    int ones() {
        return ones;
    }

    void calculate() {
        addDiff(adapters[0]);
        for (int i = 0; i < adapters.length - 1; i++) {
            var diff = adapters[i + 1] - adapters[i];
            addDiff(diff);
        }
    }

    private void addDiff(int diff) {
        switch (diff) {
            case 1 -> ones++;
            case 2 -> twos++;
            case 3 ->  threes++;
            default -> throw new IllegalStateException("Wrong distance");
        }
    }

    @Override
    public String toString() {
        return "AdapterManager{" + "adapters=" + Arrays.toString(adapters) + ", ones=" + ones + ", threes=" + threes + ", twos=" + twos + '}';
    }
}

class AdvancedAdapterManager{

    static long calculateCombinations(int size) {
        return switch(size){
            case 1,2 -> 1; 
            case 3->  2;
            case 4-> 4;
            case 5-> 7;
            default -> {throw new IllegalStateException("Wrong size: " + size);}
        };
    }

    int[] adapters;
    
    AdvancedAdapterManager(int[] input) {
     adapters = new int[input.length+2];
     System.arraycopy(input, 0, adapters, 1, input.length);
     adapters[0] = Integer.MAX_VALUE;
     Arrays.sort(adapters);
     adapters[adapters.length-1]=adapters[adapters.length-2]+3;
}     
    
    ArrayList<int[]> findAreas(){
        int lower = 0;
        int higher;
        ArrayList<int[]> arr = new ArrayList<>();
        for (int i = 0; i < adapters.length-1; i++){
            var diff = adapters[i + 1] - adapters[i];
            if (diff==3) {
                arr.add(Arrays.copyOfRange(adapters, lower, i+1));
                lower = i+1;
            }
        }
    return arr;
    }
    
}
public class Day10 {

    int[] input;

    Day10() {
    }

    Day10(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
            input = sc.tokens()
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    void calculate() {
        Utils.printResult("Day 10",part1(input),part2(input));
    }

    int part1(int[] input) {
        var adapter = new AdapterManager(input); 
        adapter.calculate();
        return adapter.ones() * adapter.threes();
    }

    long part2(int[] input) {
       return new AdvancedAdapterManager(input).findAreas()
                .stream()
                .mapToInt(a -> a.length)
                .mapToLong(AdvancedAdapterManager::calculateCombinations)
                .reduce((a,b) -> a * b)
                .getAsLong();
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Day10("Day10.txt").calculate();
    }
}
