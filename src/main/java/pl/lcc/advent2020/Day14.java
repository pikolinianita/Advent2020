/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.UnaryOperator;





public class Day14 {

    Mask mask;
    
    HashMap<String,Long> registers;
    
    private final List<String> input;
 
    public Day14(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
           input = sc.useDelimiter("\n")
                   .tokens()
                   .toList();
        }
    registers = new HashMap<>();    
    }
    
    Day14(List<String> input){
        this.input = input;
        registers = new HashMap<>();   
    }
    
    void calculate(){
        Utils.printResult("Template",part1(),part2());
    }
    
    long part1() {
        input.forEach(this::process);
        return getP1Answer();
    }

    int part2() {
        return -1;
    }

    private long getP1Answer() {
        System.out.println(registers);
        return registers.values().stream().mapToLong(l -> l).sum();       
                }

    private void process(String line) {
        if(line.charAt(1)=='a'){
            mask = new Mask(line.substring(7));           
        } else {
            var numbers = line.split("(mem\\[)|(\\] = )");       
            var afterMask = mask.apply(BitSet.valueOf(new long[]{Long.valueOf(numbers[2])})).toLongArray()[0];           
            registers.put(numbers[1], afterMask);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
       new Day14("day14.txt").calculate(); //5786255997050 was too low
    }
    
  static class Mask implements UnaryOperator<BitSet> {
    BitSet andMask; //1110 X->1
    BitSet orMask;  //1000 X->0
    
    public Mask(String description){
        andMask = new BitSet(description.length());
        orMask = new BitSet(description.length());
        for(int i =0; i<description.length(); i++){       
            switch(description.charAt(i)){
                case '1' -> {
                    andMask.set(description.length() - i -1, true);
                    orMask.set(description.length() - i -1, true);}
                 case '0' -> {
                    andMask.set(description.length() - i -1, false);
                    orMask.set(description.length() - i -1, false);}
                  case 'X' -> {
                    andMask.set(description.length() - i -1, true);
                    orMask.set(description.length() - i -1, false);}
                  default -> throw new IllegalArgumentException("----"+description.charAt(i)+"----");
            }
        }            
    }

    @Override
    public BitSet apply(BitSet t) {
        t.and(andMask);
        t.or(orMask);
        return t;     
    }    
}
}
