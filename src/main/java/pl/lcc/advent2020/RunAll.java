/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.FileNotFoundException;

public class RunAll {
    public static void main(String[] args) throws FileNotFoundException {
        new Day1("day1.txt").calculate();
        new Day2("day2.txt").calculate();
        new Day3("day3.txt").calculate();
        new Day4("day4.txt").calculate();
        new Day5("day5.txt").calculate();
        new Day6("day6.txt").calculate();
        new Day7("day7.txt").calculate();
    }
}
