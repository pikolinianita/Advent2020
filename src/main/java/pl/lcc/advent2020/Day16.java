/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.ArrayList;
import java.util.List;

public class Day16 {

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

        Train() {
            ticketFields = new ArrayList<>();
            tickets = new ArrayList<>();
        }

        Train setMyTicket(Ticket myTicket) {
            this.myTicket = myTicket;
            return this;
        }
        List<Range> ticketFields;
        List<Ticket> tickets;
        Ticket myTicket;

        Train addRange(Range r) {
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
    }

    class Reader {
        
    }
    
    void calculate() {
        Utils.printResult("Template", part1(null), part2(null));
    }

    int part1(int[] input) {
        return -1;
    }

    int part2(int[] input) {
        return -1;
    }
}
