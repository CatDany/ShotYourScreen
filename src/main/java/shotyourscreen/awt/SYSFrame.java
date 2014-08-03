package shotyourscreen.awt;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import net.minecraft.client.Minecraft;
import shotyourscreen.ShotYourScreen;

public class SYSFrame extends JFrame
{
	public BufferedImage image;
	
	public SYSFrame(File file)
	{
		super();
		
		try
		{
			this.image = ImageIO.read(file);
		}
		catch (Throwable t)
		{
			ShotYourScreen.logger.warn("Failed to read downloaded image!");
			ShotYourScreen.logger.catching(t);
			return;
		}
		
		setTitle("Screenshot");
		setSize(getImgWidth(), getImgHeight());
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		SYSScreen screen = new SYSScreen(this);
		add(screen);
		addWindowListener(new SYSScreen.WindowControl(screen));
		
		ShotYourScreen.logger.info("Screenshot window is opened.");
	}
	
	private int getImgWidth()
	{
		int width = image.getWidth();
		if (width > Minecraft.getMinecraft().displayWidth)
		{
			width = Minecraft.getMinecraft().displayWidth;
		}
		return width;
	}
	
	private int getImgHeight()
	{
		int height = image.getHeight() + 10;
		if (height > Minecraft.getMinecraft().displayHeight)
		{
			height = Minecraft.getMinecraft().displayHeight + 10;
		}
		return height;
	}
}