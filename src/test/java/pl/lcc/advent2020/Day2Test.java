/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
 class Day2Test {
    
    public Day2Test() {
    }

    @Test
     void testParse() {
        var verifier =  new Day2.PassVerifier("1-3 a: abcde");
        assertEquals(1, verifier.min);
        assertEquals(3, verifier.max);
        assertEquals("a", verifier.letter);
        assertEquals("abcde", verifier.pax);
        assertEquals(true, verifier.isOK());
    }
    
    //@Test
     void testParts1(){
        String[] testList = {"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
        var result = new Day2().part1(testList);
        assertEquals(2,  result);
       
    }
    
     @Test
     void testParts2(){
        String[] testList = {"1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"};
        var result = new Day2().part2(testList);
        assertEquals(1, result);
    }    
}
