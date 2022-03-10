/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Nauczyciel
 */
public class Day17 {
    
    List<String> boardLines;
    
    static List<Tile> neighbours = neighbours();
    
    static List<Tile> neighbours(){
        List<Tile> result = new ArrayList<>(27);
        for(int x =-1; x<2; x++)
            for(int y =-1; y<2; y++)
                for(int z =-1; z<2; z++)
                result.add(new Tile (x,y,z));
        result.remove(new Tile(0,0,0));
        return result;
    };
    
    public static void main(String[] args) throws FileNotFoundException {
        new Day17("day17.txt").calculate();
    }

    private Day17(String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            boardLines = sc.tokens().toList();
        }
    }
    
    void calculate(){
        Utils.printResult("Template",part1(),part2());
    }
    
    int part1() {
      return -1;
    }

    int part2() {
        return -1;
    }
    
    record Tile(int x, int y, int z){
        Tile add (Tile t){
            return new Tile (x + t.x(), y + t.y(), z+t.z());
        }
    }
    
    static class Ticker{
    
        Set<Tile> activeTiles;
        int numberOfTurns;
        Map<Tile, Integer> inactiveForChange;
                
    Ticker(Board board, int n){
        activeTiles = board.activeTiles;
        numberOfTurns = n;
    }
    
    Board getResult(){
        
         for (int i = 0; i < numberOfTurns; i++) {
                activeTiles = doTurn();
            }
        
        return new Board(activeTiles);
    }

        private Set<Tile> doTurn() {
            inactiveForChange = new HashMap<>();
            List<Tile> activeToTurnoff = makeBlackToTurnListAndConstructWhitesMap();
            List<Tile> inactiveToTournOn = filterWhiteMap();

            var result = new HashSet<>(activeTiles);
            result.removeAll(activeToTurnoff);
            result.addAll(inactiveToTournOn);
            return result;
        }

        private List<Tile> makeBlackToTurnListAndConstructWhitesMap() {
            return activeTiles.stream()
                    .filter(this::checkNumbersAnsPopulateInactiveMap)
                    .toList();
        }

        private List<Tile> filterWhiteMap() {
            return inactiveForChange.entrySet().stream()
                    .filter(entry -> entry.getValue() == 3)
                    .map(Map.Entry::getKey)
                    .toList();
        }

        private boolean checkNumbersAnsPopulateInactiveMap(Tile tile) {
            var count = neighbours.stream()
                    .map(tile::add)
                    .filter((t)
                            -> {
                        if (activeTiles.contains(t)) {
                            return true;
                        } else {
                            addToInactiveMap(t);
                            return false;
                        }
                    })
                    .count();
            return !(count == 2 || count == 3);
        }

        private void addToInactiveMap(Tile t) {
            inactiveForChange.merge(t, 1, (old, sth) -> ++old);
        }
    
    
    
    }
    
    
    static class Board {
        
        Set<Tile> activeTiles;
        
        Board(String input){
            activeTiles = makeTiles(input);
        }

        private Board(Set<Tile> activeTiles) {
            this.activeTiles = activeTiles;
        }

        private Set<Tile> makeTiles(String input) {
            var result = new HashSet<Tile>();
           try(var sc = new Scanner(input)){
               sc.useDelimiter("\n");
               var rowList = sc.tokens().toList();
               for (int i = 0; i<rowList.size(); i++ ){
                   var tokens = rowList.get(i).split("");
                   for (int j=0; j<tokens.length; j++){
                       if ("#".equals(tokens[j])) result.add(new Tile(j,i,0));
                   }
               }
           }
           
           return result;
        }
        
        int countActive(){
            return activeTiles.size();
        }
    }
    
    
}
