/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package pl.lcc.advent2020;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author piko
 */
class Day18Test {

    @ParameterizedTest
    @CsvSource({"1 + 2 * 3 + 4 * 5 + 6, 71",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 26",
        "5 + (8 * 3 + 9 + 3 * 4 * 3),  437",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 12240",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 13632"
    })
    public void testCalculate (String expression, int value) {
        assertThat(new Day18(). new Calculator().calculateExpression(expression)).isEqualTo(value);
    }

    @ParameterizedTest
    @CsvSource({"1 + 2 * 3 + 4 * 5 + 6, 231",
        "1 + (2 * 3) + (4 * (5 + 6)), 51",
        "2 * 3 + (4 * 5), 46",
        "5 + (8 * 3 + 9 + 3 * 4 * 3),  1445",
        "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4)), 669060",
        "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2, 23340"
    })
    public void testCalculateP2 (String expression, int value) {
        assertThat(new Day18(). new Calculator().calculateExpressionP2(expression)).isEqualTo(value);
    }
    
    
}
