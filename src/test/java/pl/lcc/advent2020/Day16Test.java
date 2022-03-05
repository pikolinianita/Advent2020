/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;


import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author piko
 */
public class Day16Test {
    
    String sampleData = """
                        class: 1-3 or 5-7
                        row: 6-11 or 33-44
                        seat: 13-40 or 45-50
                        
                        your ticket:
                        7,1,14
                        
                        nearby tickets:
                        7,3,47
                        40,4,50
                        55,2,20
                        38,6,12""";
    
    public Day16Test() {       
    }
    
    Day16.Train getTestTrain(){
        return  new Day16() .new Train()
                .addRange(Day16.Reader.parseCategories("class: 1-3 or 5-7"))
                .addRange(Day16.Reader.parseCategories("row: 6-11 or 33-44"))
                .addRange(Day16.Reader.parseCategories("seat: 13-40 or 45-50"))
                .setMyTicket(Day16.Reader.parseTicket("7,1,14"))
                .addTicket(Day16.Reader.parseTicket("7,3,47"))
                .addTicket(Day16.Reader.parseTicket("40,4,50"))
                .addTicket(Day16.Reader.parseTicket("55,2,20"))
                .addTicket(Day16.Reader.parseTicket("38,6,12"));
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
    
    @Test
    void parseRangeTest() {
        String input = "departure location: 47-164 or 179-960";
        var suspectedResult = new Day16.CategoryLine("departure location",47,164,179,960);
        assertThat(Day16.Reader.parseCategories(input)).isEqualTo(suspectedResult);
    }
            
     @Test
    void parseTicketTest() {
    String input = "10,23,45";
    var suspectedResult = new Day16.Ticket(List.of(10,23,45));
    assertThat(Day16.Reader.parseTicket(input)).isEqualTo(suspectedResult);    
    }
    
    @Test
    void testTrain(){
       var train = getTestTrain();
       assertThat(train.calculateP1()).isEqualTo(71);
    }
}
