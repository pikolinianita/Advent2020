/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.FileNotFoundException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author piko
 */


public class Day11Test{
    
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
    
    int[] testArray;
    int[] fullArray;
    
    public Day11Test() {
        testArray = String.join("", testInput.split("\n")).chars().toArray();
        fullArray = String.join("", fullString.split("\n")).chars().toArray();
            }
     
    @Test
    void testCountGuys() throws FileNotFoundException
    {
        assertEquals(71, new Day11(testArray).countChar('L'));
    }
   
    @Test 
    void testGet(){
       var day = new Day11(testArray);
       assertEquals('L', day.get(0,0));
       assertEquals('.', day.get(0,1));
       assertEquals('.', day.get(6,0));
       assertEquals('L', day.get(2,4));
       assertEquals('L', day.get(9,9));
       assertEquals('0', day.get(-1,4));
       assertEquals('0', day.get(10,4));
       assertEquals('0', day.get(1,10));
       assertEquals('0', day.get(1,-1));
    }
    
    @Test   
    public void testP1FindNewOnEmpty() {
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
    public void testP1FindNewOnFull() {
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
    public void testP1(){
         var day = new Day11(testArray);
         assertEquals(37, day.part1());
    }
    
    @Test
    public void testSweep() {
        var day = new Day11(testArray).setLogic(new Day11.P1Logic());
        System.out.println(day.toBoardString());
        day.sweepBoard();
        System.out.println(day.toBoardString());
        day.sweepBoard();
        System.out.println(day.toBoardString());
        day.sweepBoard();
        System.out.println(day.toBoardString());
    }        
}

