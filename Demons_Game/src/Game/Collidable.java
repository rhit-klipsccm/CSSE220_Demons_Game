package Game;

import java.awt.Rectangle;

/**
 * Defines how objects can interact with the in game world
 * Gives defined boundaries
 */
public interface Collidable {
	/**
	 * Updates entity state and position using world dimensions
	 * @param worldWidth
	 * @param worldHeight
	 */
	void update(int worldWidth, int worldHeight);
	
	/**
	 * Provides hit-boxes for all Collidable objects
	 * @return
	 */
	Rectangle getBounds();
	
	
	
}
