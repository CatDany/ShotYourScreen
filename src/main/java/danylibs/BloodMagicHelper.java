package danylibs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BloodMagicHelper
{
	public static int getOrbTier(EntityPlayer player)
	{
		for (int i = 0; i < player.inventory.getSizeInventory(); i++)
		{
			ItemStack stack = player.inventory.getStackInSlot(i);
			if (stack == null)
				continue;
			String name = ItemUtils.getFullUniqueItemName(stack.getItem());
			if ("AWWayofTime:archmageBloodOrb".equals(name))
				return 5;
			else if ("AWWayofTime:masterBloodOrb".equals(name))
				return 4;
			else if ("AWWayofTime:magicianBloodOrb".equals(name))
				return 3;
			else if ("AWWayofTime:apprenticeBloodOrb".equals(name))
				return 2;
			else if ("AWWayofTime:weakBloodOrb".equals(name))
				return 1;
		}
		return 0;
	}
	
	public static int getLimit(EntityPlayer player)
	{
		return getLimitByOrbTier(getOrbTier(player));
	}
	
	public static int getLimitByOrbTier(int tier)
	{
		switch (tier)
		{
		case 5:
			return 10000000; // 10kk
		case 4:
			return 1000000;  // 1kk
		case 3:
			return 150000;   // 150k
		case 2:
			return 25000;    // 25k
		case  1:
			return 5000;     // 5k
		}
		return 0;
	}
}