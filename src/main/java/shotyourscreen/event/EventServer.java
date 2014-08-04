package shotyourscreen.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import shotyourscreen.network.PacketHandler;
import shotyourscreen.network.packet.PacketServerCheck;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class EventServer
{
	@SubscribeEvent
	public void playerConnected(EntityJoinWorldEvent e)
	{
		if (!e.world.isRemote)
		{
			if (e.entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP)e.entity;
				IMessage msg = new PacketServerCheck.MessageServerCheck();
				PacketHandler.instance().net().sendTo(msg, player);
			}
		}
	}
}