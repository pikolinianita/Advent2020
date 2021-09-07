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

record BusAndTime(int bus, int time){

    int getAnswerP1() {return bus * time;}
};

public class Day13 {
   
    Day13(){};
    
    int timeStamp ;
    int[] busses;
    
    Day13(String path) throws FileNotFoundException{
         try ( Scanner sc = new Scanner(new File(path))) {
         var  input = sc.tokens().toList();
         timeStamp = Integer.parseInt(input.get(0));
         busses = Arrays.stream( input.get(1).split(","))
                 .filter(s -> !"x".equals(s))
                 .mapToInt(Integer::parseInt)
                 .toArray();
         
         System.out.println(timeStamp);
         System.out.println(Arrays.toString(busses));
          //System.out.println(input);
          }
    }
      
    void calculate(){
        Utils.printResult("Day 13",part1(),part2(null));
    }
    
    int part1() {
        return findBusSolution(timeStamp, busses);
    }

    int part2(int[] input) {
        return -1;
    }

    int findBusSolution(int timeStamp, int[] busses) {
       return  Arrays.stream(busses)
                 .mapToObj(bus -> new BusAndTime( bus, bus - (timeStamp % bus)))
                 .sorted((a,b)-> a.time() - b.time())
                 .findFirst()
                 .get()
                 .getAnswerP1();
        
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Day13("day13.txt").calculate();
    }
}
