// Copyright (c) 2008-2016  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by SRIN on 2/24/2016.
 */
public class Block implements RotateableGrid{

    private final char cell;

    public Block(char cell) {
        this.cell = cell;
    }

    public int rows() {
        return 1;
    }

    public int columns() {
        return 1;
    }

    public char cellAt(Point point) {
        return cell;
    }

    public RotateableGrid rotateRight() {
        return this;
    }

    public RotateableGrid rotateLeft() {
        return this;
    }

    public RotateableGrid rotateClockwise() {
        return this;
    }

    public RotateableGrid rotateCounterClockwise() {
        return this;
    }

}
