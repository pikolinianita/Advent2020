/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Nauczyciel
 */
public class Day24 {

    List<String> directions;

    Day24() {
    }

    ;
    
    Day24(String path) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            directions = sc.tokens().toList();
        }
    }

    void calculate() {
        Utils.printResult("Template", part1(), part2());
    }

    int part1() {
        return new Board(directions).getAnswer();
    }

    int part2() {
        return new Animator(new Board(directions), 100).result().getAnswer();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day24("day24.txt").calculate();
    }

    class Animator {

        Set<Tile> sourceTiles;
        int turnsRequired;
        Set<Tile> resultTiles;
        Map<Tile, Integer> whitesForChange;

        static List<Tile> neighbours = List.of(
                new Tile(2, 0),
                new Tile(-2, 0),
                new Tile(1, 1),
                new Tile(-1, 1),
                new Tile(1, -1),
                new Tile(-1, -1)
        );

        public Animator(Board source, int n) {
            sourceTiles = new HashSet(source.coloured);
            turnsRequired = n;
        }

        Board result() {
            resultTiles = new HashSet<>(sourceTiles);
            for (int i = 0; i < turnsRequired; i++) {
                resultTiles = doTurn();
            }

            return new Board(resultTiles);
        }

        private Set<Tile> doTurn() {
            whitesForChange = new HashMap<>();
            List<Tile> blackToTurn = makeBlackToTurnListAndConstructWhitesMap();
            List<Tile> whiteToTurn = filterWhiteMap();

            var result = new HashSet<>(resultTiles);
            result.removeAll(blackToTurn);
            result.addAll(whiteToTurn);
            return result;
        }

        private List<Tile> makeBlackToTurnListAndConstructWhitesMap() {
            return resultTiles.stream()
                    .filter(this::checkNumbersAnsPopulateWhiteMap)
                    .toList();
        }

        private List<Tile> filterWhiteMap() {
            return whitesForChange.entrySet().stream()
                    .filter(entry -> entry.getValue() == 2)
                    .map(Entry::getKey)
                    .toList();
        }

        private boolean checkNumbersAnsPopulateWhiteMap(Tile tile) {
            var count = neighbours.stream()
                    .map(tile::add)
                    .filter((t)
                            -> {
                        if (resultTiles.contains(t)) {
                            return true;
                        } else {
                            addToWhitesMap(t);
                            return false;
                        }
                    })
                    .count();
            return !(count == 1 || count == 2);
        }

        private void addToWhitesMap(Tile t) {
            whitesForChange.merge(t, 1, (old, sth) -> ++old);
        }
    }

    record Tile(int x, int y) {

        Tile add(Tile t) {
            return new Tile(x + t.x, y + t.y);
        }

    }

    class Board {

        Set<Tile> coloured;

        Board() {
            coloured = new HashSet<>();
        }

        Board(Set<Tile> data) {
            coloured = data;
        }

        Board(List<String> list) {
            this();
            coloured = list.stream()
                    .map(this::parseString)
                    .collect(HashSet::new,
                            this::addToSet,
                            (a, b) -> {
                                throw new RuntimeException("Combiner should not be invoked");
                            }
                    );
        }

        void addToSet(Set<Tile> acc, Tile tile) {
            if (acc.contains(tile)) {
                acc.remove(tile);
            } else {
                acc.add(tile);
            }

        }

        int getAnswer() {
            return coloured.size();
        }

        Tile parseString(String s) {
            int x = 0;
            int y = 0;
            try (var sc = new Scanner(s)) {
                sc.useDelimiter("");
                while (sc.hasNext()) {
                    switch (sc.next()) {
                        case "e" ->
                            x += 2;
                        case "w" ->
                            x -= 2;
                        case "n" -> {
                            if ("e".equals(sc.next())) {
                                x++;
                                y++;
                            } else {
                                x--;
                                y++;
                            }
                        }
                        case "s" -> {
                            if ("e".equals(sc.next())) {
                                x++;
                                y--;
                            } else {
                                x--;
                                y--;
                            }
                        }
                    }
                }
            }
            return new Tile(x, y);
        }

        Board animate(int n) {
            return new Animator(this, n).result();
        }

    }

}
