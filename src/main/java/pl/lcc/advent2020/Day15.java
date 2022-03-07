/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.util.HashMap;

//TODO hardcoded Input, could be faster

public class Day15 {

    class Game {

       HashMap<Integer, NumberData> game;

       int turn;

       int last;

        Game() {
            game = new HashMap<>();
        }

        Game setInput(int[] input) {
            for (int i = 0; i < input.length; i++) {
                game.put(input[i], new NumberData((i + 1), 1));
            }
            turn = input.length;
            last = input[input.length - 1];
            return this;
        }

        int findNext() {

            var lastValue = game.get(last);
            if (lastValue == null) {
                game.put(last, new NumberData(turn, 1));
                last = 0;

            } else {
                game.put(last, lastValue.update(turn));
                last = turn - lastValue.lastSeenTime;
            }
            turn++;
            return last;
        }

        @Override
        public String toString() {
            return "Game{" + "game=" + game + ", turn=" + turn + '}';
        }

        private int getNumberNo(int number) {
            while (turn<number){
            findNext();
            }            
            return last;
        }
    }

    record NumberData(int lastSeenTime, int seenCount) {

        NumberData update(int newSeen) {
            return new NumberData(newSeen, this.seenCount + 1);
        }
    }

    void calculate() {
        Utils.printResult("Template", part1(new int[]{6,19,0,5,7,13,1}), part2(new int[]{6,19,0,5,7,13,1}));
    }

    int part1(int[] input) {
       return new Game().setInput(input).getNumberNo(2020);
    }

    int part2(int[] input) {
       return new Game().setInput(input).getNumberNo(30000000);
    }

    public static void main(String[] args) {
        new Day15().calculate();
    }
    
}
