package danylibs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class PlayerUtils
{
	public static void print(EntityPlayer player, String msg)
	{
		if (player.worldObj.isRemote)
			return;
		
		player.addChatMessage(
				new ChatComponentText(msg)
				);
	}
	
	public static EntityPlayer getPlayer(String username)
	{
		WorldServer[] worlds = MinecraftServer.getServer().worldServers;
		for (WorldServer w : worlds)
		{
			for (Object objP : w.playerEntities.toArray())
			{
				EntityPlayer p = (EntityPlayer)objP;
				if (p.getCommandSenderName().equals(username))
				{
					return p;
				}
			}
		}
		return null;
	}
	
	public static ArrayList<EntityPlayer> getPlayersInRange(Coords e, int radius)
	{
		List list = e.world.getEntitiesWithinAABB(
				EntityPlayer.class,
				AxisAlignedBB.getBoundingBox(
						e.x-0.5f,
						e.y-0.5f,
						e.z-0.5f,
						e.x + 0.5f,
						e.y + 0.5f,
						e.z + 0.5f
						).expand(
						radius, radius, radius
						));
		ArrayList<EntityPlayer> listPlayers = new ArrayList<EntityPlayer>();
		listPlayers.addAll(list);
		return listPlayers;
	}
	
	class Coords
	{
		public World world;
		public double x;
		public double y;
		public double z;
		
		public Coords() {}
		
		public Coords(World world, double x, double y, double z)
		{
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public Coords(int dim, double x, double y, double z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
			{
				this.world = Minecraft.getMinecraft().theWorld;
			}
			else
			{
				for (WorldServer i : MinecraftServer.getServer().worldServers)
				{
					if (i.provider.dimensionId == dim)
					{
						this.world = (World)i;
						break;
					}
				}
			}
		}
	}
}