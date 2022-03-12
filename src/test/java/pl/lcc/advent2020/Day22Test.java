/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package pl.lcc.advent2020;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author piko
 */
class Day22Test {

    Deque<Integer> player1 = new LinkedList<>(List.of(9, 2, 6, 3, 1));
    Deque<Integer> player2 = new LinkedList<>(List.of(5, 8, 4, 7, 10));

    @Test
    void testFirstPart() {
        long result = new Day22.Game(player1, player2).score();
        assertThat(result).isEqualTo(306);
    }

    @Test
    void testSecondPart() {
        long result = new Day22.Game(player1, player2).scoreP2();
        assertThat(result).isEqualTo(291);
    }

    @Test
    void testTiebreaker() {
        var game = new Day22.Game(new LinkedList<>(List.of(43, 19)), new LinkedList<>(List.of(2, 29, 14)));
        game.scoreP2();
        assertThat(game.player1).hasSizeBetween(1, 4);
    }

    @Test
    void testCutList() {
        var game = new Day22.Game(player1, player2);
        var result = game.makeShortList(player1, 3);
        assertThat(result).containsExactlyElementsOf(List.of(9, 2, 6));
    }

}
