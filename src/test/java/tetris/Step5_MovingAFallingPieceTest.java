// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

//@Ignore("contains no test")
@RunWith(NestedJUnit.class)
public class Step5_MovingAFallingPieceTest extends Assert {

    // Step 5: It's your turn now
    // - Remove the @Ignore annotation from this class
    // - The test names have been provided, you just need to fill in the test body
    // - Next step: RotatingAFallingPieceTest

    private static final Piece PIECE = new Piece("" +
            ".X.\n" +
            ".X.\n" +
            ".X.\n");
    private static final int LOTS_OF_TIMES = 10;

    private Board board = new Board(5, 8);


    public class When_a_piece_is_falling_on_an_empty_board {

        @Before
        public void dropPiece() {
            board.drop(PIECE);
            assertEquals("" +
                    "....X...\n" +
                    "....X...\n" +
                    "....X...\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        // TODO: a falling piece can be moved left
        @Test
        public void it_can_be_moved_left() {
            board.moveLeft();
            assertEquals("" +
                    "...X....\n" +
                    "...X....\n" +
                    "...X....\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        // TODO: a falling piece can be moved right
        @Test
        public void it_can_be_moved_right() {
            board.moveRight();
            assertEquals("" +
                    ".....X..\n" +
                    ".....X..\n" +
                    ".....X..\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        // TODO: a falling piece can be moved down
        @Test
        public void it_can_be_moved_down() {
            board.moveDown();
            assertEquals("" +
                    "........\n" +
                    "....X...\n" +
                    "....X...\n" +
                    "....X...\n" +
                    "........\n", board.toString());
        }

        // TODO: it will not move left over the board
        @Test
        public void it_cannot_be_moved_left_over_the_board() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveLeft();
            }
            assertEquals("" +
                    "X.......\n" +
                    "X.......\n" +
                    "X.......\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        // TODO: it will not move right over the board
        @Test
        public void it_cannot_be_moved_right_over_the_board() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveRight();
            }
            assertEquals("" +
                    ".......X\n" +
                    ".......X\n" +
                    ".......X\n" +
                    "........\n" +
                    "........\n", board.toString());
        }

        // TODO: it will not move down over the board (will stop falling)
        @Test
        public void it_cannot_be_moved_down_over_the_board() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveDown();
            }
            assertEquals("" +
                    "........\n" +
                    "........\n" +
                    "....X...\n" +
                    "....X...\n" +
                    "....X...\n", board.toString());
            assertTrue(board.isFallingBlock());
        }

    }

    public class When_a_piece_is_falling_and_some_blocks_are_in_its_way {

        @Before
        public void dropPiece() {
            board = new Board("" +
                    "........\n" +
                    "Y......Y\n" +
                    "Y......Y\n" +
                    "Y......Y\n" +
                    "YYYYY..Y\n");

            board.drop(PIECE);
            assertEquals("" +
                    "....X...\n" +
                    "Y...X..Y\n" +
                    "Y...X..Y\n" +
                    "Y......Y\n" +
                    "YYYYY..Y\n", board.toString());
        }

        // TODO: it cannot be moved left if another piece is in the way
        @Test
        public void it_cannot_be_moved_left_over_the_other_blocks() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveLeft();
            }
            assertEquals("" +
                    ".X......\n" +
                    "YX.....Y\n" +
                    "YX.....Y\n" +
                    "Y......Y\n" +
                    "YYYYY..Y\n", board.toString());
        }

        // TODO: it cannot be moved right if another piece is in the way
        @Test
        public void it_cannot_be_moved_right_over_the_other_blocks() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveRight();
            }
            assertEquals("" +
                    "......X.\n" +
                    "Y.....XY\n" +
                    "Y.....XY\n" +
                    "Y......Y\n" +
                    "YYYYY..Y\n", board.toString());
        }

        // TODO: it cannot be moved down if another piece is in the way (will stop falling)
        @Test
        public void it_cannot_be_moved_down_over_the_other_blocks() {
            for (int i = 0; i < LOTS_OF_TIMES; i++) {
                board.moveDown();
            }
            assertEquals("" +
                    "........\n" +
                    "Y...X..Y\n" +
                    "Y...X..Y\n" +
                    "Y...X..Y\n" +
                    "YYYYY..Y\n", board.toString());
            assertTrue(board.isFallingBlock());
        }
    }
    // P.S. Take into consideration, that part of the piece's area may be empty cells.
    // Only non-empty cells should take part in the collision checks.
}
