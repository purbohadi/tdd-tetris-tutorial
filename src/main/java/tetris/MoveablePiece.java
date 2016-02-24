// Copyright (c) 2008-2016  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

import java.util.*;

/**
 * Created by SRIN on 2/24/2016.
 */
public class MoveablePiece implements Grid {

    // Coordinates in use:
    // 'outer'  = coordinate in the parent grid (game board)
    // 'inner'  = coordinate in the contained piece
    // 'offset' = the inner coordinate [0,0] in outer coordinates

    private final Point offset;
    private final RotateableGrid innerPiece;

    public MoveablePiece(RotateableGrid innerPiece) {
        this(new Point(0, 0), innerPiece);
    }

    private MoveablePiece(Point offset, RotateableGrid innerPiece) {
        this.offset = offset;
        this.innerPiece = innerPiece;
    }

    public boolean outsideBoard(Grid board) {
        for (Point inner : Grids.allPointsOf(this)) {
            if (innerPiece.cellAt(inner) != EMPTY && outsideBoard(inner, board)) {
                    return true;
            }
        }
        return false;
    }

    private boolean outsideBoard(Point inner, Grid board) {
        Point outer = asOuter(inner);
        return outer.row >= board.rows()
                || outer.col < 0
                || outer.col >= board.columns();
    }

    public boolean isAt(Point outer) {
        Point inner = asInner(outer);
        return inner.row >= 0 && inner.row < innerPiece.rows()
                && inner.col >= 0 && inner.col < innerPiece.columns()
                && innerPiece.cellAt(inner) != EMPTY;
    }

    public MoveablePiece moveTo(Point offset) {
        return new MoveablePiece(offset, innerPiece);
    }

    public MoveablePiece moveDown() {
        return new MoveablePiece(new Point(offset.row + 1, offset.col), innerPiece);
    }
    public MoveablePiece moveLeft() {
        return new MoveablePiece(new Point(offset.row, offset.col - 1), innerPiece);
    }
    public MoveablePiece moveRight() {
        return new MoveablePiece(new Point(offset.row, offset.col + 1), innerPiece);
    }

    public MoveablePiece rotateClockwise() {
        return new MoveablePiece(offset, innerPiece.rotateClockwise());
    }

    public MoveablePiece rotateCounterClockwise() {
        return new MoveablePiece(offset, innerPiece.rotateCounterClockwise());
    }

    public List<Point> allBlocksOnBoard() {
        List<Point> innerPoints = Grids.allNonEmptyPointsOf(innerPiece);
        List<Point> outerPoints = new ArrayList<Point>();
        for (Point inner : innerPoints) {
            outerPoints.add(asOuter(inner));
        }
        return outerPoints;
    }

    public int rows() {
        return innerPiece.rows();
    }

    public int columns() {
        return innerPiece.columns();
    }

    public char cellAt(Point outer) {
        return innerPiece.cellAt(asInner(outer));
    }

    private Point asInner(Point outer) {
        return new Point(outer.row - offset.row, outer.col - offset.col);
    }

    private Point asOuter(Point inner) {
        return new Point(inner.row + offset.row, inner.col + offset.col);
    }

}
