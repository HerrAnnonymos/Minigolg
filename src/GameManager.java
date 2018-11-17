
public class GameManager {
	
	private Renderer renderer;
	private Map map;
	static boolean gameRunning = true;
	
	public GameManager() {
		init();
		long prev = System.currentTimeMillis();
		while(gameRunning) {
			double delta = (System.currentTimeMillis() - prev) * 1000d;
			prev = System.currentTimeMillis();
			update();
			fixedUpdate(delta);
		}
	}
	
	public void init() {
		map = new Map(60, 60);
		map.initTestMap();
		renderer = new Renderer();
	}
	
	public void update() {
		//Renderer
		renderer.setMapParameters(map, 1, 0, 0);
		//renderer.drawMap(map, 1, 0, 0);
	}
	
	public void fixedUpdate(double delta) {
		//Physics
	}

	public static boolean isGameRunning() {
		return gameRunning;
	}

	public static void setGameRunning(boolean gameRunning) {
		GameManager.gameRunning = gameRunning;
	}
	
}
