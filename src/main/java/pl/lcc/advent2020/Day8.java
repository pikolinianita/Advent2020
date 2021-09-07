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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

record Command(String command, int value) {

    public Command(String line) {
        this(line.substring(0, 3), Integer.parseInt(line.substring(4)));
    }

};

record State (int position, int value){};

public class Day8 {

    ArrayList<Command> commands;

    private String[] lines;

    Day8() {

    }

    Day8(String path) throws FileNotFoundException {
        this();
        try ( java.util.Scanner sc = new Scanner(new File(path))) {
            lines = sc.useDelimiter("\n").tokens().toArray(String[]::new);
        };
    }

    void calculate() {
       Utils.printResult("Day 8", part1(lines),part2(lines));
    }

    int part1(String[] input) {
        commands = Arrays.stream(input)
                .map(Command::new)
                .collect(Collectors.toCollection(ArrayList<Command>::new));
        return new Emulator(commands).emulate();
    }

    int part2(String[] input) {
         commands = Arrays.stream(input)
                .map(Command::new)
                .collect(Collectors.toCollection(ArrayList<Command>::new));
        var firstRun = new Emulator(commands);
        firstRun.emulate();
        var suspectedCommands = firstRun.visited.stream()
                .filter(this::isSuspected)
                .toList();
        return new Fixer(suspectedCommands, commands, firstRun).tryFindSolution();
      // return -1;
    }

    private boolean isSuspected(Integer i) {
        return "jmp".equals(commands.get(i).command())||"nop".equals(commands.get(i).command());
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day8("day8.txt").calculate();
    }

    
    class Fixer{
    LinkedList<Integer> suspectedCommands;
    
    ArrayList<Command> commands;
    
    private final Set<Integer> visitedOrg;

    private final Set<State> restartValues;
    
        public Fixer(List<Integer> suspectedCommands, ArrayList<Command> commands, Emulator firstRun) {
            this.suspectedCommands = new LinkedList<>(suspectedCommands);
            this.commands = commands;
            this.visitedOrg = firstRun.visited;
            this.restartValues = firstRun.states;
        }

        private int tryFindSolution( ) {
            for(int suspectPos : suspectedCommands ){
               
                var newCommands = new ArrayList<>(commands);
                newCommands.set(suspectPos, swapSuspects(newCommands.get(suspectPos)));
                var result = new Emulator(newCommands);
                result.emulate();
                if (result.success){ 
                   
                    return result.finalValue;}
            }
            return -1;
        }

        private Command swapSuspects(Command suspect) {
            return "nop".equals(suspect.command())
                    ? new Command("jmp",suspect.value()) 
                    : new Command ("nop", suspect.value());
        
    }
    }

    class Emulator {

        ArrayList<Command> emCommands;

        Set<Integer> visited;
        
        Set<State> states;
        
        private final int endPosition;
        
        private boolean success;
        private int finalValue;

        public Emulator(ArrayList<Command> commands) {
            this.emCommands = commands;
            visited = new HashSet<>();
            endPosition = commands.size();
            success = false;
            states = new HashSet<>();
        }

        public int emulate() {
            finalValue = emulate(0, 0);
            return finalValue;
        }

        public int emulate(int position, int accumulator) {
            if (isEnd(position)) {
                success = true;
                return accumulator;
            }
            if (!visited.contains(position)) {
                visited.add(position);
                states.add(new State(position, accumulator));
                var comm = emCommands.get(position);
                // System.out.println(comm.toString());
                //  System.out.println("Pos: " + position + " Acc: " + accumulator);
                return switch (comm.command()) {
                    case "acc" -> {
                        yield emulate(position + 1, accumulator + comm.value());
                    }
                    case "jmp" -> {
                        yield emulate(position + comm.value(), accumulator);
                    }
                    case "nop" -> {
                        yield emulate(position + 1, accumulator);
                    }
                    default -> {
                        throw new IllegalArgumentException("wrong" + comm.command() + comm.value());
                    }
                };
            }
            return accumulator;
        }

        private boolean isEnd(int position) {
            return position==endPosition;
        }
    }
}
