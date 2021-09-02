/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Teresa
 */
public class Day4Test {
    
    String[] falseLines = {"eyr:1972 cid:100 hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
        "iyr:2019 hcl:#602927 eyr:1967 hgt:170cm ecl:grn pid:012533040 byr:1946",
        "hcl:dab227 iyr:2012 ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
        "hgt:59cm ecl:zzz eyr:2038 hcl:74454a iyr:2023 pid:3556412378 byr:2007"};
    
    String[] trueLines = {"pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 hcl:#623a2f",
        "eyr:2029 ecl:blu cid:129 byr:1989 iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
        "hcl:#888785 hgt:164cm byr:2001 iyr:2015 cid:88 pid:545766238 ecl:hzl eyr:2022",
        "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"};
    
    
    public Day4Test() {
    }

    @Test
    public void testSomeMethod() throws FileNotFoundException {
        var x = new Day4("Day4.txt");
        x.calculate();
        System.out.println(x.entries.length);
        System.out.println("123".matches("\\d+"));
    }
    
    @Test
    void testDocEntry(){
        var de = new DocEntry("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm");
        assertTrue(de.validForP1());
    }
    @Test
    void testDocEntry2(){
        var de = new DocEntry("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm");
        assertTrue(de.validForP2());
    }
    
    @Test
     void testWrongEntries(){
        var falseList = Arrays.stream(falseLines)
                 .map(DocEntry::new )
                 .map(DocEntry::validForP2)
                 .toList();
        assertIterableEquals(List.of(false, false, false, false), falseList);
     }
     
     @Test
     void testTrueEntries(){
        var trueList = Arrays.stream(trueLines)
                 .map(DocEntry::new )
                 .map(DocEntry::validForP2)
                 .toList();
        assertIterableEquals(List.of(true, true, true, true), trueList);
     }
     
     @Test
     void testDelimiters() throws FileNotFoundException{
         var sc = new Scanner(new File("day4.txt"));
         System.out.println(sc.delimiter().pattern());
     }
}

