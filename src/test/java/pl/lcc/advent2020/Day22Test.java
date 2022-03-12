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
public class Day22Test{
    
    Deque<Integer> player1 = new LinkedList<>(List.of(9, 2, 6, 3, 1));
    Deque<Integer> player2 = new LinkedList<>(List.of(5, 8, 4, 7, 10));
    
    @Test
    public void testFirstPart() {
        
       long result = new Day22.Game(player1, player2).score();
       assertThat(result).isEqualTo(306);
    }
            
}

