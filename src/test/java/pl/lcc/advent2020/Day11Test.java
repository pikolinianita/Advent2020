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
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 *
 * @author piko
 */

/*
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
    void testSomeMethod() {
        
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
    public void testFindNewOnEmpty() {
        var day = new Day11(testArray);
        
        SoftAssertions softly = new SoftAssertions();        
        softly.assertThat(day.findNewValue(0, 1)).isEqualTo('.');
        softly.assertThat(day.findNewValue(0, 0)).isEqualTo('#');
        softly.assertThat(day.findNewValue(9, 9)).isEqualTo('#');
                
        softly.assertAll();
    }
    
    @Test   
    public void testFindNewOnFull() {
        var day = new Day11(fullArray);
        
        SoftAssertions softly = new SoftAssertions();        
        softly.assertThat(day.findNewValue(0, 1)).isEqualTo('.');
        softly.assertThat(day.findNewValue(0, 0)).isEqualTo('#');
        softly.assertThat(day.findNewValue(3, 3)).isEqualTo('L');
        softly.assertThat(day.findNewValue(9, 9)).isEqualTo('#');
                
        softly.assertAll();
    }
    
    @Test
    public void testp1(){
         var day = new Day11(testArray);
         assertEquals(37, day.part1());
    }
    
    @Test
    public void testSweep() {
        var day = new Day11(testArray);
        System.out.println(Arrays.toString(day.input));
        day.sweepBoard();
        System.out.println(Arrays.toString(day.input));
    }
    
    @Test
     void testGetCount(){
        var day = new Day11(fullArray);
        System.out.println(day.getNCount(3, 3));
    }
     
     
}

class BoxDisplay{
    
    int width;
    Consumer<String> output;           
    
    BoxDisplay(int width){
        this.width = width;
        output = System.out::println;
    }
    * }
    * /
    
   /* void display(int[] data) {
        int size = data.length/width;
        IntStream.range(0, size)
                .mapToObj(n-> Arrays.copyOfRange(data, n*width, (n+1) * width))
                .map(arr -> String.join(","))
                .
    }
    */
