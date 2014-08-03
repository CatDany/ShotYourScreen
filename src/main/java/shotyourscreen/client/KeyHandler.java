package shotyourscreen.client;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;

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
	public final KeyBinding keyShot = new KeyBinding("Share a Screenshot", Keyboard.KEY_NUMPAD0, Refs.MOD_NAME);
	public final KeyBinding keyCopy = new KeyBinding("Take Screenshot and Copy Link", Keyboard.KEY_NUMPAD1, Refs.MOD_NAME);
	
	public void initKeybindings()
	{
		ClientRegistry.registerKeyBinding(keyShot);
		ClientRegistry.registerKeyBinding(keyCopy);
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
			if (keyCopy.isPressed())
			{
				String imgurId = ImgurUploader.takeScreenshotAndUpload();
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(ImgurUploader.getImgurUrl(imgurId)), null);
				Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Screenshot uploaded and link copied to your clipboard."));
			}
		}
	}
}