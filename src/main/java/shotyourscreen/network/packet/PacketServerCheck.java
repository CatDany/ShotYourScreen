package shotyourscreen.network.packet;

import io.netty.buffer.ByteBuf;
import shotyourscreen.Refs;
import shotyourscreen.ShotYourScreen;
import shotyourscreen.client.ServerSupport;
import shotyourscreen.network.packet.PacketServerCheck.MessageServerCheck;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class PacketServerCheck implements IMessageHandler<MessageServerCheck, IMessage>
{
	@Override
	public IMessage onMessage(MessageServerCheck message, MessageContext ctx)
	{
		if (ctx.side.isClient())
		{
			ServerSupport.SERVER_SUPPORT = true;
			ShotYourScreen.logger.info(String.format("Current server supports %s.", Refs.MOD_ID));
		}
		return null;
	}
	
	public static class MessageServerCheck implements IMessage
	{
		@Override
		public void fromBytes(ByteBuf buf) {}
		
		@Override
		public void toBytes(ByteBuf buf) {}
	}
}