/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author piko
 */
public class Day16Test {
    
    public Day16Test() {       
    }
    
    @Test
    public void testSingle() {
        var mr = new Day16().new MultiRange();
        mr.addRange(new Day16.Range("", 4, 10));
       assertThat(mr.size()).isEqualTo(7); 
    }
    
    @Test
    public void testTwoDis() {
         var mr = new Day16().new MultiRange();
         mr.addRange(new Day16.Range("", 4, 10)).addRange(new Day16.Range("", 12, 15));
         assertThat(mr.size()).isEqualTo(11); 
    }
    
    @Test
    public void testTwoFirstL() {
        var mr = new Day16().new MultiRange();
         mr.addRange(new Day16.Range("", 4, 10)).addRange(new Day16.Range("", 5, 9));
         assertThat(mr.size()).isEqualTo(7); 
    }
    
    @Test
    public void testTwoSecL() {
         var mr = new Day16().new MultiRange();
         mr.addRange(new Day16.Range("", 4, 10)).addRange(new Day16.Range("", 3, 11));
         assertThat(mr.size()).isEqualTo(9); 
    }
    
    @Test
    public void testJoinFirstL() {
        var mr = new Day16().new MultiRange();
         mr.addRange(new Day16.Range("", 4, 10)).addRange(new Day16.Range("", 5, 11));
         assertThat(mr.size()).isEqualTo(8); 
    }
    
    @Test
    public void testJoinSecL() {
         var mr = new Day16().new MultiRange();
         mr.addRange(new Day16.Range("", 4, 10)).addRange(new Day16.Range("", 3, 7));
         assertThat(mr.size()).isEqualTo(8); 
    }
}
