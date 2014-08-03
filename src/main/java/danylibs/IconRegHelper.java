package danylibs;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import shotyourscreen.Refs;

public class IconRegHelper
{
	public static IIcon regItem(Item item, IIconRegister reg)
	{
		return regItem(item, reg, "");
	}
	
	public static IIcon regBlock(Block block, IIconRegister reg)
	{
		return regBlock(block, reg, "");
	}
	
	public static IIcon regItem(Item item, IIconRegister reg, String suffix)
	{
		return reg.registerIcon(Refs.RESLOC + ":" + item.getUnlocalizedName().substring(5) + suffix);
	}
	
	public static IIcon regBlock(Block block, IIconRegister reg, String suffix)
	{
		return reg.registerIcon(Refs.RESLOC + ":" + block.getUnlocalizedName().substring(5) + suffix);
	}
}