// Copyright (c) 2008-2016  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by SRIN on 2/24/2016.
 */
public class Tetromine implements RotateableGrid, Grid{


    public static final Tetromine I_SHAPE = new Tetromine(2, 1, "" +
            ".....\n" +
            ".....\n" +
            "IIII.\n" +
            ".....\n" +
            ".....\n");
    public static final Tetromine J_SHAPE = new Tetromine(4, 0, "" +
            "..J\n" +
            "JJJ\n" +
            "...\n");
    public static final Tetromine L_SHAPE = new Tetromine(4, 0, "" +
            "L..\n" +
            "LLL\n" +
            "...\n");
    public static final Tetromine O_SHAPE = new Tetromine(1, 0, "" +
            ".OO\n" +
            ".OO\n" +
            "...\n");
    public static final Tetromine S_SHAPE = new Tetromine(2, 0, "" +
            "...\n" +
            ".SS\n" +
            "SS.\n");
    public static final Tetromine T_SHAPE = new Tetromine(4, 0, "" +
            ".T.\n" +
            "TTT\n" +
            "...\n");
    public static final Tetromine Z_SHAPE = new Tetromine(2, 1, "" +
            "...\n" +
            "ZZ.\n" +
            ".ZZ\n");

    private final Grid[] rotations;
    private final int currentRotation;

    public Tetromine(int maxRotations, int currentRotation, String blocks) {
        Piece firstRotation = firstRotation(new Piece(blocks), currentRotation);
        this.rotations = allRotations(firstRotation, maxRotations);
        this.currentRotation = currentRotation;
    }

    private static Piece firstRotation(Piece piece, int currentRotation) {
        for (int i = 0; i < currentRotation; i++) {
            piece = piece.rotateLeft();
        }
        return piece;
    }

    private static Grid[] allRotations(Piece firstRotation, int maxRotations) {
        Piece[] x = new Piece[maxRotations];
        x[0] = firstRotation;
        for (int i = 1; i < x.length; i++) {
            x[i] = x[i - 1].rotateRight();
        }
        return x;
    }

    private Tetromine(Grid[] rotations, int currentRotation) {
        while (currentRotation < 0) {
            currentRotation += rotations.length;
        }
        this.rotations = rotations;
        this.currentRotation = currentRotation % rotations.length;
    }

    @Override
    public int rows() {
        return self().rows();
    }

    @Override
    public int columns() {
        return self().columns();
    }

    @Override
    public char cellAt(Point point) {
        return self().cellAt(point);
    }

    @Override
    public Tetromine rotateRight() {
        return new Tetromine(rotations, currentRotation+1);
    }

    @Override
    public Tetromine rotateLeft() {
        return new Tetromine(rotations, currentRotation-1);
    }

    public Tetromine rotateClockwise() {
        return new Tetromine(rotations, currentRotation + 1);
    }

    public Tetromine rotateCounterClockwise() {
        return new Tetromine(rotations, currentRotation - 1);
    }

    private Grid self() {
        return rotations[currentRotation];
    }

    public String toString() {
        return Grids.toString(this);
    }

}
