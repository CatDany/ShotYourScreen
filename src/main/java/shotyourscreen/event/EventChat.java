package shotyourscreen.event;

import net.minecraftforge.event.ServerChatEvent;
import shotyourscreen.network.PacketHandler;
import shotyourscreen.network.packet.MessageImgur;
import shotyourscreen.network.packet.MessageImgurClient;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import danylibs.Paragraph;
import danylibs.PlayerUtils;

public class EventChat
{
	public static final String COMMAND = "$BROWSEIMG";
	
	@SubscribeEvent
	public void chat(ServerChatEvent e)
	{
		if (e.message.startsWith(COMMAND))
		{
			e.setCanceled(true);
			try
			{
				String imgurId = e.message.substring(COMMAND.length() + 1);
				IMessage msg = new MessageImgurClient(imgurId);
				PacketHandler.instance().net().sendTo(msg, e.player);
			}
			catch (Throwable t)
			{
				PlayerUtils.print(e.player, Paragraph.rose + "Command is invalid.");
			}
		}
	}
}