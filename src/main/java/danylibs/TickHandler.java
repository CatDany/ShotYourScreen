package danylibs;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import danylibs.EventBusHelper.EventBusType;

public abstract class TickHandler
{
	public static void registerTickHandler(TickHandler handler)
	{
		EventBusHelper.register(handler, EventBusType.FML);
	}
	
	@SubscribeEvent
	private void tickClient(TickEvent.ClientTickEvent e)
	{
		ITickData tickData = new TickDataClient(Minecraft.getMinecraft());
		if (e.phase == Phase.START)
		{
			tickStart(tickData);
		}
		else if (e.phase == Phase.END)
		{
			tickEnd(tickData);
		}
	}
	
	@SubscribeEvent
	private void tickPlayer(TickEvent.PlayerTickEvent e)
	{
		ITickData tickData = new TickDataPlayer(e.player);
		if (e.phase == Phase.START)
		{
			tickStart(tickData);
		}
		else if (e.phase == Phase.END)
		{
			tickEnd(tickData);
		}
	}
	
	@SubscribeEvent
	private void tickRender(TickEvent.RenderTickEvent e)
	{
		ITickData tickData = new TickDataRender(e.renderTickTime);
		if (e.phase == Phase.START)
		{
			tickStart(tickData);
		}
		else if (e.phase == Phase.END)
		{
			tickEnd(tickData);
		}
	}
	
	@SubscribeEvent
	private void tickServer(TickEvent.ServerTickEvent e)
	{
		ITickData tickData = new TickDataServer(MinecraftServer.getServer());
		if (e.phase == Phase.START)
		{
			tickStart(tickData);
		}
		else if (e.phase == Phase.END)
		{
			tickEnd(tickData);
		}
	}
	
	@SubscribeEvent
	private void tickWorld(TickEvent.WorldTickEvent e)
	{
		ITickData tickData = new TickDataWorld(e.world);
		if (e.phase == Phase.START)
		{
			tickStart(tickData);
		}
		else if (e.phase == Phase.END)
		{
			tickEnd(tickData);
		}
	}
	
	public abstract TickEvent.Type getTickType();
	
	public abstract void tickStart(ITickData tickData);
	
	public abstract void tickEnd(ITickData tickData);
	
	public static interface ITickData {}
	
	public static class TickDataClient implements ITickData
	{
		private Minecraft mc;
		
		private TickDataClient(Minecraft mc)
		{
			this.mc = mc;
		}
		
		public Minecraft getMinecraft()
		{
			return mc;
		}
	}
	
	public static class TickDataPlayer implements ITickData
	{
		private EntityPlayer player;
		
		private TickDataPlayer(EntityPlayer player)
		{
			this.player = player;
		}
		
		public EntityPlayer getPlayer()
		{
			return player;
		}
	}
	
	public static class TickDataRender implements ITickData
	{
		private float renderTickTime;
		
		private TickDataRender(float renderTickTime)
		{
			this.renderTickTime = renderTickTime;
		}
		
		public float getRenderTickTime()
		{
			return renderTickTime;
		}
	}
	
	public static class TickDataServer implements ITickData
	{
		private MinecraftServer server;
		
		private TickDataServer(MinecraftServer server)
		{
			this.server = server;
		}
		
		public MinecraftServer getRenderTickTime()
		{
			return server;
		}
	}
	
	public static class TickDataWorld implements ITickData
	{
		private World world;
		
		private TickDataWorld(World world)
		{
			this.world = world;
		}
		
		public World getWorld()
		{
			return world;
		}
	}
}