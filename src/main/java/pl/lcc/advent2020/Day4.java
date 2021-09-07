/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class DocEntry{

    Map<String,String> passport;
    
    static Set<String> allFields = Set.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid");
    
    static Set<String> ValidEyes = Set.of( "amb", "blu", "brn", "gry", "grn", "hzl", "oth");
   
    public DocEntry(String entry) {
        var fields = new Scanner(entry).tokens().flatMap(s -> Arrays.stream(s.split(":"))).toArray(String[]::new);
      //  var fields = entry.split(" |:|\r\n");
        passport = new HashMap<>();
        putFieldsMap(0,fields);
    }

    private void putFieldsMap(int i, String[] fields) {
        passport.put(fields[i], fields[i+1]);
        if (fields.length -2 != i)
            putFieldsMap(i+2, fields);
    }
    
    public boolean validForP1(){
       selfValidate();
       return passport.keySet().containsAll(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));        
    }
    
    private boolean selfValidate() {
        if (allFields.containsAll(passport.keySet())) {
            return true;
        } else {
            throw new IllegalStateException("Something wrong with parser");
        }
    }
    
     public boolean validForP2(){
     if (!validForP1()) return false;      
     return byrValid() && yitValid() && eyrValid() && hgtValid() && hclValid() && eclValid() && pidValid();
     }

    private boolean byrValid() {
       var bir = passport.get("byr");
      return isValidNumberInRange(bir, 1920, 2002);
    }

    private boolean yitValid() {
         return isValidNumberInRange(passport.get("iyr"), 2010, 2020);
    }  
    
    private boolean isValidNumberInRange(String s, int min, int max) {
        if (s.matches("\\d+")) {
            var sValue = Integer.parseInt(s);
            if (sValue >= min && sValue <= max) {
                return true;
            }
        }
        return false;
    }

    private boolean eyrValid() {
       return isValidNumberInRange(passport.get("eyr"), 2020, 2030);
    }

    private boolean hgtValid() {
        var hgt = passport.get("hgt");
        if (hgt.matches("\\d+cm")) {
            return isValidNumberInRange(hgt.substring(0, hgt.length() - 2), 150, 193);
        }
        if (hgt.matches("\\d+in")) {
            return isValidNumberInRange(hgt.substring(0, hgt.length() - 2), 59, 76);
        }
        return false;
    }

    private boolean hclValid() {
        var hcl = passport.get("hcl");
        return (hcl.length() == 7 ) && hcl.matches("#[0-9a-f]{6}");
    }

    private boolean eclValid() {
        return ValidEyes.contains(passport.get("ecl"));
    }

    private boolean pidValid() {
        var pid = passport.get("pid");
        return (pid.length() == 9 ) && pid.matches("\\d+");
    }
    
}


public class Day4 {
    
    String[] entries;
   
   public Day4 (String path) throws FileNotFoundException{
        try (java.util.Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n\r\n");
            entries = sc.tokens().toArray(String[]::new);
        }
        
    }
    
    Day4(){};
      
    void calculate(){
       Utils.printResult("Day 4", part1(entries),part2(entries));
    }
    
    int part1(String[] input) {
    return (int) Arrays.stream(input)
              .map(DocEntry::new)
              .filter(DocEntry::validForP1)
              .count();
    }

    int part2(String[] input) {    
         return (int) Arrays.stream(input)
              .map(DocEntry::new)
              .filter(DocEntry::validForP2)
              .count();
    }
}
