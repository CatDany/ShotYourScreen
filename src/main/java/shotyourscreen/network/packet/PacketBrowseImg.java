package shotyourscreen.network.packet;

import java.io.File;

import shotyourscreen.awt.SYSFrame;
import shotyourscreen.core.ImgurUploader;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import danylibs.InternetHelper;

public class PacketBrowseImg implements IMessageHandler<MessageImgurClient, IMessage>
{
	@Override
	public IMessage onMessage(MessageImgurClient message, MessageContext ctx)
	{
		if (ctx.side.isClient())
		{
			File image = new File(String.format("downloaded-screenshots\\imgur_%s.png", message.imgurId));
			
			if (!image.exists())
			{
				InternetHelper.downloadRemoteFile(image.getAbsolutePath(), ImgurUploader.getImgurUrl(message.imgurId));
			}
			new SYSFrame(image);
		}
		return null;
	}
}