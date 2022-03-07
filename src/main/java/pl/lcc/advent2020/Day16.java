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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day16 {

    Train train;

    Day16() {
        train = new Train();
    }

    public Day16(String path) throws FileNotFoundException {
        train = new Train();
        try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            String next = sc.next();
            while (!next.isBlank()) {
                train.addRange(Reader.parseCategories(next));
                next = sc.next();
            }
            sc.next();
            train.setMyTicket(Reader.parseTicket(sc.next()));
            sc.next();
            sc.next();
            while (sc.hasNext()) {
                train.addTicket(Reader.parseTicket(sc.next()));
            }
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

        int calculateP1() {
            return tickets.stream()
                    .flatMap(t -> t.numbers().stream())
                    .filter(this::isNumberValid)
                    .mapToInt(i -> i)
                    .sum();
        }

        private boolean isNumberValid(Integer n) {
            return ticketFields.stream().noneMatch(cat -> cat.inRange(n));
        }

        boolean isTicketValid(Ticket t) {
            return t.numbers.stream().noneMatch(this::isNumberValid);
        }

        List<Ticket> removeInvalidTickets() {
            return tickets.stream().filter(this::isTicketValid).toList();
        }

        @Override
        public String toString() {
            return "Train{" + "ticketFields=" + ticketFields + ", tickets=" + tickets + ", myTicket=" + myTicket + '}';
        }

    }

    record CategoryLine(String name, int min1, int max1, int min2, int max2) {

        boolean inRange(int n) {
            return ((n >= min1) && (n <= max1)) || ((n >= min2) && (n <= max2));
        }

        boolean inRange(List<Integer> list) {
            return list.stream().allMatch(this::inRange);
        }

    }

    class P2Solver {

        int size;

        Map<Integer, List<Integer>> numbersInPosition;

        Map<CategoryLine, List<Integer>> possibleSolutions;

        Ticket myTicket;
        
          public P2Solver(int size, Day16.Train train) {
           this.size = size;
           numbersInPosition = new HashMap<>();
           for (int i = 0; i < size; i++) {
               numbersInPosition.put(i, new ArrayList<>());
            }
           myTicket = train.myTicket;
            add(train.removeInvalidTickets());
            add(train.myTicket);
            findPossible(train.ticketFields);
            
        }

       private P2Solver add(List<Ticket> tickets) {
            tickets.forEach(this::addTicket);
            return this;
        }

        private P2Solver add(Ticket ticket) {
            addTicket(ticket);
            return this;
        }

        private void addTicket(Ticket t) {
            for (int i = 0; i < size; i++) {
                numbersInPosition.compute(i, (n, list) -> {
                    list.add(t.numbers.get(n));
                    return list;
                });
            }
        }

       private P2Solver findPossible(List<CategoryLine> cats) {
            possibleSolutions = new HashMap<>();
            cats.forEach(cat -> possibleSolutions.put(cat, new ArrayList<>()));

            cats.forEach(cat -> (numbersInPosition).entrySet().stream()
                    .forEach(entry -> {
                        if (cat.inRange(entry.getValue())) {
                            possibleSolutions.compute(cat, (ct, list) -> {
                                list.add(entry.getKey());
                                return list;
                            });
                        }
                    })
            );

            eliminateAlreadyFitted();
            return this;
        }

        void eliminateAlreadyFitted() {

            var workingList = possibleSolutions.entrySet().stream()
                    .filter(entry -> entry.getValue().size() == 1)
                    .map(Entry::getKey)
                    .collect(Collectors.toCollection(ArrayList::new));

            while (!workingList.isEmpty()) {

                var element = possibleSolutions.get(workingList.remove(0)).get(0);

                possibleSolutions.entrySet().stream()
                        .filter(entry -> entry.getValue().size() > 1)
                        .forEach(entry -> {
                            if (entry.getValue().remove(element) && entry.getValue().size() == 1) {                               
                                    workingList.add(entry.getKey());
                                }
                        });
            }

        }

        long getSolution() {
            var targetList = List.of("departure location", "departure station", "departure platform", "departure track", "departure date", "departure time");
            
            return possibleSolutions.entrySet().stream()
                    .filter(entry -> targetList.contains(entry.getKey().name))
                    .map(entry -> entry.getValue().get(0))
                    .map(myTicket.numbers::get)
                    .mapToLong(i -> i)
                    .reduce((a, b) -> a * b)
                    .getAsLong();
        }

    }

    class Reader {

        private Reader(){};
        
        static CategoryLine parseCategories(String input) {
            var name = input.substring(0, input.indexOf(':'));
            var numbers = Arrays.stream(input.replaceAll("[^0-9]+", " ").trim().split(" ")).mapToInt(Integer::parseInt).toArray();

            return new CategoryLine(name, numbers[0], numbers[1], numbers[2], numbers[3]);
        }

        static Ticket parseTicket(String input) {
            var numbers = Arrays.stream(input
                    .replace(",", " ")
                    .trim()
                    .split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList<Integer>::new));

            return new Ticket(numbers);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day16("day16.txt").calculate();
    }

    void calculate() {
        Utils.printResult("Day 16", part1(), part2());
    }

    int part1() {
        return train.calculateP1();
    }

    long part2() {
        return new P2Solver(20,train)
                .getSolution();

    }
}