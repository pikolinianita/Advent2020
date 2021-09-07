/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
class Day5Test {
    
    public Day5Test() {
    }

    @Test
     void testParser1() {
        var par = new Parser("BFFFBBFRRR");
        assertEquals(70, par.row);
        assertEquals(7, par.column);
        assertEquals(567, par.getId());
        //BFFFBBFRRR: row 70, column 7, seat ID 567.

    }
    
    @Test
     void testParser2() {
        var par = new Parser("FFFBBBFRRR");
        assertEquals(14, par.row);
        assertEquals(7, par.column);
        assertEquals(119, par.getId());
    }
    
    @Test
     void testParser3() {
        var par = new Parser("BBFFBBFRLL");
        assertEquals(102, par.row);
        assertEquals(4, par.column);
        assertEquals(820, par.getId());
     }    
                
    @Test
     void testFindSeat(){
        var result = new Day5().findSeat(List.of(4,5,6,7,8,9,11,12,13,14));
        assertEquals(10,result);
    }
            
    
}
