package shotyourscreen.network.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageImgurClient implements IMessage
{
	String imgurId;
	
	public MessageImgurClient() {}
	
	public MessageImgurClient(String imgurId)
	{
		this.imgurId = imgurId;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		int imgurIdLength = buf.readByte();
		this.imgurId = new String(buf.readBytes(imgurIdLength).array());
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeByte(imgurId.getBytes().length);
		buf.writeBytes(imgurId.getBytes());
	}
}