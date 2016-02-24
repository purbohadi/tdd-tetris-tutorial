// Copyright (c) 2008-2015  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.Arrays;

public class Board implements Grid{

   private char[][] blocks;

    private MoveablePiece fallingBlock;

    public Board(int rows, int columns) {
        this.blocks = new char[rows][columns];
        for (char[] tmp : blocks) {
            Arrays.fill(tmp,EMPTY);
        }
    }

    public Board(String initialState) {
        blocks = Grids.fromString(initialState);
    }

    public void drop(RotateableGrid piece) {
        if (isFallingBlock()) {
            throw new IllegalStateException("Another block may not be dropped when one is already falling");
        }

        Point topCenter = new Point(0, columns() / 2 - piece.columns() / 2);
        fallingBlock = new MoveablePiece(piece).moveTo(topCenter);
    }

    public void tick() {
        MoveablePiece test = fallingBlock.moveDown();
        if (conflictsWithBoard(test)) {
            stopFallingBlock();
        } else {
            fallingBlock = test;
        }
    }

    // Conflict Checks

    private boolean conflictsWithBoard(MoveablePiece test) {
        return outsideBoard(test) || hitsStationaryBlock(test);
    }

    private boolean conflictsWithBoard(Point p) {
        return outsideBoard(p) || hitsStationaryBlock(p);
    }

    private boolean outsideBoard(MoveablePiece test) {
        for (Point p : test.allBlocksOnBoard()) {
            if (outsideBoard(p)) {
                return true;
            }
        }
        return false;
    }

    private boolean outsideBoard(Point p) {
        return p.row >= rows()
                || p.col < 0
                || p.col >= columns();
    }

    private boolean hitsStationaryBlock(MoveablePiece test) {
        for (Point p : test.allBlocksOnBoard()) {
            if (hitsStationaryBlock(p)) {
                return true;
            }
        }
        return false;
    }

    private boolean hitsStationaryBlock(Point p) {
        return blocks[p.row][p.col] != EMPTY;
    }


    public void moveLeft() {
        moveIfNoConflict(fallingBlock.moveLeft());
    }

    public void moveRight() {
        moveIfNoConflict(fallingBlock.moveRight());
    }

    public void moveDown() {
        moveIfNoConflict(fallingBlock.moveDown());
    }

    private void moveIfNoConflict(MoveablePiece test) {
        if (!conflictsWithBoard(test)) {
            fallingBlock = test;
        }
    }

    public boolean isFallingBlock() {
        return fallingBlock != null;
    }

    private void stopFallingBlock() {
        assert isFallingBlock();
        copyToBoard(fallingBlock);
        fallingBlock = null;
    }

    private void copyToBoard(MoveablePiece piece) {
        for (Point point : Grids.allPointsOf(this)) {
            if (piece.isAt(point) && piece.cellAt(point) != EMPTY) {
                blocks[point.row][point.col] = piece.cellAt(point);
            }
        }
    }

    public String toString() {
        return Grids.toString(this);
    }

    public char cellAt(Point point) {
        if (fallingBlock != null && fallingBlock.isAt(point)) {
            return fallingBlock.cellAt(point);
        } else {
            return blocks[point.row][point.col];
        }
    }

    public int rows() {
        return blocks.length;
    }

    public int columns() {
        return blocks[0].length;
    }


    // Rotating

    public void rotateClockwise() {
        rotateIfNoConflict(fallingBlock.rotateClockwise());
    }

    public void rotateCounterClockwise() {
        rotateIfNoConflict(fallingBlock.rotateCounterClockwise());
    }

    private void rotateIfNoConflict(MoveablePiece test) {
        if (!conflictsWithBoard(test)) {
            fallingBlock = test;
        } else if (hasRoomOnRight(test)) {
            rotateIfNoConflict(test.moveRight());
        } else if (hasRoomOnLeft(test)) {
            rotateIfNoConflict(test.moveLeft());
        }
    }

    private boolean hasRoomOnLeft(MoveablePiece test) {
        for (Point p : test.allBlocksOnBoard()) {
            if (conflictsWithBoard(p.moveLeft())) {
                return false;
            }
        }
        return true;
    }

    private boolean hasRoomOnRight(MoveablePiece test) {
        for (Point p : test.allBlocksOnBoard()) {
            if (conflictsWithBoard(p.moveRight())) {
                return false;
            }
        }
        return true;
    }

}
