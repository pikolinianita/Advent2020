/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
public class Day15Test {
    
    public Day15Test() {       
    }
    
    @Test
    public void testGame() {
        var d = new Day15();
        var day =  d.new Game().setInput(new int[]{3,1,2});
        while (day.turn<2020){
            day.findNext();
        }
        assertThat(day.turn).isEqualTo(2020);
        assertThat(day.last).isEqualTo(1836);
    }
    
    @Test
    void testPart1(){
        var day = new Day15();
        assertThat(day.part1(new int[]{3,1,2})).isEqualTo(1836);
        assertThat(day.part1(new int[]{3,2,1})).isEqualTo(438);
        assertThat(day.part1(new int[]{1,3,2})).isEqualTo(1);
    }
    
    @Test
    void testPart2(){
        var day = new Day15();
        assertThat(day.part2(new int[]{3,1,2})).isEqualTo(362);       
    }
    
}
