/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Scanner;
import static pl.lcc.advent2020.Day12.Direction.*;

public class Day12 {
   
    String[] parsedString;
    
    Day12(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            parsedString = sc
                    .tokens()
                    .toArray(String[]::new);
        }
    }    
      
    void calculate(){
        Utils.printResult("Template",part1(),part2(null));
    }
    
    int part1() {
        var ship = new Ship();
        return Arrays.stream(parsedString)
                .map(ShipCommand::new)
                .reduce(ship, (Ship s, ShipCommand c) -> s.move(c),Ship::dummyMethod)
                .getManchattan();
    }

    int part2(int[] input) {
        var ship = new ShipWithWaypoint();
        return Arrays.stream(parsedString)
                .map(ShipCommand::new)
                .reduce(ship, (Ship s, ShipCommand c) -> s.move(c),Ship::dummyMethod)
                .getManchattan();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        new Day12("day12.txt").calculate();
        
        //1714 too high
    }
    
    enum Direction{
    EAST(1,0) {
        @Override
        Direction turnLeft(int arc) {
        return switch (arc) {
                case 90, -270 -> NORTH;
                case -90, 270 -> SOUTH;    
                case 180,-180 -> WEST;                    
                default -> throw new UnsupportedOperationException("Wrong arc value;"); 
            };
        }

        @Override
        Direction turnRight(int arc) {
            return turnLeft(- arc);
        }
    },
    WEST(-1,0) {
        @Override
        Direction turnLeft(int arc) {
            return switch (arc) {
                case 90, -270 -> SOUTH;
                case -90, 270 -> NORTH;    
                case 180,-180 -> EAST;                    
                default -> throw new UnsupportedOperationException("Wrong arc value;"); 
            };
        }

        @Override
        Direction turnRight(int arc) {
              return turnLeft(- arc);
        }
    },
    NORTH(0,1) {
        @Override
        Direction turnLeft(int arc) {
            return switch (arc) {
                case 90, -270 -> WEST;
                case -90, 270 -> EAST;    
                case 180,-180 -> SOUTH;                    
                default -> throw new UnsupportedOperationException("Wrong arc value;"); 
            };
        }

        @Override
        Direction turnRight(int arc) {
              return turnLeft(- arc);
        }
    },
    SOUTH(0,-1) {
        @Override
        Direction turnLeft(int arc) {
            return switch (arc) {
                case 90, -270 -> EAST;
                case -90, 270 -> WEST;    
                case 180,-180 -> NORTH;                    
                default -> throw new UnsupportedOperationException("Wrong arc value: " + arc); 
            };
        }

        @Override
        Direction turnRight(int arc) {
             return turnLeft(- arc);
        }
    };
    
    int x;
    int y;
    
    Direction (int x, int y){
        this.y = y;
        this.x = x;
    }
    
    abstract Direction turnLeft(int arc);
    abstract Direction turnRight(int arc);
    
    int x() {
        return x;
    }
    int y() {
        return y;    
    }
    
}

static class ShipCommand{
    int value;
    String letter;

    public ShipCommand(String commandLine) {
        System.out.println(commandLine);
        letter = commandLine.substring(0, 1);
        value = Integer.parseInt(commandLine.substring(1));
    }

    public int getValue() {
        return value;
    }

    public String getLetter() {
        return letter;
    }  
    
}

static class Ship{
    int x;
    int y;
    Direction bowDirection;     

    public Ship() {
        x = 0;
        y = 0;
        bowDirection = EAST;
    }
    
    public Ship move(ShipCommand comm){
        switch (comm.getLetter()){
            case "L" ->  bowDirection = bowDirection.turnLeft(comm.getValue()); 
            case "R" ->  bowDirection = bowDirection.turnRight(comm.getValue());
            case "F" -> moveTowards(bowDirection, comm.getValue());
            case "N" -> moveTowards(NORTH, comm.getValue());
            case "S" -> moveTowards(SOUTH, comm.getValue());
            case "W" -> moveTowards(WEST, comm.getValue());
            case "E" -> moveTowards(EAST, comm.getValue());
            default -> throw new UnsupportedOperationException("Wrong direction letter value: " + comm.getLetter()); 
        }
        System.out.println(this);
        return this;
    }

    private void moveTowards(Direction direction, int value) {
        x+= direction.x()*value;
        y+= direction.y()*value;
    }
    
    Ship dummyMethod(Ship s){
        throw new ConcurrentModificationException("Class cannot be used in parallel");
    }

    int getManchattan(){
        return Math.abs(x)+Math.abs(y);
    }
    
    @Override
    public String toString() {
        return "Ship{" + "x=" + x + ", y=" + y + ", bowDirection=" + bowDirection + '}';
    }
    
}

static class ShipWithWaypoint extends Ship {
    int wpX;
    int wpY;
    
    ShipWithWaypoint(){
        wpX = 10;
        wpY = 1;
    }
    
    @Override
    public ShipWithWaypoint move(ShipCommand comm) {
        switch (comm.getLetter()){
            case "L" ->  rotateWPLeft(comm.getValue()); 
            case "R" ->  rotateWPRigth(comm.getValue());
            case "F" -> moveToWp(comm.getValue());
            case "N" -> moveWp(NORTH, comm.getValue());
            case "S" -> moveWp(SOUTH, comm.getValue());
            case "W" -> moveWp(WEST, comm.getValue());
            case "E" -> moveWp(EAST, comm.getValue());
            default -> throw new UnsupportedOperationException("Wrong direction letter value: " + comm.getLetter()); 
        }
        System.out.println(this);
        return this;
    }

    private void moveWp(Direction direction, int value) {
        wpX+= direction.x()*value;
        wpY+= direction.y()*value;
    }

    private void moveToWp(int value) {
        x+= wpX * value;
        y+= wpY * value;
    }

    private void rotateWPRigth(int value) {
       while(value > 0){
           rot90R();
           value -= 90;        
       }
    }

    private void rotateWPLeft(int value) {
        rotateWPRigth(360-value);
    }

    @Override
    public String toString() {
        return "ShipWithWaypoint{" + "wpX=" + wpX + ", wpY=" + wpY + ", x=" + x + ", y=" + y +'}';
    }

    private void rot90R() {
       int tmp = wpY;
       wpY = - wpX;
       wpX = tmp;
    } 
    
}
    
}
