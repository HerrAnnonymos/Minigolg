import java.util.ArrayList;

import com.jogamp.newt.event.MouseAdapter;

public class Ball{

	private float radian;
	private Vector2 pos;
	private Vector2 vel;
	
	public Vector2 closestCP;
	public  Vector2 secondClosestCP;
	public Vector2 collisonVector;
	
	public Ball(Vector2 pos, float radian) {
		this.pos = pos;
		this.radian = radian;
		vel = new Vector2(0, 0);
		closestCP = new Vector2(0, 0);
		secondClosestCP = new Vector2(0, 0);
		collisonVector = new Vector2(0, 0);
	}
	
	public void move(float maxMoveDistance, float delta){
		Vector2 tempVel = vel.scale(delta);
		if(maxMoveDistance < tempVel.abs()){
			collisonVector = closestCP.subtract(secondClosestCP);
			double angle = Math.min(vel.angle(collisonVector), vel.angle(collisonVector.scale(-1)));
			vel = vel.rotate(-Math.PI - 2*angle);
		}
		pos = pos.add(vel.scale(delta));
	}
	
	public float calcMaxMoveDistance(ArrayList<Vector2> collisionPoints){
		float maxMoveDistance = vel.abs();
		for(Vector2 v: collisionPoints){
			float distance = pos.subtract(v).abs()-radian;
			if(distance < maxMoveDistance){
				secondClosestCP = closestCP;
				closestCP = v;
				maxMoveDistance = distance;
			}
		}
		System.out.println(maxMoveDistance);
		return maxMoveDistance;
	}
	
	public void putt(Vector2 vel){
		this.vel = this.vel.add(vel);
	}

	public float getRadian() {
		return radian;
	}

	public Vector2 getPos() {
		return pos;
	}

	public Vector2 getVel() {
		return vel;
	}

	public void setRadian(float radian) {
		this.radian = radian;
	}

	public void setPos(Vector2 pos) {
		this.pos = pos;
	}

	public void setVel(Vector2 vel) {
		this.vel = vel;
	}

}
