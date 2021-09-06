/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author piko
 */
public class Day13Test {
    
    int timeStamp = 939;
    int[] busses = {7,13,59,31,19};
    
    public Day13Test() {
    }

    @Test
    public void testPart1() {
       int result = new Day13().findBusSolution(timeStamp, busses);
       assertEquals(295, result);
    }
    
    @Test
    public void testInput() throws FileNotFoundException{
        var d = new Day13("day13.txt");
    }
    
}
