package danylibs;

import net.minecraft.nbt.NBTTagCompound;

public class MathUtils
{
	public static double getDistance(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		return Math.sqrt(
				Math.pow(x2 - x1, 2)
				+
				Math.pow(y2 - y1, 2)
				+
				Math.pow(z2 - z1, 2)
				);
	}
}