package shotyourscreen.network;

import shotyourscreen.Refs;
import shotyourscreen.network.packet.MessageImgur;
import shotyourscreen.network.packet.MessageImgurClient;
import shotyourscreen.network.packet.PacketBrowseImg;
import shotyourscreen.network.packet.PacketShareScreenshot;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import danylibs.NetworkHelper;

public class PacketHandler
{
	private static final PacketHandler instance = new PacketHandler();
	private NetworkHelper helper;
	
	public void init()
	{
		helper = NetworkHelper.addNetHandler(Refs.MOD_ID.toUpperCase());
		
		helper.registerMessage(0, PacketBrowseImg.class, MessageImgurClient.class);
		helper.registerMessage(1, PacketShareScreenshot.class, MessageImgur.class);
	}
	
	public SimpleNetworkWrapper net()
	{
		return helper.net;
	}
	
	public static PacketHandler instance()
	{
		return instance;
	}
}