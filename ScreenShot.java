import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ScreenShot {

	public ScreenShot() throws AWTException {
		
//		Robot robot = new Robot();
//		
//		BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] screens = ge.getScreenDevices();
		
		int screen = 0;
		
		try {
			ImageIO.write(new Robot(screens[screen]).createScreenCapture(new Rectangle(screens[screen].getDefaultConfiguration().getBounds())), "png", new File("C:\\Users\\Cameron\\Desktop\\screenshot.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getScreenShot(){
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] screens = ge.getScreenDevices();
		
		int screen = 0;
		
		try {
			return new Robot(screens[screen]).createScreenCapture(new Rectangle(screens[screen].getDefaultConfiguration().getBounds()));
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	public static void main(String[] args) {
		try {
			new ScreenShot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
