/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */
package pl.lcc.advent2020;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.lcc.advent2020.Day24.Tile;

/**
 *
 * @author Nauczyciel
 */
class Day24Test {

    List<String> source = List.of(
            "sesenwnenenewseeswwswswwnenewsewsw",
            "neeenesenwnwwswnenewnwwsewnenwseswesw",
            "seswneswswsenwwnwse",
            "nwnwneseeswswnenewneswwnewseswneseene",
            "swweswneswnenwsewnwneneseenw",
            "eesenwseswswnenwswnwnwsewwnwsene",
            "sewnenenenesenwsewnenwwwse",
            "wenwwweseeeweswwwnwwe",
            "wsweesenenewnwwnwsenewsenwwsesesenwne",
            "neeswseenwwswnwswswnw",
            "nenwswwsewswnenenewsenwsenwnesesenew",
            "enewnwewneswsewnwswenweswnenwsenwsw",
            "sweneswneswneneenwnewenewwneswswnese",
            "swwesenesewenwneswnwwneseswwne",
            "enesenwswwswneneswsenwnewswseenwsese",
            "wnwnesenesenenwwnenwsewesewsesesew",
            "nenewswnwewswnenesenwnesewesw",
            "eneswnwswnwsenenwnwnwwseeswneewsenese",
            "neswnwewnwnwseenwseesewsenwsweewe",
            "wseweeenwnesenwwwswnew");

    @Test
    void testPart1() {

        var result = new Day24().new Board(source).getAnswer();
        assertThat(result).isEqualTo(10);

    }

    @Test
    void testOneTile() {
        var result = new Day24().new Board().parseString("sene");
        var result2 = new Day24().new Board().parseString("e");
        var resultIdentity = new Day24().new Board().parseString("nwwswee");
        assertThat(result).isEqualTo(result2);
        assertThat(resultIdentity).isEqualTo(new Day24.Tile(0, 0));
    }

    @Test
    void testAnswerSix() {
        var board = new Day24().new Board(List.of("e", "w", "se", "sw", "ne", "nw"));
        assertThat(board.getAnswer()).isEqualTo(6);
    }

    @Test
    void testAnswerZero() {
        var board = new Day24().new Board(List.of("e", "w", "se", "sw", "ne", "nw", "e", "w", "se", "sw", "ne", "nw"));
        assertThat(board.getAnswer()).isZero();
    }

    @ParameterizedTest
    @CsvSource({
        "e, 2, 0",
        "w , -2, 0",
        "nw, -1, 1",
        "ne, 1, 1",
        "se, 1, -1",
        "sw, -1, -1"
    })
    void testRose(String s, int x, int y) {
        var result = new Day24().new Board().parseString(s);
        assertThat(result).isEqualTo(new Day24.Tile(x, y));
    }

    @ParameterizedTest
    @CsvSource({"1,15", "2,12", "3,25",
         "10,37", "20, 132",
         "100, 2208"
    })
    void testAnimation(int n, int result) {
        var day = new Day24();
        var boardAfterAnimation = day.new Animator(day.new Board(source), n).result();
        assertThat(boardAfterAnimation.getAnswer()).isEqualTo(result);
    }
    
}
