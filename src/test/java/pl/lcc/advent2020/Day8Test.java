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
 class Day8Test {
    
    String input = """
                   nop +1
                   acc +1
                   jmp +4
                   acc +3
                   jmp -3
                   acc -99
                   acc +1
                   jmp -4
                   acc +6
                   """;
    
    String inputp2 = """
                     nop +0
                     acc +1
                     jmp +4
                     acc +3
                     jmp -3
                     acc -99
                     acc +1
                     nop -4
                     acc +6
                     """;
    
    public Day8Test() {
    }
    
    @Test
     void testRecord() {
        var obj = new Day8.Command("acc -99");
        assertEquals("acc", obj.command());
        assertEquals(-99, obj.value());
    }
    
    @Test
     void part1Test() {        
        // given
         var exampleLines = input.lines().toArray(String[]::new);
        // when
        var result = new Day8().part1(exampleLines);
        // then
        assertEquals(5, result);
    }
    
    @Test
     void testP1AllOk() {        
        // given
        var exampleLines = inputp2.lines().toArray(String[]::new);
        // when
        var result = new Day8().part1(exampleLines);
        // then
        assertEquals(8, result);        
    }
    
    @Test
     void testP2() {        
        // given
        var exampleLines = input.lines().toArray(String[]::new);
        // when
        var result = new Day8().part2(exampleLines);
        // then
        assertEquals(8, result);        
    }
    
}
