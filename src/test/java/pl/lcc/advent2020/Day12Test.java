/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.List;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author piko
 */
 class Day12Test {
    
    List<String> input = List.<String>of("F10","N3","F7","R90","F11");
    
    @Test
     void testShipSailing() {
        var ship = new Day12.Ship();
        Day12.Ship result = input.stream()
                .map(Day12.ShipCommand::new)
                .reduce(ship, (Day12.Ship s,Day12.ShipCommand c) -> s.move(c), Day12.Ship::dummyMethod);
        assertThat(result.getManchattan()).as("manch").isEqualTo(25);                
    }
    
     @Test
     void testShipWPSailing() {
        var ship = new Day12.ShipWithWaypoint();
        Day12.Ship result = input.stream()
                .map(Day12.ShipCommand::new)
                .reduce(ship, (Day12.Ship s,Day12.ShipCommand c) -> s.move(c), Day12.Ship::dummyMethod);
        assertThat(result.getManchattan()).as("manch").isEqualTo(286);
    
}}
