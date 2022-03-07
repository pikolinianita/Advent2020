/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author piko
 */
class Day16Test {

    public Day16Test() {
    }

    Day16.Train getTestTrain() {
        return new Day16().new Train()
                .addRange(Day16.Reader.parseCategories("class: 1-3 or 5-7"))
                .addRange(Day16.Reader.parseCategories("row: 6-11 or 33-44"))
                .addRange(Day16.Reader.parseCategories("seat: 13-40 or 45-50"))
                .setMyTicket(Day16.Reader.parseTicket("7,1,14"))
                .addTicket(Day16.Reader.parseTicket("7,3,47"))
                .addTicket(Day16.Reader.parseTicket("40,4,50"))
                .addTicket(Day16.Reader.parseTicket("55,2,20"))
                .addTicket(Day16.Reader.parseTicket("38,6,12"));
    }

    Day16.Train getTestTrainP2() {
        return new Day16().new Train()
                .addRange(Day16.Reader.parseCategories("class: 0-1 or 4-19"))
                .addRange(Day16.Reader.parseCategories("row: 0-5 or 8-19"))
                .addRange(Day16.Reader.parseCategories("seat: 0-13 or 16-19"))
                .setMyTicket(Day16.Reader.parseTicket("11,12,13"))
                .addTicket(Day16.Reader.parseTicket("3,9,18"))
                .addTicket(Day16.Reader.parseTicket("15,1,5"))
                .addTicket(Day16.Reader.parseTicket("5,14,9"));
    }

    @Test
    void parseRangeTest() {
        String input = "departure location: 47-164 or 179-960";
        var suspectedResult = new Day16.CategoryLine("departure location", 47, 164, 179, 960);
        assertThat(Day16.Reader.parseCategories(input)).isEqualTo(suspectedResult);
    }

    @Test
    void parseTicketTest() {
        String input = "10,23,45";
        var suspectedResult = new Day16.Ticket(List.of(10, 23, 45));
        assertThat(Day16.Reader.parseTicket(input)).isEqualTo(suspectedResult);
    }

    @Test
    void testTrain() {
        var train = getTestTrain();
        assertThat(train.calculateP1()).isEqualTo(71);
    }
    
    @Test
    void testTrainP2() {        
        var sizeP1 = getTestTrain().removeInvalidTickets().size();
        var sizeP2 = getTestTrainP2().removeInvalidTickets().size();
        
        assertThat(sizeP1).isEqualTo(1);
        assertThat(sizeP2).isEqualTo(3);
    }
    
    @Test
    void testMappedTicket(){
        var train = getTestTrainP2();
        var result = new Day16(). new P2Solver(3, train); 
        
        assertThat(result.numbersInPosition)
                .containsEntry(0, List.of(3, 15, 5, 11))
                .containsEntry(1, List.of(9, 1, 14, 12))
                .containsEntry(2, List.of(18, 5, 9, 13))
                .hasSize(3);      
    }
    
    @Test
    void testFindPossible(){
        var train = getTestTrainP2();
        var result = new Day16(). new P2Solver(3, train) ;

        assertThat(result.possibleSolutions).hasSize(3).allSatisfy((i,list)->assertThat(list).hasSize(1));
    }
    
}
