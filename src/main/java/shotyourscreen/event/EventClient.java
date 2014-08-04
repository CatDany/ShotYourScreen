package shotyourscreen.event;

import shotyourscreen.client.ServerSupport;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class EventClient
{
	@SubscribeEvent
	public void disconnectedFromServer(ClientDisconnectionFromServerEvent e)
	{
		ServerSupport.SERVER_SUPPORT = false;
	}
}