/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package pl.lcc.advent2020;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author Nauczyciel
 */
public class Day17Test {
    
    String test = """
                  .#.
                  ..#
                  ###""";
    
    String inp = """
                 ###..#..
                 .#######
                 #####...
                 #..##.#.
                 ###..##.
                 ##...#..
                 ..#...#.
                 .#....##""";
    
    
    @Test
    public void testCalculate() {
    }

    @Test
    public void testPart1() {
    }
    
    @Test
    public void testPart2() {
    }
    
    @Test
    void testparseTile(){
       var result = new Day17.Board(test).countActive();
     
        assertThat(result).isEqualTo(5);
    }
    
@Test
void testTicker(){
    var result = new Day17.Ticker(new Day17.Board(test), 1);
    assertThat(result.getResult().countActive()).isEqualTo(11);
}
    
@Test
void testTickerSix(){
    var result = new Day17.Ticker(new Day17.Board(test), 6);
    assertThat(result.getResult().countActive()).isEqualTo(112);
}

@Test
void runTickerSixProper(){
     var result = new Day17.Ticker(new Day17.Board(inp), 6);
    assertThat(result.getResult().countActive()).isEqualTo(112);
}

}
