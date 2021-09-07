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
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;

class ColoredBag {

    String kindOfBag;

    List<String> mayContains;

    public ColoredBag(List<String> parsedLine) {
        mayContains = new ArrayList<>();
        kindOfBag = parsedLine.get(0);
        for (int i = 1; i < parsedLine.size(); i++) {
            mayContains.add(parsedLine.get(i));
        }
    }

    @Override
    public String toString() {
        return "ColoredBag{" + "kindOfBag=" + kindOfBag + ", mayContains=" + mayContains + '}';
    }

    void addThisToMap(Map<String, List<String>> db) {
        mayContains.forEach(item
                -> db.merge(item, new ArrayList(List.of(kindOfBag)),
                        (old, nw) -> {
                            old.add(kindOfBag);
                            return old;
                        }));
    }    
}

public class Day7 {

    private static final String TARGET_COLOR = "shiny gold";

    Map<String, List<String>> dbForPart1;

    Map<String, Map<String,Integer>> dbForPart2;

    String[] lines;

    Pattern bagExtract = Pattern.compile("[a-z ]*(?= bag)");
    
    Pattern extendedBagExtract = Pattern.compile("(\\d )?[a-z ]*(?= bag)");

    Day7() {
        dbForPart1 = new HashMap<>();
        dbForPart2 = new HashMap<>();
    }

    ;
    
    public Day7(String path) throws FileNotFoundException {
        try ( java.util.Scanner sc = new Scanner(new File(path))) {
            lines = sc.useDelimiter("\n").tokens().toArray(String[]::new);
        }
        dbForPart1 = new HashMap<>();
        dbForPart2 = new HashMap<>();
    }

    void calculate() {
       Utils.printResult("Day 7",part1(lines),part2(lines));
    }

    int part1(String[] input) {
        List<ColoredBag> bagsList = makeBagsList(input);

        bagsList.forEach(bag -> bag.addThisToMap(dbForPart1));
        return new BagManager(dbForPart1).findColorsP1(TARGET_COLOR);
    }

    private List<ColoredBag> makeBagsList(String[] input) {
        var bagsList = Arrays.stream(input)
                .map(this::processLine)
                .filter(list -> !list.isEmpty())
                .map(ColoredBag::new)
                .toList();
        return bagsList;
    }

    int part2(String[] input) {
        List<FilledBag> bagsList = makeFilledBagsList(input);
        bagsList.forEach(bag -> dbForPart2.put(bag.kindOfBag, bag.bags));; //bag.mayContains));
        return new BagManagerForP2(dbForPart2).CachedFindInternals(TARGET_COLOR)-1;
    }

    private List<FilledBag> makeFilledBagsList(String[] input) {
       var bagsList = Arrays.stream(input)
                .map(this::processLine2)
                .filter(list -> !list.isEmpty())
                .map(FilledBag::new)
                .toList();
        return bagsList;
    }
    
    List<String> processLine(String line) {
        var matcher = bagExtract.matcher(line);
        var list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group().strip());
        }

        if (list.get(0).endsWith("contain no other")) {
            list.remove(0);
        }
        list.removeIf(String::isBlank);
        return list;
    }
    
    List<String> processLine2(String line) {       
        var matcher = extendedBagExtract.matcher(line);
        var list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group().strip());
        }
        
        if (list.get(0).endsWith("contain no other")) {
            list.remove(0);
        }
        
        list.removeIf(String::isBlank);
        return list;
    }

    static class BagManager {

        Map<String, List<String>> db;

        Deque<String> toProcess;

        Set<String> processed;

        public BagManager(Map<String, List<String>> db) {
            this.db = db;
            toProcess = new LinkedList<>();
            processed = new HashSet<>();
        }

        int findColorsP1(String initialColor) {

            toProcess.addAll(db.get(initialColor));
            while (!toProcess.isEmpty()) {
                processColor(toProcess.pop());
            }
            return processed.size();
        }

        private void processColor(String color) {
            if (!processed.contains(color)) {
                processed.add(color);
                toProcess.addAll(db.getOrDefault(color, Collections.EMPTY_LIST));
            }
        }

     //   private int CachedFindInternals(String TARGET_COLOR) {
   //         return 0;
     //   }
    }

    static class FilledBag {

        String kindOfBag;

        Map<String, Integer> bags;

        public FilledBag(List<String> parsedLine) {

            bags = new HashMap<>();
            kindOfBag = parsedLine.get(0);
            for (int i = 1; i < parsedLine.size(); i++) {
                var s = parsedLine.get(i);
                bags.put(s.substring(2),Integer.parseInt(s.substring(0, 1)));
            }
        }
    }

    private static class BagManagerForP2 {
       
        private final Map<String, Map<String, Integer>> db;
        
        private final Map<String, Integer> cache;

        public BagManagerForP2(Map<String, Map<String,Integer>> dbForPart2) {
            this.db = dbForPart2;         
            cache = new HashMap<>((int)(db.size() * 1.4));
            
        }

        int CachedFindInternals(String color) {
            if (cache.containsKey(color)) {
                return cache.get(color);
            } else {
                int result = findInternals(color);
                cache.put(color, result);
                return result;
            }
        }

        private int findInternals(String color) {
            if (Objects.isNull(db.get(color))) {
                return 1;
            }
            int result = 1;
            Set<String> toSearch = db.get(color).keySet();
            for (String s : toSearch) {
                result += CachedFindInternals(s) * db.get(color).get(s);
            }
            return result;
        }        
    }
}
