import java.util.Vector;

public class Map {
	private Tile[][] tiles;
	
	public Map(int width, int height) {
		tiles = new Tile[width][height];
		
	}
	
	public void initTestMap() {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				tiles[x][y] = new Tile(Tile.Type.Grass);
			}
		}
	}

	
	//GETTERS
	public Tile[][] getTiles() {
		return tiles;
	}
	
	
}
