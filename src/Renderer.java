import java.awt.Frame;

import com.jogamp.nativewindow.WindowClosingProtocol.WindowClosingMode;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

public class Renderer implements GLEventListener{

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	private GLCanvas glCanvas;
	private GLCapabilities capabilities;
	private GLProfile profile;
	private Frame frame;
	private GL2 gl;
	private GLU glu;
	
	private Map map;
	private float scale;
	private float xOffset;
	private float yOffset;
	
	public Renderer() {
		scale = 1;
		xOffset = 0;
		yOffset = 0;
		initWindow();
	}
	
	public void setMapParameters(Map map, float scale, float xOffset, float yOffset) {
		this.map = map;
		this.scale = scale;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void drawMap(Map map, float scale, float xOffset, float yOffset) {
		Tile[][] tiles = map.getTiles();
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				float xPos = (xOffset + x * TILE_WIDTH) * scale;
				float yPos = (yOffset + y * TILE_HEIGHT) * scale;
				float width =  TILE_WIDTH * scale;
				float height = TILE_HEIGHT * scale;
				
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
				drawTile(xPos, yPos, width, height);
			}
		}
	}
	
	public void drawBall(float x, float y, float radius) {
		gl.glColor3f(0, 0, 1);
		drawCircle(x, y, radius, 20);
	}
	
	public void drawCircle(float cx, float cy, float r, int num_segments) { 
		gl.glBegin(GL2.GL_POLYGON);
		for(int i = 0; i < num_segments; i++) { 
			float theta = 2.0f * 3.1415926f * (float)i / (float)(num_segments);//get the current angle 

			float x = (float) (r * Math.cos(theta));//calculate the x component 
			float y = (float) (r * Math.sin(theta));//calculate the y component 

			gl.glVertex2f(x/1600 + cx/1600, y/900 + cy/900);//output vertex 

		} 
		gl.glEnd(); 
	}
	
	public void drawTile(float x, float y, float width, float height) {
		gl.glBegin(GL2.GL_POLYGON);
		gl.glVertex2f(x/1600f, y/900f);
		gl.glVertex2f(x/1600f  + width/1600f, y/900f);
		gl.glVertex2f(x/1600f + width/1600f, y/900f + height/900f);
		gl.glVertex2f(x/1600f, y/900f  + height/900f);
		gl.glEnd();
		
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glColor3f(1f, 0f, 0f);
		gl.glVertex2f(x/1600f, y/900f);
		gl.glVertex2f(x/1600f  + width/1600f, y/900f);
		gl.glVertex2f(x/1600f + width/1600f, y/900f + height/900f);
		gl.glVertex2f(x/1600f, y/900f  + height/900f);
		gl.glEnd();
	}
	
	public void initWindow() {
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
		glCanvas = new GLCanvas(capabilities);
		glCanvas.addGLEventListener(this);
		glCanvas.setSize(1600, 900);
		glCanvas.setDefaultCloseOperation(WindowClosingMode.DISPOSE_ON_CLOSE);
		frame = new Frame("Minigolg");
		frame.setSize(1600, 900);
		frame.add(glCanvas);
		frame.setVisible(true);
		glu = new GLU();
	}
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		drawMap(map, scale, xOffset, yOffset);	
		drawBall(300, 300, 16);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glViewport(0, 0, 1600, 900);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(0.0, 1.0, 0.0, 1.0, -1.0, 1.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

}
