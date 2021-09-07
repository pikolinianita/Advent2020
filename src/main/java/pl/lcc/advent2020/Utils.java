/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

/**
 *
 * @author lcc
 */
public class Utils {
    
    private Utils() {
         throw new IllegalStateException("Utility class");
  }
    
    static void printResult(String name, long part1, long part2){
        System.out.println(name + " result:");
        System.out.println("\t part1: " + part1);
        System.out.println("\t part2: " + part2);
    }
}
