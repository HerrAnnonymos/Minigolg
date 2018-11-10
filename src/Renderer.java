import java.awt.Frame;

import com.jogamp.nativewindow.WindowClosingProtocol.WindowClosingMode;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class Renderer implements GLEventListener{

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	
	private GLCanvas glCanvas;
	private GLCapabilities capabilities;
	private GLProfile profile;
	private Frame frame;
	private GL2 gl;
	
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
					drawGrass(xPos, yPos, width, height);
					break;
				case Wall:
					break;
				case Hole:
					break;
				}
			}
		}
	}
	
	public void drawGrass(float x, float y, float width, float height) {
		//gl.glBegin(GL2.GL_LINES);
		gl.glColor3f(1, 0, 0);
		gl.glVertex2f(x, y);
		gl.glVertex2f(width, height);
		//gl.glEnd();
	}
	
	public void initWindow() {
		profile = GLProfile.get(GLProfile.GL2);
		capabilities = new GLCapabilities(profile);
		glCanvas = new GLCanvas(capabilities);
		glCanvas.addGLEventListener(this);
		glCanvas.setSize(1600, 900);
		frame = new Frame("Minigolg");
		frame.setSize(1600, 900);
		frame.add(glCanvas);
		frame.setVisible(true);
	}
	
	
	@Override
	public void display(GLAutoDrawable drawable) {
		gl = drawable.getGL().getGL2();
		gl.glBegin (GL2.GL_LINES);
		gl.glVertex3f(0.50f,-0.50f,0);
		gl.glVertex3f(-0.50f,0.50f,0);
		drawMap(map, scale, xOffset, yOffset);
		gl.glEnd();
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		//gl = drawable.getGL().getGL2();
		
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}

}
