
public class Tile {
	enum Type{
		Grass, Wall, Hole,
	}
	private Type type;
	private Vector2[] collisionPoints;
	
	public Tile(Type type) {
		this.type = type;
	}

	
	//GETTERS & SEZTZETRS
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}