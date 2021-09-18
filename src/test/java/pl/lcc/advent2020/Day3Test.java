/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
class Day3Test{
    
    String input = """
                   ..##.......
                   #...#...#..
                   .#....#..#.
                   ..#.#...#.#
                   .#...##..#.
                   ..#.##.....
                   .#.#.#....#
                   .#........#
                   #.##...#...
                   #...##....#
                   .#..#...#.#
                   """;     
    
    private final String[] inputLines;
    
    public Day3Test() {
        try (Scanner sc = new Scanner(input)) {
            sc.useDelimiter("\n");
            inputLines = sc
                    .tokens()
                    .toArray(String[]::new);
        }
    }

    @Test
    void testPart1() {
        var day = new Day3(inputLines);
        var result = day.part1();
        assertEquals(7, result); 
    }
    
    @Test
    void testGetPos(){
        var day = new Day3(inputLines);
        var result = day.getPos(0, 0);
        assertEquals('.', result);
        assertEquals('#', day.getPos(0, 1));
        assertEquals('#', day.getPos(11, 1));
    }
    
    @Test
    void testPart2Simple(){
            var day = new Day3(inputLines);
            assertEquals(2, day.countTrees(1, 1));
            assertEquals(3, day.countTrees(5, 1));
            assertEquals(4, day.countTrees(7, 1));
    }
    
    @Test
    void testPart2Complex(){
            var day = new Day3(inputLines);
            assertEquals(2, day.countTrees(1, 2));
    }
    
    @Test
    void testPart2(){
         var day = new Day3(inputLines);
        var result = day.part2();
        assertEquals(336, result); 
    }
}
