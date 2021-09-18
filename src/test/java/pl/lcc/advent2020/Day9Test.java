/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



/**
 *
 * @author piko
 */
class Day9Test{
    
    long[] inputArray = {20, 2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,1,21,22,23,24,25,45,-1};
    
    long[] input5 = {35,20,15,25,47,40,62,55,65,95,102,117,150,182,127,219,299,277,309,576};
    
    public Day9Test() {
       
    }
    
    @Test
    void testFromZero() {
        var day = new Day9(inputArray,25);
        assertTrue(day.verify(0));
    }
    
    @Test
    void testFromOne(){
    inputArray[26]=26;    
     var day = new Day9(inputArray,25);
     assertTrue(day.verify(1));
    }
    
    @Test
    void testFromOneWithLast(){
    inputArray[26]=64;    
     var day = new Day9(inputArray,25);
     assertTrue(day.verify(1));
    }
    
    @Test
    void testFromOnewithFalse(){
    inputArray[26]=65;    
     var day = new Day9(inputArray,25);
     assertFalse(day.verify(1));
    }
    
    @Test
    void testInputFive(){
         var day = new Day9(input5,5);
         assertEquals(127,day.part1());
    }
    
    @Test
    void testInputFivep2(){
         var day = new Day9(input5,5);
         assertEquals(62,day.part2(127));
    }
    
    @Test
    void skipTooLargeTest(){
        var day = new Day9(input5,5);
        assertEquals(14, day.skipLarger(150));
    }
}
