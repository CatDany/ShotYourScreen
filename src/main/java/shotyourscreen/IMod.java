package shotyourscreen;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public interface IMod
{
	public void preInit(FMLPreInitializationEvent e);
	
	public void init(FMLInitializationEvent e);
	
	public void postInit(FMLPostInitializationEvent e);
	
	public void serverStarting(FMLServerStartingEvent e);
}