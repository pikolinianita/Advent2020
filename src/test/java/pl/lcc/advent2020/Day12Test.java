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
public class Day12Test {
    
    List<String> input = List.<String>of("F10","N3","F7","R90","F11");
    
    public Day12Test() {
       
    }
    
    @Test
    public void testShipSailing() {
        var ship = new Ship();
        Ship result = input.stream()
                .map(ShipCommand::new)
                .reduce(ship, (Ship s,ShipCommand c) -> s.move(c), Ship::dummyMethod);
        assertThat(result.getManchattan()).as("manch").isEqualTo(25);
                
    }
    
     @Test
    public void testShipWPSailing() {
        var ship = new ShipWithWaypoint();
        Ship result = input.stream()
                .map(ShipCommand::new)
                .reduce(ship, (Ship s,ShipCommand c) -> s.move(c), Ship::dummyMethod);
        assertThat(result.getManchattan()).as("manch").isEqualTo(286);
    
}}
