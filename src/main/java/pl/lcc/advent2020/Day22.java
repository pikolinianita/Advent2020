
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author piko
 */
public class Day22 {

    Deque<Integer> player1;
    Deque<Integer> player2;
    
    private Day22(String path) throws FileNotFoundException {
         try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
           player1 = makePlayer(sc);
           player2 = makePlayer(sc);
    }}

    void calculate(){
        Utils.printResult("Day22",part1(),part2());
    }
    
    long part1() {
      return new Game(player1, player2).score();
    }

    long part2() {
        return -1;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        new Day22("day22.txt").calculate();
    }

    private Deque<Integer> makePlayer(Scanner sc) {
        Deque<Integer> result = new LinkedList<>();
        sc.next();
        var nextToken = sc.next();
        while (!"".equals(nextToken)){
            result.addLast(Integer.parseInt(nextToken));
            if (sc.hasNext()) {
                nextToken = sc.next();
            } else {
                nextToken = "";
            }
        }
        System.out.println(result);
        return result;
    }

    static class Game {
        
        Deque<Integer> player1;
        Deque<Integer> player2;
        
        public Game(Deque<Integer> player1, Deque<Integer> player2) {
            this.player1 = player1;
            this.player2 = player2;            
        }

        long score() {
            
            doTurns();
            
           var winner = player1.size()>0 ? player1 : player2;
            
            return calculateScore(winner);
        }

        private void doTurns() {
            while(bothPlayersHasCards()){
                Integer card1 = player1.pollFirst();
                Integer card2 = player2.pollFirst();
                if (card1 > card2)
                {player1.addLast(card1); player1.addLast(card2);}
                else
                {player2.addLast(card2); player2.addLast(card1);}
            }
        }

        private boolean bothPlayersHasCards() {
            return !(player1.isEmpty() || player2.isEmpty());
        }

        private long calculateScore(Deque<Integer> winner) {
           var count = 1;
           long result = 0;
           while (!winner.isEmpty()){
               result += count * winner.pollLast();
               count++;
           }
           return result;
        }
        
        
    }
    
}
