package shotyourscreen.client;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import shotyourscreen.Refs;
import shotyourscreen.ShotYourScreen;
import shotyourscreen.core.ImgurUploader;
import shotyourscreen.network.PacketHandler;
import shotyourscreen.network.packet.MessageImgur;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class KeyHandler
{
	public final KeyBinding keyShot = new KeyBinding("Take a Screenshot", Keyboard.KEY_NUMPAD0, Refs.MOD_NAME);
	
	public void initKeybindings()
	{
		ClientRegistry.registerKeyBinding(keyShot);
		FMLCommonHandler.instance().bus().register(this);
		ShotYourScreen.logger.info("Key Bindings initialized.");
	}
	
	@SubscribeEvent
	public void clientTick(TickEvent.ClientTickEvent e)
	{
		if (e.phase == Phase.START)
		{
			if (keyShot.isPressed())
			{
				String imgurId = ImgurUploader.takeScreenshotAndUpload();
				IMessage msg = new MessageImgur(imgurId);
				PacketHandler.instance().net().sendToServer(msg);
			}
		}
	}
}