import java.awt.Frame;
import java.util.List;

import com.jogamp.nativewindow.WindowClosingProtocol.WindowClosingMode;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Renderer implements GLEventListener{
	
	public static final float SCREEN_WIDTH = 1600;
	public static final float SCREEN_HEIGHT = 900;
	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	private GLCanvas glCanvas;
	private GLCapabilities capabilities;
	private GLProfile profile;
	private Frame frame;
	private GL2 gl;
	
	private Map map;
	private Ball ball;
	private Player player;
	private float scale;
	private Vector2 offset;
	
	public Renderer(Map map, Ball ball, Player player, float scale, Vector2 offset) {
		this.map = map;
		this.ball = ball;
		this.player = player;
		this.scale = scale;
		this.offset = offset;
		initWindow();
	}
	
	public void drawMap(Map map, float scale, Vector2 offset) {
		Tile[][] tiles = map.getTiles();
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				Vector2 pos = new Vector2(x * TILE_WIDTH, y * TILE_HEIGHT);
				
				switch (tiles[x][y].getType()) {
				case Grass:
					gl.glColor3f(0f, 1f, 0f);
					break;
				case Wall:
					gl.glColor3f(1f, 0f, 0f);
					break;
				case Hole:
					break;
				}
				drawTile(pos, TILE_WIDTH, TILE_HEIGHT);
			}
		}
	}
	
	public void drawVertex2f(float v1, float v2) {
		gl.glVertex2f(((v1 + offset.getX())*scale)/SCREEN_WIDTH, ((v2 + offset.getY())*scale)/SCREEN_HEIGHT);
	}
	
	public void drawVector(Vector2 v, float x, float y){
		gl.glLineWidth(3f);
		gl.glBegin(GL2.GL_LINES);
		drawVertex2f(x, y);
		drawVertex2f(x + v.getX(), y + v.getY());
		gl.glEnd();
	}
	
	public void drawBall(Vector2 pos, float radius) {
		gl.glColor3f(0, 0, 1);
		drawCircle(pos, radius, 20);
	}
	
	public void drawCircle(Vector2 pos, float r, int num_segments) { 
		gl.glBegin(GL2.GL_POLYGON);
		for(int i = 0; i < num_segments; i++) { 
			float theta = 2.0f * 3.1415926f * (float)i / (float)(num_segments);//get the current angle 

			float x = (float) (r * Math.cos(theta));//calculate the x component 
			float y = (float) (r * Math.sin(theta));//calculate the y component 

			drawVertex2f(x+ pos.getX(), y + pos.getY());//output vertex 

		} 
		gl.glEnd(); 
	}
	
	public void drawTile(Vector2 pos, float width, float height) {
		gl.glBegin(GL2.GL_POLYGON);
		drawVertex2f(pos.getX(), pos.getY());
		drawVertex2f(pos.getX()  + width, pos.getY());
		drawVertex2f(pos.getX() + width, pos.getY() + height);
		drawVertex2f(pos.getX(), pos.getY()  + height);
		gl.glEnd();
		
		/*gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1f, 0f, 0f);
		drawVertex2f(pos.getX(), pos.getY());
		drawVertex2f(pos.getX()  + width, pos.getY());
		drawVertex2f(pos.getX() + width, pos.getY() + height);
		drawVertex2f(pos.getX(), pos.getY()  + height);
		gl.glEnd();*/
	}
	
	public void drawCollisionPoints(List<Vector2> collisionPoints){
		for (Vector2 v: collisionPoints){
			gl.glBegin(GL2.GL_POLYGON);
			drawVertex2f((v.getX() - 2), (v.getY() - 2));
			drawVertex2f((v.getX() - 2), (v.getY() + 2));
			drawVertex2f((v.getX() + 2), (v.getY() + 2));
			drawVertex2f((v.getX() + 2), (v.getY() - 2));
			gl.glEnd();
		}
	}
	
	public void initWindow() {
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
		glCanvas = new GLCanvas(capabilities);
		glCanvas.addGLEventListener(this);
		glCanvas.addMouseListener(player);
		glCanvas.addKeyListener(player);
		glCanvas.addMouseMotionListener(player);
		glCanvas.setDefaultCloseOperation(WindowClosingMode.DISPOSE_ON_CLOSE);
		frame = new Frame("Minigolg");
		frame.add(glCanvas);
		frame.setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
		frame.setVisible(true);
		
		
		FPSAnimator animator = new FPSAnimator(60);
		animator.add(glCanvas);
		animator.start();
		
	}
	
	@Override
	public void display(GLAutoDrawable drawable) {
		drawMap(map, scale, offset);	
		drawBall(ball.getPos(), ball.getRadian());
		drawCollisionPoints(map.getCollisionPoints());
		
		drawVector(ball.getVel(), ball.getPos().getX(), ball.getPos().getY());
		gl.glColor3f(1, 1, 1);
		drawVector(ball.collisonVector.scale(20), ball.closestCP.getX() - ball.collisonVector.scale(10).getX(), ball.closestCP.getY() - ball.collisonVector.scale(10).getY());
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
		gl.glViewport(0, 0, (int)SCREEN_WIDTH, (int)SCREEN_HEIGHT);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {		
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public Vector2 getOffset() {
		return offset;
	}

	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}

}
