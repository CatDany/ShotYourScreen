package shotyourscreen.event;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import shotyourscreen.ShotYourScreen;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventChat
{
	@SubscribeEvent
	public void clientChat(ClientChatReceivedEvent e)
	{
		if (e.message.getUnformattedText().contains("http://i.imgur.com/"))
		{
			int i = e.message.getUnformattedText().lastIndexOf("/") + 1;
			int j = e.message.getUnformattedText().lastIndexOf(".");
			try
			{
				String imgurId = e.message.getUnformattedText().substring(i, j);
				e.message.appendSibling(new ChatComponentText(" [")).appendSibling(new ChatComponentText("Click to see").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN).setChatHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ChatComponentText("Click to see an image"))).setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, String.format("/browseimg %s", imgurId))))).appendText("]");
			}
			catch (Throwable t)
			{
				ShotYourScreen.logger.warn("Unable to parse imgur link in chat!");
				ShotYourScreen.logger.catching(t);
			}
		}
	}
}