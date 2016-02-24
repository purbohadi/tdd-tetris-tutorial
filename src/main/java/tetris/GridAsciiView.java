// Copyright (c) 2008-2016  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris;

/**
 * Created by SRIN on 2/24/2016.
 */
public class GridAsciiView {

    private final Grid grid;

    public GridAsciiView(Grid grid) {
        this.grid = grid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < grid.rows(); row++) {
            for (int col = 0; col < grid.columns(); col++) {
//                sb.append(grid.cellAt(row, col));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static char[][] fromString(String s) {
        String[] lines = s.split("\n");
        char[][] grid = new char[lines.length][lines.length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = lines[row].charAt(col);
            }
        }
        return grid;
    }

}
