package shotyourscreen;

import org.apache.logging.log4j.Logger;

import shotyourscreen.client.KeyHandler;
import shotyourscreen.command.CommandBrowseImgServer;
import shotyourscreen.event.EventChat;
import shotyourscreen.event.EventServer;
import shotyourscreen.network.PacketHandler;
import shotyourscreen.proxy.IProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import danylibs.EventBusHelper;

@Mod
(
	modid = Refs.MOD_ID,
	name = Refs.MOD_NAME,
	version = Refs.VERSION,
	dependencies = Refs.DEPENDENCIES
)
public class ShotYourScreen implements IMod
{
	@Mod.Instance(Refs.MOD_ID)
	public static ShotYourScreen instance;
	
	@SidedProxy(clientSide = Refs.PROXY_CLIENT, serverSide = Refs.PROXY_SERVER)
	public static IProxy proxy;
	
	public static Logger logger;
	
	public static KeyHandler keyHandler;
	
	@Override
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		logger = e.getModLog();
		proxy.preInit(e);
	}
	
	@Override
	@Mod.EventHandler
	public void init(FMLInitializationEvent e)
	{
		PacketHandler.instance().init();
		EventBusHelper.checkBusAndRegister(new EventChat());
		EventBusHelper.checkBusAndRegister(new EventServer());
		proxy.init(e);
	}
	
	@Override
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit(e);
	}
	
	@Override
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent e)
	{
		e.registerServerCommand(new CommandBrowseImgServer());
	}
}