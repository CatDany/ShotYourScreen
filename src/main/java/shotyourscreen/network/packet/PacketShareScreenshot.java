package shotyourscreen.network.packet;

import shotyourscreen.ShotYourScreen;
import shotyourscreen.core.ImgurUploader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent.Action;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class PacketShareScreenshot implements IMessageHandler<MessageImgur, IMessage>
{
	@Override
	public IMessage onMessage(MessageImgur message, MessageContext ctx)
	{
		if (ctx.side.isServer())
		{
			if (message.imgurId == null)
			{
				return null;
			}
			IChatComponent chat = new ChatComponentText("* " + ctx.getServerHandler().playerEntity.getCommandSenderName() + " shared a screenshot!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)).appendText(" [").appendSibling(new ChatComponentText("Click to see").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN).setChatHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ChatComponentText("Click to see an image"))).setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, String.format("$BROWSEIMG %s", message.imgurId))))).appendText("]");
			ShotYourScreen.logger.info(ctx.getServerHandler().playerEntity.getCommandSenderName() + " shared a screenshot --> " + ImgurUploader.getImgurUrl(message.imgurId));
			
			for (WorldServer w : MinecraftServer.getServer().worldServers)
			{
				for (Object p : w.playerEntities)
				{
					EntityPlayer player = (EntityPlayer)p;
					player.addChatMessage(chat);
				}
			}
		}
		return null;
	}
}