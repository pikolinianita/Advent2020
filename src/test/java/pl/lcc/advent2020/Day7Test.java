/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author piko
 */
public class Day7Test {
    
    String example = """
                     light red bags contain 1 bright white bag, 2 muted yellow bags.
                     dark orange bags contain 3 bright white bags, 4 muted yellow bags.
                     bright white bags contain 1 shiny gold bag.
                     muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
                     shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
                     dark olive bags contain 3 faded blue bags, 4 dotted black bags.
                     vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
                     faded blue bags contain no other bags.
                     dotted black bags contain no other bags.
                     """;
    
    String anotherExample = """
                            shiny gold bags contain 2 dark red bags.
                            dark red bags contain 2 dark orange bags.
                            dark orange bags contain 2 dark yellow bags.
                            dark yellow bags contain 2 dark green bags.
                            dark green bags contain 2 dark blue bags.
                            dark blue bags contain 2 dark violet bags.
                            dark violet bags contain no other bags.
                            """;
    
    public Day7Test() {
    }

    @Test
    public void testProcessLine() {
       var d = new Day7().processLine("light red bags contain 1 bright white bag, 2 muted yellow bags.");
       assertEquals(3, d.size());
    }
    
     @Test
    public void testProcessLineBagempty() {
       var d = new Day7().processLine("dotted black bags contain no other bags.");
       assertTrue(d.isEmpty());
    }
    
     @Test
    public void testProcessLine2() {
       var d = new Day7().processLine2("light red bags contain 1 bright white bag, 2 muted yellow bags.");
       System.out.println(d);
       assertEquals(3, d.size());
       
    }
    
     @Test
    public void testProcessLineBagempty2() {
       var d = new Day7().processLine2("dotted black bags contain no other bags.");
       assertTrue(d.isEmpty());
    }
    
    @Test
    public void testPart1(){
        var day = new Day7(); 
        var exampleLines = example.lines().toArray(String[]::new);
        //System.out.println(Arrays.toString(day.lines));
        assertEquals(4, day.part1(exampleLines));        
    }
    
    @Test
    public void testPart2(){
        var day = new Day7(); 
        var exampleLines = example.lines().toArray(String[]::new);
        //System.out.println(Arrays.toString(day.lines)); 
        assertEquals(32, day.part2(exampleLines));        
    }
    
    @Test
    public void testAnotherPart2(){
        var day = new Day7(); 
        var exampleLines = anotherExample.lines().toArray(String[]::new);
        //System.out.println(Arrays.toString(day.lines));
        assertEquals(126, day.part2(exampleLines));        
    }
    
    @Test
    public void testManagerOne(){
        Map<String, List<String>> db = new HashMap<>();
        db.put("Gold", List.of("Red","Blue"));
        var manager = new Day7.BagManager(db);
        assertEquals(2, manager.findColorsP1("Gold"));
    }
    
    @Test
    public void testManagerTwo(){
        Map<String, List<String>> db = new HashMap<>();
        db.put("Gold", List.of("Red","Blue"));
        db.put("Red", List.of("Blue", "Green"));
        var manager = new Day7.BagManager(db);
        assertEquals(3, manager.findColorsP1("Gold"));
    }
    
    @Test
    public void FilledBagTest() {
        
        // given
        var input = List.of("light red", "1 bright white", "2 muted yellow");
        // when
       var result = new Day7.FilledBag(input);
        // then
        assertEquals(2, result.bags.size());
        assertEquals("light red", result.kindOfBag);
        assertTrue(result.bags.containsKey("bright white"));
    }
    
    
    
}
 