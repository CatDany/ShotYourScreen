package danylibs;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHelper
{
	public SimpleNetworkWrapper net;
	
	private NetworkHelper() {}
	
	public static NetworkHelper addNetHandler(String channel)
	{
		NetworkHelper helper = new NetworkHelper();
		helper.net = NetworkRegistry.INSTANCE.newSimpleChannel(channel);
		return helper;
	}
	
	public void registerMessage(int index, Class packet, Class message)
	{
		registerSidedMessage(index, packet, message, Side.CLIENT);
		registerSidedMessage(index, packet, message, Side.SERVER);
	}
	
	public void registerSidedMessage(int index, Class packet, Class message, Side side)
	{
		net.registerMessage(packet, message, index, side);
	}
}