/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    static List<Tile3D> neighbours3D = createNeighbours3D();

    static List<Tile3D> createNeighbours3D() {
        List<Tile3D> result = new ArrayList<>(27);
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    result.add(new Tile3D(x, y, z));
                }
            }
        }
        result.remove(new Tile3D(0, 0, 0));
        return result;
    }   
    
    static List<Tile4D> neighbours4D = CreateNeighbours4D();

    static List<Tile4D> CreateNeighbours4D() {
        List<Tile4D> result = new ArrayList<>(27);
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    for (int w = -1; w < 2; w++) {
                        result.add(new Tile4D(x, y, z, w));
                    }
                }
            }
        }
        result.remove(new Tile4D(0, 0, 0, 0));
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day17("day17.txt").calculate();
    }

    private Day17(String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            boardLines = sc.tokens().toList();
        }
    }

    void calculate() {
        Utils.printResult("Template", part1(), part2());
    }

    int part1() {
       return new Ticker(new Board(boardLines, false),6).getResult().countActive();
         
    }

    int part2() {
        return new Ticker(new Board(boardLines, true),6).getResult().countActive();
    }

    interface Tile<T> {

        T add(T t);
    }

    record Tile3D(int x, int y, int z) implements Tile<Tile3D> {

        @Override
        public Tile3D add(Tile3D t) {
            return new Tile3D(x + t.x(), y + t.y(), z + t.z());
        }
    }

    record Tile4D(int x, int y, int z, int w) implements Tile<Tile4D> {

        @Override
        public Tile4D add(Tile4D t) {
            return new Tile4D(x + t.x(), y + t.y(), z + t.z(), w + t.w());
        }
    }

    static class Ticker {

        Set<Tile> activeTiles;
        int numberOfTurns;
        Map<Tile, Integer> inactiveForChange;
        final boolean is4D;
        final List<? extends Tile> neighbours;

        Ticker(Board board, int n) {
            activeTiles = board.activeTiles;
            numberOfTurns = n;
            this.is4D = board.is4D();
            if (is4D) {
                neighbours = neighbours4D;
            } else {
                neighbours = neighbours3D;
            }
        }

        Board getResult() {

            for (int i = 0; i < numberOfTurns; i++) {
                activeTiles = doTurn();
            }

            return new Board(activeTiles, is4D);
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
                    .map((t) -> (Tile) tile.add(t))
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

        final boolean is4D;
        Set<Tile> activeTiles;

        Board(List<String> input, boolean is4D) {
            activeTiles = makeTiles(input, is4D);
            this.is4D = is4D;
        }

        private Board(Set<Tile> activeTiles, boolean is4D) {
            this.activeTiles = activeTiles;
            this.is4D = is4D;
        }

        private Set<Tile> makeTiles(List <String> rowList, boolean is4D) {
            var result = new HashSet<Tile>();
                for (int i = 0; i < rowList.size(); i++) {
                    var tokens = rowList.get(i).split("");
                    for (int j = 0; j < tokens.length; j++) {
                        if ("#".equals(tokens[j])) {
                            createTile(result, j, i, is4D);
                        }
                    }
                }
            

            return result;
        }

        private void createTile(HashSet<Tile> result, int j, int i, boolean is4D) {
            if (is4D) {
                result.add(new Tile4D(j, i, 0, 0));
            } else {
                result.add(new Tile3D(j, i, 0));
            }
        }

        int countActive() {
            return activeTiles.size();
        }

        private boolean is4D() {
            return is4D;
        }
    }

}
