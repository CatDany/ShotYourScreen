package shotyourscreen.proxy;

import net.minecraft.command.ServerCommandManager;
import shotyourscreen.event.EventServer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import danylibs.EventBusHelper;

public class ServerProxy implements IProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent e) {}
	
	@Override
	public void init(FMLInitializationEvent e) {}
	
	@Override
	public void postInit(FMLPostInitializationEvent e) {}

	@Override
	public Side side()
	{
		return Side.SERVER;
	}
}