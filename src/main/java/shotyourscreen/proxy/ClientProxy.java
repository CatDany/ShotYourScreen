package shotyourscreen.proxy;

import java.io.File;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.client.ClientCommandHandler;
import shotyourscreen.ShotYourScreen;
import shotyourscreen.awt.SYSFrame;
import shotyourscreen.client.KeyHandler;
import shotyourscreen.command.CommandBrowseImg;
import shotyourscreen.event.EventClient;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import danylibs.EventBusHelper;

public class ClientProxy implements IProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		File downloadedScreenshotsFolder = new File("downloaded-screenshots");
		if (!downloadedScreenshotsFolder.exists())
		{
			downloadedScreenshotsFolder.mkdir();
			ShotYourScreen.logger.info("Created an empty folder for downloaded screenshots.");
		}
	}

	@Override
	public void init(FMLInitializationEvent e)
	{
		ShotYourScreen.keyHandler = new KeyHandler();
		ShotYourScreen.keyHandler.initKeybindings();
		EventBusHelper.checkBusAndRegister(new EventClient());
		ClientCommandHandler.instance.registerCommand(new CommandBrowseImg());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {}

	@Override
	public Side side()
	{
		return Side.CLIENT;
	}
}