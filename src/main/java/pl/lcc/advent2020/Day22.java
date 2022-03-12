package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author piko
 */
public class Day22 {

    Deque<Integer> player1;
    Deque<Integer> player2;

    private Day22(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            player1 = makePlayer(sc);
            player2 = makePlayer(sc);
        }
    }

    void calculate() {
        Utils.printResult("Day22", part1(), part2());
    }

    long part1() {
        return new Game(player1, player2).score();
    }

    long part2() {
        return new Game(player1,player2).scoreP2();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day22("day22.txt").calculate();
    }

    private Deque<Integer> makePlayer(Scanner sc) {
        Deque<Integer> result = new LinkedList<>();
        sc.next();
        var nextToken = sc.next();
        while (!"".equals(nextToken)) {
            result.addLast(Integer.parseInt(nextToken));
            if (sc.hasNext()) {
                nextToken = sc.next();
            } else {
                nextToken = "";
            }
        }
        return result;
    }

    record GameState(Deque<Integer> player1, Deque<Integer> player2) {
    }

    static class Game {

        Deque<Integer> player1;
        Deque<Integer> player2;
        Set<GameState> previousStates;

        public Game(Deque<Integer> player1, Deque<Integer> player2) {
            this.player1 = new LinkedList(player1);
            this.player2 = new LinkedList(player2);
            previousStates = new HashSet<>();
        }

        long score() {
            doTurns();
            var winner = player1.size() > 0 ? player1 : player2;
            return calculateScore(winner);
        }

        private void doTurns() {
            while (bothPlayersHasCards()) {
                Integer card1 = player1.pollFirst();
                Integer card2 = player2.pollFirst();
                if (card1 > card2) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card2);
                    player2.addLast(card1);
                }
            }
        }

        private boolean bothPlayersHasCards() {
            return !(player1.isEmpty() || player2.isEmpty());
        }

        private long calculateScore(Deque<Integer> winner) {
            var count = 1;
            long result = 0;
            while (!winner.isEmpty()) {
                result += count * winner.pollLast();
                count++;
            }
            return result;
        }

        long scoreP2() {
            doTurnsP2();
            var winner = player1.size() > 0 ? player1 : player2;
            return calculateScore(winner);
        }

        private Game doTurnsP2() {
            boolean isFirstWinner;

            while (bothPlayersHasCards()) {

                var state = new GameState(player1, player2);
                if (previousStates.contains(state)) {
                    player2.clear();
                    break;
                } else {
                    previousStates.add(state);
                }

                Integer card1 = player1.pollFirst();
                Integer card2 = player2.pollFirst();

                if (cardsAreLowerThanSizes(card1, card2)) {
                    isFirstWinner = new Game(makeShortList(player1, card1), makeShortList(player2, card2))
                            .doTurnsP2()
                            .isFirstWinner();
                } else if (card1 > card2) {
                    isFirstWinner = true;
                } else {
                    isFirstWinner = false;
                }

                if (isFirstWinner) {
                    player1.addLast(card1);
                    player1.addLast(card2);
                } else {
                    player2.addLast(card2);
                    player2.addLast(card1);
                }
            }
            return this;
        }

        private boolean cardsAreLowerThanSizes(Integer card1, Integer card2) {
            return (card1 <= player1.size()) && (card2 <= player2.size());
        }

        private boolean isFirstWinner() {
            return player2.isEmpty();
        }

        Deque<Integer> makeShortList(Deque<Integer> player, Integer n) {
            var result = new LinkedList<Integer>();
            var iter = player.iterator();
            while (n > 0) {
                result.add(iter.next());
                n--;
            }
            return result;
        }

    }

}
