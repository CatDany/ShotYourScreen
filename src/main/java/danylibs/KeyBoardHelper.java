package danylibs;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class KeyBoardHelper
{
	public static boolean isShiftDown()
	{
		return Minecraft.getMinecraft().currentScreen.isShiftKeyDown();
	}
	
	public static boolean isCtrlDown()
	{
		return Minecraft.getMinecraft().currentScreen.isCtrlKeyDown();
	}
}