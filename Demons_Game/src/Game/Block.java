package Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Block {

    public static final int SIZE = 40;

    private int type;

    private static ArrayList<BufferedImage> pathBricks = new ArrayList<>();
    private static BufferedImage wallBrick;
    private static BufferedImage exitBrick;

    private static boolean loaded = false;
    private static final Random random = new Random();

    public Block(int type) {
        if (!loaded) {
            loadTileSprites();
            loaded = true;
        }
        this.type = type;
    }

    private static void loadTileSprites() {
        try {
            wallBrick = ImageIO.read(Block.class.getResource("/Game/TileSprites/WallBrickTile.png"));
            exitBrick = ImageIO.read(Block.class.getResource("/Game/TileSprites/ExitTile.png"));

            pathBricks.add(ImageIO.read(Block.class.getResource("/Game/TileSprites/PathTile.png")));
            pathBricks.add(ImageIO.read(Block.class.getResource("/Game/TileSprites/PathTile2.png")));

            System.out.println("Tiles loaded successfully.");
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Failed to load tile sprites!");
            e.printStackTrace();
        }
    }

    public boolean isWalkable() {
        return type != 0;
    }

    public void draw(Graphics2D g2, int x, int y) {
        switch (type) {
            case 0 -> {
                if (wallBrick != null)
                    g2.drawImage(wallBrick, x, y, SIZE, SIZE, null);
            }
            case 1 -> {
                if (exitBrick != null)
                    g2.drawImage(exitBrick, x, y, SIZE, SIZE, null);
            }
            case 2 -> {
                if (!pathBricks.isEmpty()) {
                    int randNum = random.nextInt(pathBricks.size());
                    g2.drawImage(pathBricks.get(randNum), x, y, SIZE, SIZE, null);
                }
            }
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}