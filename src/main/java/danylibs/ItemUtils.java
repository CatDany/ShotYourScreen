package danylibs;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;

public class ItemUtils
{
	public static String getUniqueItemName(Object item)
	{
		if (item instanceof Block)
			return GameRegistry.findUniqueIdentifierFor((Block)item).name;
		else if (item instanceof Item)
			return GameRegistry.findUniqueIdentifierFor((Item)item).name;
		else
			return null;
	}
	
	public static String getUniqueModName(Object item)
	{
		if (item instanceof Block)
			return GameRegistry.findUniqueIdentifierFor((Block)item).modId;
		else if (item instanceof Item)
			return GameRegistry.findUniqueIdentifierFor((Item)item).modId;
		else
			return null;
	}
	
	/**
	 * return unique item/block name in format 'modid:name'
	 * @param item net.minecraft.item.Item or net.minecraft.block.Block instance
	 * @return String
	 */
	public static String getFullUniqueItemName(Object item)
	{
		UniqueIdentifier uid;
		String modid;
		String name;
		
		if (item instanceof Block)
			uid = GameRegistry.findUniqueIdentifierFor((Block)item);
		else if (item instanceof Item)
			uid = GameRegistry.findUniqueIdentifierFor((Item)item);
		else
			return null;
		
		modid = uid.modId;
		name = uid.name;
		
		return modid + ":" + name;
	}
	
	/**
	 * Compare unique item names
	 * @param item
	 * @param comparable
	 * @return
	 */
	public static boolean compare(Object item, Object comparable)
	{
		if (item == null || comparable == null)
			return false;
		
		String first = getFullUniqueItemName(item);
		String second = getFullUniqueItemName(comparable);
		return first.equals(second);
	}
	
	/**
	 * Compare unique item names NOT ITEM STACKS
	 * @param stack
	 * @param comparable
	 * @return
	 */
	public static boolean compare(ItemStack stack, ItemStack comparable)
	{
		return compare(stack.getItem(), comparable.getItem());
	}
}