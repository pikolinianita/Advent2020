/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
 class Day1Test {
    
    int[] testData; 
    
    public Day1Test() {
       testData = new int[] {1, 2, 1721, 979, 366, 299, 675, 1456, 2021, 200002};
       Arrays.sort(testData);
    }
    
    @Test
     void testPart1() {
       var day = new Day1();       
       var result = day.part1(testData);       
        assertEquals(514579, result, "Day 1 multiplicated");
    }
     
    @Test
    void testFingPair(){
         var day = new Day1();
         day.sortedInput = testData;
         var result = day.findPair(2029);
         assertEquals(-1, result);
         
    }

   @Test
    void testPart2(){
       var day = new Day1();
       var result = day.part2(testData);       
       assertEquals(241861950, result, "Day 1 multiplicated part 2");
   }
    
}
