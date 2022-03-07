package pl.lcc.advent2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.LongBinaryOperator;

/**
 *
 * @author piko
 */
public class Day18 {

    Operators op = new Operators();

    List<String> expressions;

    public Day18(String path) throws FileNotFoundException {
        try ( Scanner sc = new Scanner(new File(path))) {
            sc.useDelimiter("\n");
            expressions = sc.tokens().toList();
        }
    }

    Day18() {
    }

    class Operators {

       private Map<String, LongBinaryOperator> operatorMap = new HashMap<>();

        Operators() {
            operatorMap.put("+", (a, b) -> a + b);
            operatorMap.put("*", (a, b) -> a * b);
        }

        long apply(String opx, long x, long y) {
            return operatorMap.get(opx).applyAsLong(x, y);
        }
    }

    class Calculation {

        long result;
        String sign;
        boolean finished;
        Iterator<String> iter;
        Deque<Long> stack;

        public Calculation(Iterator<String> iter) {
            this.result = 0;
            this.sign = "+";
            this.finished = false;
            this.iter = iter;
            stack = new LinkedList<>();
            stack.addFirst(0L);
        }

        public long calculate() {
            while (!finished && iter.hasNext()) {
                result = processToken(iter.next());
            }
            return result;
        }

        long processToken(String next) {
            return switch (next) {
                case "1","2","3","4","5","6","7","8","9" ->
                    op.apply(sign, result, Integer.parseInt(next));
                case "+","*" -> {
                    sign = next;
                    yield result;
                }
                case "(" ->
                    op.apply(sign, result, new Calculation(iter).calculate());
                case ")" -> {
                    finished = true;
                    yield result;
                }
                default ->  throw new  IllegalArgumentException("wrong token: " + next);
                
            };
        }
        
          public long calculateP2() {
            while (!finished && iter.hasNext()) {
                processTokenP2(iter.next());
            }
            return stack.stream().reduce((a, b) -> a * b).get();
        }

        void processTokenP2(String next) {
            switch (next) {
                case "1","2","3","4","5","6","7","8","9" -> {
                    if (sign.equals("*")) {
                        stack.addLast(Long.parseLong(next));
                    } else {
                        stack.addLast(op.apply(sign, stack.removeLast(), Long.parseLong(next)));
                    }}     
                case "+","*" ->  sign = next;                
                case "(" -> {
                    var subValue = new Calculation(iter).calculateP2();
                    if (sign.equals("*")) {
                        stack.addLast(subValue);
                    } else {
                        stack.addLast(op.apply(sign, stack.removeLast(), subValue));
                    }
                }
                case ")" -> finished = true;                
                default ->  throw new IllegalArgumentException("wrong token: " + next);
            }
        }

    }

    class Calculator {

        long result;
        String sign;

        public Calculator() {
            this.result = 0;
            this.sign = "+";
        }

        long calculateExpression(String expression) {
            Iterator<String> iter = makeIterator(expression);
            return new Calculation(iter).calculate();

        }

        long calculateExpressionP2(String expression) {
            Iterator<String> iter = makeIterator(expression);
            return new Calculation(iter).calculateP2();
        }
    

    private Iterator<String> makeIterator(String expression) {
        try (var sc = new Scanner(expression)){
        return sc.tokens()
                .flatMap(s -> Arrays.stream(s.split("")))
                .toList().iterator();
        }
    }
    
    }

    void calculate() {
        Utils.printResult("Template", part1(), part2());
    }

    long part1() {
        return expressions.stream()
                .mapToLong(s -> new Calculator().calculateExpression(s))
                .sum();
    }

    long part2() {
        return expressions.stream()
                .mapToLong(s -> new Calculator().calculateExpressionP2(s))
                .sum();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Day18("day18.txt").calculate();
    }

}

