/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.BitSet;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
public class Day14Test{
    
    List<String> commands = List.of("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X","mem[8] = 11","mem[7] = 101","mem[8] = 0");

    String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X";

    @Test
    void testSomeMask() {
       var input = BitSetfromString("0101");
       var sut = new Day14.Mask("0XX1");
       var result = sut.apply(input).toString();
       assertThat(result).isEqualTo("{0, 2}");
    }
    
    @Test
    void testTransparentMask() {
       var input = BitSetfromString("0101");
       var sut = new Day14.Mask("XXXX");
       var result = sut.apply(input).toString();
       assertThat(result).isEqualTo("{0, 2}");
    }

    @Test
    void testZeroMask() {
       var input = BitSetfromString("0101");
       var sut = new Day14.Mask("0000");
       var result = sut.apply(input).toString();
       assertThat(result).isEqualTo("{}");
    }
    
     @Test
    void testFullMask() {
       var input = BitSetfromString("0101");
       var sut = new Day14.Mask("1111");
       var result = sut.apply(input).toString();
       assertThat(result).isEqualTo("{0, 1, 2, 3}");
    }
    
    @Test
    void testMaskToLong() {
       var input = BitSetfromString("0101");
       var sut = new Day14.Mask("1111");       
       var result = sut.apply(input).toLongArray()[0];
       var resStr = Long.toBinaryString(sut.apply(input).toLongArray()[0]);
       assertThat(result).isEqualTo(15);
       assertThat(resStr).isEqualTo("1111");
    }
    
    @Test
    void testCorrValues(){
       var input = BitSetfromLong(11);
       var sut = new Day14.Mask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
       var result = sut.apply(input).toLongArray()[0];
       assertThat(result).isEqualTo(73);
    }
    
   @Test
    void testOtherValues(){
       var input = BitSetfromLong(101);
       var sut = new Day14.Mask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
       var result = sut.apply(input).toLongArray()[0];
       assertThat(result).isEqualTo(101);
    }   
    
    @Test
    void p1Test(){
       var result = new Day14(commands).part1();
       assertThat(result).isEqualTo(165);
    }    
    
    private BitSet BitSetfromLong(long l){
             return BitSet.valueOf(new long[]{l});
    }
    
    
    private BitSet BitSetfromString(String s){
        return BitSetfromLong(Long.valueOf(s,2));
    }
}
