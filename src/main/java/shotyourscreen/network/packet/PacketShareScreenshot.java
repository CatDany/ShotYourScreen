package shotyourscreen.network.packet;

import io.netty.buffer.ByteBuf;
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
import shotyourscreen.ShotYourScreen;
import shotyourscreen.core.ImgurUploader;
import shotyourscreen.network.packet.PacketShareScreenshot.MessageShareScreenshot;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketShareScreenshot implements IMessageHandler<MessageShareScreenshot, IMessage>
{
	@Override
	public IMessage onMessage(MessageShareScreenshot message, MessageContext ctx)
	{
		if (ctx.side.isServer())
		{
			if (message.imgurId == null)
			{
				return null;
			}
			IChatComponent chat = new ChatComponentText("* " + ctx.getServerHandler().playerEntity.getCommandSenderName() + " shared a screenshot!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD)).appendText(" [").appendSibling(new ChatComponentText("Click to see").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN).setChatHoverEvent(new HoverEvent(Action.SHOW_TEXT, new ChatComponentText("Click to see an image"))).setChatClickEvent(new ClickEvent(net.minecraft.event.ClickEvent.Action.RUN_COMMAND, String.format("/browseimg %s", message.imgurId))))).appendText("]");
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
	
	public static class MessageShareScreenshot implements IMessage
	{
		String imgurId;
		
		public MessageShareScreenshot() {}
		
		public MessageShareScreenshot(String imgurId)
		{
			this.imgurId = imgurId;
		}
		
		@Override
		public void fromBytes(ByteBuf buf)
		{
			int imgurIdLength = buf.readInt();
			this.imgurId = new String(buf.readBytes(imgurIdLength).array());
		}
		
		@Override
		public void toBytes(ByteBuf buf)
		{
			buf.writeInt(imgurId.getBytes().length);
			buf.writeBytes(imgurId.getBytes());
		}
	}
}