package shotyourscreen.proxy;

import java.io.File;

import shotyourscreen.ShotYourScreen;
import shotyourscreen.awt.SYSFrame;
import shotyourscreen.client.KeyHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy implements IProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e) {}

	@Override
	public void init(FMLInitializationEvent e)
	{
		ShotYourScreen.keyHandler = new KeyHandler();
		ShotYourScreen.keyHandler.initKeybindings();
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {}

	@Override
	public Side side()
	{
		return Side.CLIENT;
	}
}