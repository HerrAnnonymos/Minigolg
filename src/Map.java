import java.util.ArrayList;

public class Map {
	
	public static final int COLLISION_POINTS_PER_EDGE = 4;
	private Tile[][] tiles;
	private ArrayList<Vector2> collisionPoints;
	
	public Map(int width, int height) {
		tiles = new Tile[width][height];
		collisionPoints = new ArrayList<Vector2>();
	}
	
	public void initTestMap() {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				if(x == 3) tiles[x][y] = new Tile(Tile.Type.Wall);
				else if(x == 30) tiles[x][y] = new Tile(Tile.Type.Wall);
				else if(y == 3) tiles[x][y] = new Tile(Tile.Type.Wall);
				else if(y == 30) tiles[x][y] = new Tile(Tile.Type.Wall);
				else tiles[x][y] = new Tile(Tile.Type.Grass);
			}
		}
		
		tiles[10][10] = new Tile(Tile.Type.Wall);
		tiles[11][10] = new Tile(Tile.Type.Wall);
		tiles[12][10] = new Tile(Tile.Type.Wall);
	}
	
	public void initCollisionPoints(){
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				if(!tiles[x][y].getType().equals(Tile.Type.Wall)) continue;
				for (int i = 0; i < COLLISION_POINTS_PER_EDGE; i++) {
					//North
					if((y < tiles[x].length - 1) && (!tiles[x][y + 1].getType().equals(Tile.Type.Wall))){
						collisionPoints.add(new Vector2(x*Renderer.TILE_WIDTH + (Renderer.TILE_WIDTH/COLLISION_POINTS_PER_EDGE)*i, y*Renderer.TILE_HEIGHT + Renderer.TILE_HEIGHT));
					}					
					//South
					if((y > 0) && (!tiles[x][y - 1].getType().equals(Tile.Type.Wall))){
						collisionPoints.add(new Vector2(x*Renderer.TILE_WIDTH + (Renderer.TILE_WIDTH/COLLISION_POINTS_PER_EDGE)*i, y*Renderer.TILE_HEIGHT));
					}
					//East
					if((x < tiles.length - 1) && (!tiles[x + 1][y].getType().equals(Tile.Type.Wall))){
						collisionPoints.add(new Vector2(x*Renderer.TILE_WIDTH + Renderer.TILE_WIDTH, y*Renderer.TILE_HEIGHT + (Renderer.TILE_HEIGHT/COLLISION_POINTS_PER_EDGE)*i));
					}					
					//West
					if((x > 0) && (!tiles[x - 1][y].getType().equals(Tile.Type.Wall))){
						collisionPoints.add(new Vector2(x*Renderer.TILE_WIDTH, y*Renderer.TILE_HEIGHT + (Renderer.TILE_HEIGHT/COLLISION_POINTS_PER_EDGE)*i));
					}
				}		
			}
		}
	}

	
	//GETTERS
	public Tile[][] getTiles() {
		return tiles;
	}

	public ArrayList<Vector2> getCollisionPoints() {
		return collisionPoints;
	}

	public void setCollisionPoints(ArrayList<Vector2> collisionPoints) {
		this.collisionPoints = collisionPoints;
	}
	
	
}
