/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author piko
 */
public class Day10Test {

    int[] inputSmall = {16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4};
    
    int[] inputLarge = {28,33,18,42,31,14,46,20,48,47,24,23,49,45,19,38,39,11,1,32,25,35,8,17,7,9,4,2,34,10,3};

    public Day10Test() {
    }

    @Test
    public void testAdapter() {
        var adapter = new AdapterManager(inputSmall);
        adapter.calculate();
        System.out.println(adapter.toString());
        var result = adapter.threes() * adapter.ones();
        assertEquals(35, result);
        assertEquals(12, adapter.threes() + adapter.ones() + adapter.twos());
    }
    
    @Test
    public void findAreasSmall() {
        var result = new AdvancedAdapterManager(inputSmall).findAreas()
                .stream()
                .mapToInt(a -> a.length)
                .mapToLong(AdvancedAdapterManager::calculateCombinations)
                .reduce((a,b) -> a * b)
                .getAsLong();
        assertEquals(8, result);
        
    }
    
    @Test
    public void findAreasLrg()  {
        var result = new AdvancedAdapterManager(inputLarge).findAreas()
                .stream()
                .mapToInt(a -> a.length)
                .mapToLong(AdvancedAdapterManager::calculateCombinations)
                .reduce((a,b) -> a * b)
                .getAsLong();
        assertEquals(19208, result);
    }
}
