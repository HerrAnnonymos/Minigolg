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
		
		tiles[10][10] = new Tile(Tile.Type.Wall);
		tiles[11][10] = new Tile(Tile.Type.Wall);
		tiles[12][10] = new Tile(Tile.Type.Wall);
	}

	
	//GETTERS
	public Tile[][] getTiles() {
		return tiles;
	}
	
	
}
