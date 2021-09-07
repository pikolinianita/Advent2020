/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
public class Day1Test {
    
    int[] testData = {1, 2, 1721, 979, 366, 299, 675, 1456, 2021, 200002 };
    
    public Day1Test() {
       
    }
    
    @Test
    public void testPart1() {
       var day = new Day1();
       var result = day.part1(testData);       
        assertEquals(514579, result, "Day 1 multiplicated");
    }

   @Test
   public void testConstr() throws FileNotFoundException{
       var day = new Day1("day1.txt");
       assertEquals(200, day.parsedInput.length);
   }
   
  // @Test
   public void testPart2(){
       var day = new Day1();
       var result = day.part2(testData);       
       assertEquals(241861950, result, "Day 1 multiplicated");
   }
    
}
