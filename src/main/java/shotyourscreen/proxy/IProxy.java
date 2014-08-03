package shotyourscreen.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;

public interface IProxy
{
	public void preInit(FMLPreInitializationEvent e);
	
	public void init(FMLInitializationEvent e);
	
	public void postInit(FMLPostInitializationEvent e);
	
	public Side side();
}