
public class GameManager {
	
	private Renderer renderer;
	private Map map;
	
	public GameManager() {
		init();
		long prev = System.currentTimeMillis();
		while(true) {
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
	
}
