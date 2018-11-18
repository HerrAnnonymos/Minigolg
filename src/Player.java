import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Player implements MouseListener, KeyListener, MouseMotionListener{

	private Ball ball;
	private GameManager gameManager;
	private Vector2 mousePos;
	
	public Player(Ball ball, GameManager gameManager) {
		this.ball = ball;
		this.gameManager = gameManager;
		mousePos = new Vector2(0, 0);
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}


	@Override
	public void keyPressed(KeyEvent e) {	
		float xOffset = 0;
		float yOffset = 0;
		int k = e.getKeyCode();
		
		if(k == KeyEvent.VK_LEFT) xOffset++;
		if(k == KeyEvent.VK_RIGHT) xOffset--;
		if(k == KeyEvent.VK_UP) yOffset--;
		if(k == KeyEvent.VK_DOWN) yOffset ++;
		
		gameManager.moveCamera(0, new Vector2(xOffset, yOffset).scale(10));
	}


	@Override
	public void keyReleased(KeyEvent e) {		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			ball.setPos(mousePos);
			ball.setVel(new Vector2(0, 0));
		}
		else{
			Vector2 v = new Vector2(mousePos.getX()-ball.getPos().getX(), mousePos.getY()-ball.getPos().getY());
			ball.putt(v.scale(0.5f));			
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {		
	}


	@Override
	public void mouseReleased(MouseEvent e) {		
	}


	@Override
	public void mouseEntered(MouseEvent e) {		
	}


	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.setX(e.getX());
		mousePos.setY(Renderer.SCREEN_HEIGHT-e.getY());
	}

}
