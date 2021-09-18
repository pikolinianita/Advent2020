/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day3 {

    String[] inputLines;
    private int sizeX;
    private int sizeY;

    Day3() {
    }

    Day3(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\r\n");
            inputLines = sc
                    .tokens()
                    .toArray(String[]::new);
            calcSize();
        }
    }

    Day3(String[] inputLines) {
        this.inputLines = inputLines;
        calcSize();
    }

    private void calcSize() {
        sizeX = inputLines[0].length();
        sizeY = inputLines.length;
    }

    void calculate() {
        Utils.printResult("Day 3", part1(), part2());
    }

    long part1() {
        return countTrees(3, 1);
    }

    long countTrees(int dx, int dy) {
        return IntStream.range(0, sizeY / dy)
                .map(n -> getPos(n * dx, n * dy))
                .filter(i -> i == '#')
                .count();
    }

    long part2() {
        return    countTrees(1, 1)
                * countTrees(3, 1)
                * countTrees(5, 1)
                * countTrees(7, 1)
                * countTrees(1, 2);
    }

    char getPos(int x, int y) {
        return inputLines[y].charAt(x % sizeX);
    }

    public static void main(String[] args) throws FileNotFoundException {
        var day = new Day3("day3.txt");
        day.calculate();
    }
}
