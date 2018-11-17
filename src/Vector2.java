
public class Vector2 {
	private float x, y;
	
	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public static float abs(float x, float y){
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public float abs(){
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vector2 add(Vector2 v){
		return new Vector2(x + v.getX(), y + v.getY());
	}
	
	public Vector2 subtract(Vector2 v){
		return new Vector2(x - v.getX(), y - v.getY());
	}
	
	public float scalar(Vector2 v){
		return x*v.getX() + y*v.getY();
	}
	
	public Vector2 rotate(double a){
	    return new Vector2((float)(x * Math.cos(a) - y * Math.sin(a)), (float)(x * Math.sin(a) + y * Math.cos(a)));
	}
	
	public double angle(Vector2 v){
		return (double) Math.acos( ( scalar(v)/(abs() * v.abs() )* (Math.PI/180) ));
	}
	
	public Vector2 scale(float scale){
		return new Vector2(x*scale, y*scale);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
