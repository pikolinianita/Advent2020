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

public class Day11 {   
    
    int[] input;
    int length;
    int height;
    boolean changed;

    Day11(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
           sc.useDelimiter("\n");
           var parsedString = sc
                    .tokens()
                    .toArray(String[]::new);
            length = parsedString[0].length();
            height = parsedString.length;
            input = String.join("", parsedString).chars().toArray();
            changed = false;        
                    
            System.out.println(input.length + " " + length + " " + height);
        }
    }

    Day11(int[] testArray) {
       input = testArray;
       length = 10;
       height = 10;
       changed = false;
    }

    void calculate() {
        Utils.printResult("Day11", part1(), part2(null));
    }

    long part1() {
        changed = true;
       
        while (changed){
            changed = false;
            sweepBoard();
        //System.out.println(toBoardString());
        }        
        return countChar('#');
    }

    void sweepBoard() {
        var output = new int[input.length];
        for(int x=0; x<length; x++) {
          //  System.out.println("x: " + x);
            for(int y=0; y<height; y++)
            {
                set(output, findNewValue (x,y), x,y );
            }
        }
        input = output;
    }

    int part2(int[] input) {
        return -1;
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
            destination [position] = value;
        } 
    }

    char findNewValue(int x, int y) {
        var thisValue = get(x, y);
        if (thisValue == '.') {
            return '.';
        }
        var neighboursCount = getNCount(x, y);
       // System.out.println(neighboursCount + " x: " + x + " y: " + y);
        if (neighboursCount == 0) {
            if(thisValue=='L') {
                changed = true;
            }
            return '#';
        } else if (neighboursCount >= 4) {
             if(thisValue=='#') {
                 changed = true;
             }
            return 'L';
        }
        else {
            return thisValue;
        }       
    }    

    int getNCount(int x, int y) {
        int count = 0;
        for (int dx = -1; dx != 2; dx++ ) {
            for (int dy = -1; dy != 2; dy++ ) {
                if (get (x+dx, y+dy) == '#') {
               //     System.out.println("x: " + (x+dx) + " y: " + (y+dy) + get (x+dx, y+dy));
                    count ++;
                }
            }
        }
        if (get (x,y)=='#'){
            count--;
        }
        return count;
    }

    private boolean isInRange(int x, int y) {
       return (x>=0 && x < length && y >=0 && y < height);
    }
    
    String toBoardString(){
        StringBuilder result = new StringBuilder() ;
        for (int i =0; i < height; i++){
            result.append( new String(input, i * length, length)).append("\n");           
        }
        return result.toString();
    }
}
