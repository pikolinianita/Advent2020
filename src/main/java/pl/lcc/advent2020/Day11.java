/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day11 {

    record Pair (int x, int y){}
    
    static List<Pair> pairs = List.of(new Pair (1,1), new Pair (0,1),new Pair (-1,1),new Pair (1,0), new Pair (-1,0), new Pair (-1,-1), new Pair (0,-1),new Pair (1,-1));
    
    interface PaxLogic {

        char findNewValue(int x, int y);

        void setBoard(Day11 board);
        
        int getNCount (int x, int y);

    }

    static class P1Logic implements PaxLogic {

        Day11 board;

        @Override
        public void setBoard(Day11 board) {
            this.board = board;
        }

        @Override
        public int getNCount(int x, int y) {
           return (int) pairs.stream()
                    .filter(p -> (board.get(x + p.x, y + p.y) == '#'))
                    .count();
        }

        @Override
        public char findNewValue(int x, int y) {
            char thisValue = board.get(x, y);
            if (thisValue == '.') {
                return '.';
            }
            int neighboursCount = getNCount(x, y);           
            if (neighboursCount == 0) {
                if (thisValue == 'L') {
                    board.changed = true;
                }
                return '#';
            } else if (neighboursCount >= 4) {
                if (thisValue == '#') {
                    board.changed = true;
                }
                return 'L';
            } else {
                return thisValue;
            }
        }
    }

    static class P2Logic implements PaxLogic {

        Day11 board;

        @Override
        public char findNewValue(int x, int y) {
         char thisValue = board.get(x, y);
            if (thisValue == '.') {
                return '.';
            }
            int neighboursCount = getNCount(x, y);
            if (neighboursCount == 0) {
                if (thisValue == 'L') {
                    board.changed = true;
                }
                return '#';
            } else if (neighboursCount >= 5) {
                if (thisValue == '#') {
                    board.changed = true;
                }
                return 'L';
            } else {
                return thisValue;
            }
        }
        
        @Override
        public int getNCount(int x, int y){
          return (int) pairs.stream()
                  .filter(p -> hasVisiblePax(x, y, p))
                  .count();
                  }
        @Override
        public void setBoard(Day11 board) {
            this.board = board;
        }

        @SuppressWarnings("InfiniteRecursion")
        private boolean hasVisiblePax(int x, int y, Pair p) {
           return switch (board.get(x + p.x, y + p.y)){
               case '#' -> true;
               case 'L', '0' -> false;
               default -> hasVisiblePax(x + p.x, y + p.y, p);
        };
        }
    }

    int[] input;
    final int[] inputImmutable;
    int length;
    int height;
    boolean changed;
    PaxLogic ai;

    Day11(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            var parsedString = sc
                    .tokens()
                    .toArray(String[]::new);
            length = parsedString[0].length();
            height = parsedString.length;
            inputImmutable = String.join("", parsedString).chars().toArray();
            changed = false;
        }
    }

    Day11 setLogic(PaxLogic board) {
        ai = board;
        board.setBoard(this);
        return this;
    }

    Day11(int[] testArray) {
        this(testArray, 10, 10);
    }

    Day11(int[] testArray, int h, int v) {
        input = testArray;
        inputImmutable = input;
        length = h;
        height = v;
        changed = false;
    }

    void calculate() {
        Utils.printResult("Day11", part1() , part2()); 
    }

    long part1() {
        input = inputImmutable;
        setLogic(new P1Logic());
        changed = true;
        while (changed) {
            changed = false;
            sweepBoard();
        }
        return countChar('#');
    }

    void sweepBoard() {
        var output = new int[input.length];
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                set(output, ai.findNewValue(x, y), x, y);
            }
        }
        input = output;
    }

    long part2() {
        input = inputImmutable;
        setLogic(new P2Logic());
         changed = true;
        while (changed) {
            changed = false;
            sweepBoard();
        }
        return countChar('#');
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day11("day11.txt").calculate();
    }

    long countChar(char c) {
        return Arrays.stream(input)
                .filter(ch -> ch == c)
                .count();
    }

    char get(int y, int x) {
        if (isInRange(x, y)) {
            var position = y * length + x;
            return (char) input[position];
        } else {
            return '0';
        }
    }

    void set(int[] destination, char value, int y, int x) {
        if (isInRange(x, y)) {
            var position = y * length + x;
            destination[position] = value;
        }
    }

    private boolean isInRange(int x, int y) {
        return (x >= 0 && x < length && y >= 0 && y < height);
    }

    String toBoardString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < height; i++) {
            result.append(new String(input, i * length, length)).append("\n");
        }
        return result.toString();
    }
}
