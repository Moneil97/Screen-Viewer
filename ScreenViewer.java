import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ScreenViewer extends JPanel implements Runnable, MouseMotionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	//keeps track of ups and fps
	private UpdatesAndFrames uaf = new UpdatesAndFrames();
	private int updateSleep = 20, updateTarget = 1000/updateSleep;//40sleep = 25fps target

	private int topBottomBorder = 39;
	private int sideBorder = 16;
	
	private Point p = new Point();

	private void gameUpdate() {
	}
	
	public void paintComponent(Graphics g1) {	
//		super.paintComponent(g1);
//		g = (Graphics2D) g1;
		//g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
//		ScreenShot.getScreenShot().getGraphics();
		
		g1.drawImage(ScreenShot.getScreenShot(), 0, 0, frame.getWidth()-sideBorder, frame.getHeight()-topBottomBorder, null);
		
//		g.setColor(Color.black);
//		g.drawString("UPS: " + uaf.getUPS(), 20, 20);
//		g.drawString("FPS: " + uaf.getFPS(), 20, 40);
//		g.drawString("Mouse at: " + p.toString().substring(14), 650, 550);
	}
	
	
	@Override
	public void run() {	
		Thread paintIt = new Thread(new Runnable() {
			public void run() {
				while (true){
					repaint();
					uaf.addFrameUpdate(); //1.5 Million FPS
					sleep(updateSleep/2);
				}
			}
		});
	
		Thread gameLoop = new Thread(new Runnable() {
			public void run() {
				gameTimer();
			}
		});
	
		paintIt.start();
		gameLoop.start();
	}

	private void gameTimer(){
		  final double GAME_HERTZ = updateTarget;
	      final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
	      double lastUpdateTime = System.nanoTime();
	      
	      while (true)
	      {
	         double now = System.nanoTime();
	            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
	            {
	            	gameUpdate();
					lastUpdateTime += TIME_BETWEEN_UPDATES;
					uaf.addGameUpdate();
					uaf.update();
					sleep(updateSleep/2);
	               //sleep(Math.min(updateSleep, 20 /*Make this a variable later*/));
	            }
	            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
	            {
	               lastUpdateTime = now - TIME_BETWEEN_UPDATES;
	            }
	      }
	}
	
	JFrame frame;

	public ScreenViewer() {
		frame = new JFrame();
		frame.setTitle("Game");

		System.out.println(ScreenShot.getScreenShot().getWidth() + "  " +  ScreenShot.getScreenShot().getHeight());
		ScreenShot.getScreenShot().getWidth();
		ScreenShot.getScreenShot().getHeight();
		frame.setSize(ScreenShot.getScreenShot().getWidth() + sideBorder, ScreenShot.getScreenShot().getHeight() + topBottomBorder);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(null);
		
		frame.add(this);
		frame.addMouseMotionListener(this);
		frame.setVisible(true);
	
		Thread gameThread = new Thread(this);
		gameThread.start();
		
	}

	public static void main(String[] args) {
		new ScreenViewer();
	}
	
	
	
	
	//Listeners

	@Override
	public void mouseMoved(MouseEvent e) {	
		p = e.getPoint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	private void sleep(int i){
		try {
			Thread.sleep(i);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void say(Object s) {
		System.out.println(this.getClass().getName() + ": " + s);
	}

}
