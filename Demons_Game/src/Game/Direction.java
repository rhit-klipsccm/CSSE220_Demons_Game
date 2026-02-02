package Game;

public enum Direction {
	UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
	
	private Direction opposite;
	
	static {
	UP.opposite = DOWN;
	DOWN.opposite = UP;
	LEFT.opposite = RIGHT;
	RIGHT.opposite = LEFT;
	}
	
	public final int dx;
	public final int dy;
	
	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public Direction opposite() {
		return opposite;
	}
	
}
