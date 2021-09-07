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
 * @author piko
 */
class Day6Test {

    String simpleInput = """
                  abcx
                  abcy
                  abcz
                  """;
    
    public Day6Test() {
    }

    @Test
    void testCountP1() {
       
        var result = new Day6().countForP1(simpleInput);
        assertEquals(6, result);
    }

    @Test
    void testPart1(){
       var result = new Day6().part1(groupInput);
       assertEquals(11, result);
    }
    private String[] groupInput = { "abc", 
        """
                             a
                             b
                             c
                              """,
        """
                             ab
                             ac
                             """,
        """
                              a
                              a
                              a
                              a
                              """,
        "b"};
    
    @Test
    void testCountP2() {
        var result = new Day6().countForP2(simpleInput);
        assertEquals(3, result);
    }
    
    @Test
    void testPart2(){
       var result = new Day6().part2(groupInput);
       assertEquals(6, result);
    }
}
