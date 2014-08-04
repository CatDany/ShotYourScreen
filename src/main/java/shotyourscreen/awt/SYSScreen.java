package shotyourscreen.awt;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JPanel;

import shotyourscreen.ShotYourScreen;

public class SYSScreen extends JPanel implements Runnable
{
	public final Thread thread = new Thread(this);
	
	private final SYSFrame frame;
	private boolean isRunning;
	
	public SYSScreen(SYSFrame frame)
	{
		this.frame = frame;
		
		thread.setDaemon(true);
		thread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawImage(frame.image, 0, 0, null);
	}

	@Override
	public void run()
	{
		while (isRunning)
		{
			repaint();
		}
	}
	
	public void close()
	{
		this.isRunning = false;
		ShotYourScreen.logger.info("Screenshot window is closed.");
	}
	
	public static class WindowControl extends WindowAdapter
	{
		private final SYSScreen screen;
		
		public WindowControl(SYSScreen screen)
		{
			this.screen = screen;
		}
		
		@Override
		public void windowClosing(WindowEvent e)
		{
			screen.close();
		}
	}
}