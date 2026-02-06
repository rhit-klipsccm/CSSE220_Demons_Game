package Game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    private int rows;
    private int cols;
    private Block[][] blocks;
    private ArrayList<Integer> zombieSpawnRow = new ArrayList<>();
    private ArrayList<Integer> zombieSpawnCol = new ArrayList<>();

    public Map() {

//        int[][] mapData = {
//            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//            {0,2,2,2,2,2,0,2,2,2,2,2,2,1,0},
//            {0,2,0,0,0,2,0,2,0,0,0,0,2,2,0},
//            {0,2,2,2,0,2,2,2,2,2,2,0,2,2,0},
//            {0,0,0,2,0,0,0,0,0,0,2,0,0,2,0},
//            {0,2,2,2,2,2,2,2,2,0,2,2,2,2,0},
//            {0,2,0,0,0,0,0,0,2,0,0,0,0,2,0},
//            {0,2,2,2,2,2,2,0,2,2,2,2,2,2,0},
//            {0,2,0,0,0,0,2,0,0,0,0,0,0,2,0},
//            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
//        };
    	
    	try {
    		int[][] mapData = mapLoader("/Game/level.txt");

    		rows = mapData.length;
    		cols = mapData[0].length;

    		blocks = new Block[rows][cols];

    		for (int r = 0; r < rows; r++) {
    		    for (int c = 0; c < cols; c++) {
    		        int type = mapData[r][c];

    		        if (type == 3) {
    		            zombieSpawnRow.add(r);
    		            zombieSpawnCol.add(c);
    		            type = 2;
    		        }

    		        blocks[r][c] = new Block(type);
    		    }
    		}
    	} catch (Exception e) {
    		System.out.println("Failed to load /Game/level.txt");
    	}
    	
    	
    		
    	
    	}

    public void draw(Graphics2D g2) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                blocks[r][c].draw(g2, c * Block.SIZE, r * Block.SIZE);
            }
        }
    }

 

    public boolean canMove(Rectangle bounds) {

        int left   = bounds.x;
        int right  = bounds.x + bounds.width - 1;
        int top    = bounds.y;
        int bottom = bounds.y + bounds.height - 1;

        return isWalkable(top / Block.SIZE, left / Block.SIZE) && isWalkable(top / Block.SIZE, right / Block.SIZE) && isWalkable(bottom / Block.SIZE, left / Block.SIZE)&& isWalkable(bottom / Block.SIZE, right / Block.SIZE);
    }

    public boolean isWalkable(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return false; // outside map = wall
        }
        return blocks[row][col].isWalkable();
    }
    
    private int[][] mapLoader(String filename) {
    	ArrayList<int[]> mapArray = new ArrayList<int[]>();
//    	File mapFile = new File(filename);
    	Scanner scanner = new Scanner(
			        getClass().getResourceAsStream(filename)
			    );
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			int[] row = new int[line.length()];
			
			for (int col = 0; col < line.length(); col++) {
				row[col] = Character.getNumericValue(line.charAt(col));
			}
			mapArray.add(row);
		}
		scanner.close();
		
		int[][] map = new int[mapArray.size()][];
		for (int i = 0; i < mapArray.size(); i++) {
			map[i] = mapArray.get(i);
		}
		
		return map;
    	
    }

    public int getPixelWidth() {
        return cols * Block.SIZE;
    }

    public int getPixelHeight() {
        return rows * Block.SIZE;
    }
    
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    
    public int getZombieSpawnCount() {
        return zombieSpawnRow.size();
    }

    public int getZombieSpawnX(int index) {
        return zombieSpawnCol.get(index) * Block.SIZE;
    }

    public int getZombieSpawnY(int index) {
        return zombieSpawnRow.get(index) * Block.SIZE;
    }
    
  

    public boolean isExit(Rectangle bounds) {
        int row = bounds.y / Block.SIZE;
        int col = bounds.x / Block.SIZE;
        return isExit(row, col);
    }

    private boolean isExit(int row, int col) {
        return blocks[row][col].getType() == 1;
    }
}
