package Game;

import java.awt.Graphics2D;

public class Map {

    private int rows;
    private int cols;
    private Block[][] blocks;

    public Map() {
        rows = 10;
        cols = 15;

        blocks = new Block[rows][cols];

        // 0 is the wall, 1 is the exit, 2 is the floor; this is the same with the name in the map. To better remember this..
        int[][] mapData = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,0,2,2,2,2,2,2,1,0},
            {0,2,0,0,0,2,0,2,0,0,0,0,2,2,0},
            {0,2,2,2,0,2,2,2,2,2,2,0,2,2,0},
            {0,0,0,2,0,0,0,0,0,0,2,0,0,2,0},
            {0,2,2,2,2,2,2,2,2,0,2,2,2,2,0},
            {0,2,0,0,0,0,0,0,2,0,0,0,0,2,0},
            {0,2,2,2,2,2,2,0,2,2,2,2,2,2,0},
            {0,2,0,0,0,0,2,0,0,0,0,0,0,2,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                blocks[r][c] = new Block(mapData[r][c]);
            }
        }
    }

    public void draw(Graphics2D g2) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = c * Block.SIZE;
                int y = r * Block.SIZE;
                blocks[r][c].draw(g2, x, y);
            }
        }
    }

    
    
    
    
    // Idk how future code is written, so I just write some kind of code maybe would help
    
    public boolean isWalkable(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return false;
        }
        return blocks[row][col].isWalkable();
    }

    public boolean isExit(int row, int col) {
        return blocks[row][col].getType() == 1;
    }
}
