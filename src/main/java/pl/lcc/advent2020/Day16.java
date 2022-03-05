/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day16 {

    Train train;
    
    Day16 (){
    train = new Train();
    }
    
    public Day16(String path) throws FileNotFoundException{
        train = new Train();
         try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            String next = sc.next();
            while(!next.isBlank()){
                train.addRange(Reader.parseCategories(next));
                next = sc.next();
            }
            sc.next();
            train.setMyTicket(Reader.parseTicket(sc.next()));
            sc.next();
            sc.next();
            while(sc.hasNext()){
                train.addTicket(Reader.parseTicket(sc.next()));
            }
         }
    }
    
    
    class MultiRange {

        List<Range> ranges;

        MultiRange() {
            ranges = new ArrayList<>();
        }

        MultiRange addRange(Range r) {
            for (int i = 0; i < ranges.size(); i++) {
                if (r.canMerge(ranges.get(i))) {
                    ranges.set(i, ranges.get(i).merge(r));
                    return this;
                }
            }

            ranges.add(r);
            return this;
        }

        int size() {
            return ranges.stream().mapToInt(Range::size).sum();
        }

    }

    static record Range(String name, int min, int max) {

        boolean isValid(int value) {
            return value >= min && value <= max;
        }

        //assumes records should merge     
        Range merge(Range other) {
            return new Range(name,
                    Math.min(this.min(), other.min()),
                    Math.max(this.max(), other.max()));
        }

        boolean canMerge(Range other) {
            return !((this.min() > other.max()) || (this.max() < other.min()));
        }

        int size() {
            return max - min + 1;
        }
    }

    record Ticket(List<Integer> numbers) {
    }

    class Train {

        
        List<CategoryLine> ticketFields;
        List<Ticket> tickets;
        Ticket myTicket;
        
        
        Train() {
            ticketFields = new ArrayList<>();
            tickets = new ArrayList<>();
        }

        Train setMyTicket(Ticket myTicket) {
            this.myTicket = myTicket;
            return this;
        }
        
        Train addRange(CategoryLine r) {
            ticketFields.add(r);
            return this;
        }

        Train addTicket(Ticket t) {
            tickets.add(t);
            return this;
        }

        List<Integer> getWrongValues() {
            return null;
        }
        
        int calculateP1(){
            return tickets.stream()
                    .flatMap(t->t.numbers().stream())
                    .filter(n -> 0 == ticketFields.stream().filter(cat -> cat.inRange(n)).count())
                    .mapToInt(i->i)
                    .sum();
        }
        
        @Override
        public String toString() {
            return "Train{" + "ticketFields=" + ticketFields + ", tickets=" + tickets + ", myTicket=" + myTicket + '}';
        }
        
        
    }
    
    record CategoryLine(String name, int min1, int max1, int min2, int max2){
    
    boolean inRange(int n){
        return ((n >= min1) && (n <= max1)) || ((n >= min2) && (n <= max2));
    }
    }

    class Reader {
        static CategoryLine parseCategories(String input){
            var name = input.substring(0, input.indexOf(':'));
            var numbers = Arrays.stream(input.replaceAll("[^0-9]+", " ").trim().split(" ")).mapToInt(Integer::parseInt).toArray();
                          
            return new CategoryLine(name,numbers[0],numbers[1],numbers[2],numbers[3]);
        }
        
        static Ticket parseTicket(String input){
            var numbers = Arrays.stream(input
                    .replaceAll(",", " ")
                    .trim()
                    .split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList<Integer>::new));
                    
            return new Ticket(numbers);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
         new Day16 ("day16.txt").calculate();
    }
    
    void calculate() {
        Utils.printResult("Day 16", part1(), part2(null));
    }

    int part1() {
       return train.calculateP1();
    }

    int part2(int[] input) {
        return -1;
    }
}
