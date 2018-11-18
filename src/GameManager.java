
public class GameManager {
	
	private Renderer renderer;
	private Map map;
	private Ball ball;
	private Player player;
	static boolean gameRunning = true;
	
	public GameManager() {
		init();
		long prev = System.currentTimeMillis();
		while(gameRunning) {
			float delta = (System.currentTimeMillis() - prev)/1000f;
			prev = System.currentTimeMillis();
			fixedUpdate(delta);
		}
	}
	
	public void moveCamera(float scale, Vector2 offset) {
		renderer.setScale(renderer.getScale() + scale);
		renderer.setOffset(renderer.getOffset().add(offset));
	}
	
	public void init() {
		map = new Map(60, 60);
		map.initTestMap();
		map.initCollisionPoints();
		ball = new Ball(new Vector2(250, 250), 16);
		player = new Player(ball, this);
		renderer = new Renderer(map, ball, player, 1, new Vector2(0, 0));
	}
	
	public void fixedUpdate(float delta) {
		//Physics
		ball.move(ball.calcMaxMoveDistance(map.getCollisionPoints()), delta);
		
	}

	public static boolean isGameRunning() {
		return gameRunning;
	}

	public static void setGameRunning(boolean gameRunning) {
		GameManager.gameRunning = gameRunning;
	}
	
}
