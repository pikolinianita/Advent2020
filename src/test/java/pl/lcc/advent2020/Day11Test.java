/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.FileNotFoundException;
import org.assertj.core.api.SoftAssertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
 class Day11Test {
    
    String testInput = """
                       L.LL.LL.LL
                       LLLLLLL.LL
                       L.L.L..L..
                       LLLL.LL.LL
                       L.LL.LL.LL
                       L.LLLLL.LL
                       ..L.L.....
                       LLLLLLLLLL
                       L.LLLLLL.L
                       L.LLLLL.LL""";
    
    String fullString = """
                       #.##.##.##
                       #######.##
                       #.#.#..#..
                       ####.##.##
                       #.##.##.##
                       #.#####.##
                       ..#.#.....
                       ##########
                       #.######.#
                       #.#####.##""";
    
    String p2Vis8 = """
                    .......#.
                    ...#.....
                    .#.......
                    .........
                    ..#L....#
                    ....#....
                    .........
                    #........
                    ...#.....""";
    
    String p2Vis0 = """
                    .##.##.
                    #.#.#.#
                    ##...##
                    ...L...
                    ##...##
                    #.#.#.#
                    .##.##.""";
    
    int[] testArray;
    int[] fullArray;
    
    public Day11Test() {
        testArray = String.join("", testInput.split("\n")).chars().toArray();
        fullArray = String.join("", fullString.split("\n")).chars().toArray();
    }
    
    @Test
    void testCountGuys() throws FileNotFoundException {
        //assertEquals
        assertThat(new Day11(testArray).countChar('L')).isEqualTo(71);
    }
    
    @Test    
    void testGet() {
        var day = new Day11(testArray);
        assertThat(day.get(0, 0)).isEqualTo('L');
        assertThat(day.get(0, 1)).isEqualTo('.');
        assertThat(day.get(6, 0)).isEqualTo('.');
        assertThat(day.get(2, 4)).isEqualTo('L');
        assertThat(day.get(9, 9)).isEqualTo('L');
        assertThat(day.get(-1, 4)).isEqualTo('0');
        assertThat(day.get(10, 4)).isEqualTo('0');
        assertThat(day.get(1, 10)).isEqualTo('0');
        assertThat(day.get(1, -1)).isEqualTo('0');

    }
    
    @Test    
    void testP1FindNewOnEmpty() {
        var logic = new Day11.P1Logic();
        var day = new Day11(testArray).setLogic(logic);
        
        SoftAssertions softly = new SoftAssertions();        
        softly.assertThat(logic.findNewValue(0, 1)).isEqualTo('.');
        softly.assertThat(logic.findNewValue(0, 0)).isEqualTo('#');
        softly.assertThat(logic.findNewValue(9, 9)).isEqualTo('#');
        softly.assertThat(logic.findNewValue(1, 0)).isEqualTo('#');        
        softly.assertAll();
    }
    
    @Test    
    void testP1FindNewOnFull() {
        var logic = new Day11.P1Logic();
        var day = new Day11(fullArray).setLogic(logic);
        
        SoftAssertions softly = new SoftAssertions();        
        softly.assertThat(logic.findNewValue(0, 1)).isEqualTo('.');
        softly.assertThat(logic.findNewValue(0, 0)).isEqualTo('#');
        softly.assertThat(logic.findNewValue(3, 3)).isEqualTo('L');
        softly.assertThat(logic.findNewValue(9, 9)).isEqualTo('#');
        softly.assertThat(logic.findNewValue(1, 0)).isEqualTo('#');        
        softly.assertAll();
    }
    
    @Test
    void testP1() {
        var day = new Day11(testArray);
        assertThat(day.part1()).isEqualTo(37);
    }
    
    @Test
    void testP2() {
        var day = new Day11(testArray);
        assertThat(day.part2()).isEqualTo(26);
    }
    
    @Test
    void testNeighboursP2_8Neighbours() {
        var inpArray = String.join("", p2Vis8.split("\n")).chars().toArray();
        var day = new Day11(inpArray, 9, 9).setLogic(new Day11.P2Logic());
        var result = day.ai.getNCount(4, 3);
        assertThat(day.get(4, 3)).isEqualTo('L');
        assertThat(result).isEqualTo(8);
      
    }
    
    @Test
    void testNeighboursP2_0Neighbours() {
        var inpArray = String.join("", p2Vis0.split("\n")).chars().toArray();
        var day = new Day11(inpArray, 7, 7).setLogic(new Day11.P2Logic());
        var result = day.ai.getNCount(3, 3);
        assertThat(day.get(3, 3)).isEqualTo('L');
        assertThat(result).isZero();
        
    }
    
}
