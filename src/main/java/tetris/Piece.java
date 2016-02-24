// Copyright (c) 2008-2016  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by SRIN on 2/24/2016.
 */
public class Piece implements RotateableGrid, Grid{

    private final char[][] blocks;

    /*
        A piece must have equal width and height
        and the length of the edge must be odd
     */
    public Piece(String shape){
        this.blocks = Grids.fromString(shape);
        assert blocks.length == blocks[0].length;
        assert blocks.length % 2 == 1;

    }

    private Piece(char[][] blocks) {
        this.blocks = blocks;
    }

    public Piece rotateRight() {
        return new Piece(rotateRight(blocks));
    }

    public Piece rotateLeft() {
        return rotateRight().rotateRight().rotateRight();
    }

    public Piece rotateClockwise() {
        return new Piece(rotateClockwise(blocks));
    }

    public Piece rotateCounterClockwise() {
        return rotateClockwise().rotateClockwise().rotateClockwise();
    }

    /**
     * Coordinates when rotating a 3x3 grid right:
     * <pre>
     * before   after
     * 0-0      0-2
     * 0-1      1-2
     * 0-2      2-2
     * 1-0      0-1
     * 1-1      1-1
     * 1-2      2-1
     * 2-0      0-0
     * 2-1      1-0
     * 2-2      2-0
     * </pre>
     * Coordinates when rotating a 5x5 grid right:
     * <pre>
     * before   after
     * 0-0      0-4
     * 0-1      1-4
     * 0-2      2-4
     * 0-3      3-4
     * 0-4      4-4
     * 1-0      0-3
     * 1-1      1-3
     * 1-2      2-3
     * 1-3      3-3
     * 1-4      4-3
     * ...
     * </pre>
     */
    private static char[][] rotateClockwise(char[][] x) {
        char[][] rotated = new char[x.length][x.length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                rotated[j][(x.length - 1) - i] = x[i][j];
            }
        }
        return rotated;
    }


//    private static char[][] linesToArrays(String blocks) {
//        String[] lines = blocks.split("\n");
//        char[][] x = new char[lines.length][lines.length];
//        for (int i = 0; i < x.length; i++) {
//            for (int j = 0; j < x[i].length; j++) {
//                x[i][j] = lines[i].charAt(j);
//            }
//        }
//        return x;
//    }

    public String toString() {
        return Grids.toString(this);

//        return new GridAsciiView(this).toString();
//        return arraysToLines(blocks);
    }

//    private static String arraysToLines(char[][] x) {
//        StringBuilder sb = new StringBuilder();
//        for (char[] line : x) {
//            for (char c : line) {
//                sb.append(c);
//            }
//            sb.append('\n');
//        }
//        return sb.toString();
//    }

    private static char[][] rotateRight(char[][] x) {
        char[][] rotated = new char[x.length][x.length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                rotated[j][(x.length - 1) - i] = x[i][j];
            }
        }
        return rotated;
    }

    @Override
    public int rows() {
        return blocks.length;
    }

    @Override
    public int columns() {
        return blocks[0].length;
    }

    public char cellAt(Point point) {
        return blocks[point.row][point.col];
    }
}
