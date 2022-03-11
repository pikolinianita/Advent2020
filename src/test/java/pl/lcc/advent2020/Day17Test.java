/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package pl.lcc.advent2020;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author Nauczyciel
 */
class Day17Test {

    List<String> test = """
                  .#.
                  ..#
                  ###""".lines().toList();

    List<String> inp = """
                 ###..#..
                 .#######
                 #####...
                 #..##.#.
                 ###..##.
                 ##...#..
                 ..#...#.
                 .#....##""".lines().toList();

    @Test
    void testparseTile() {
        var result = new Day17.Board(test, false).countActive();
        assertThat(result).isEqualTo(5);
    }

    @Test
    void testparseTile4D() {
        var result = new Day17.Board(test, true).countActive();
        assertThat(result).isEqualTo(5);
    }

    @Test
    void testNeighbours4D() {
        var result = Day17.neighbours4D.size();
        assertThat(result).isEqualTo(80);
    }

    @Test
    void testNeighbours3D() {
        var result = Day17.neighbours3D.size();
        assertThat(result).isEqualTo(26);
    }

    @Test
    void testTicker() {
        var result = new Day17.Ticker(new Day17.Board(test, false), 1);
        assertThat(result.getResult().countActive()).isEqualTo(11);
    }

    @Test
    void testTickerSix() {
        var result = new Day17.Ticker(new Day17.Board(test, false), 6);
        assertThat(result.getResult().countActive()).isEqualTo(112);
    }

    @Test
    void testTickerSixP2() {
        var result = new Day17.Ticker(new Day17.Board(test, true), 6);
        assertThat(result.getResult().countActive()).isEqualTo(848);
    }

}
