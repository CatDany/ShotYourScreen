package danylibs;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.FMLLog;

public class RotationUtils
{
	public static ForgeDirection get2DDirectionFromPosition(Position pos1, Position pos2)
	{
		double dX = pos1.positionX - pos2.positionX;
		double dZ = pos1.positionZ - pos2.positionZ;
		double angle = Math.atan2(dZ, dX) / Math.PI * 180 + 180;
		
		if (angle < 45 || angle > 315) {
			return ForgeDirection.EAST;
		} else if (angle < 135) {
			return ForgeDirection.SOUTH;
		} else if (angle < 225) {
			return ForgeDirection.WEST;
		} else {
			return ForgeDirection.NORTH;
		}
	} 
	
	public static ForgeDirection get3DDirectionFromPosition(Position pos1, Position pos2)
	{
		double dX = pos1.positionX - pos2.positionX;
		double dY = pos1.positionY - pos2.positionY;
		double angle = Math.atan2(dY, dX) / Math.PI * 180 + 180;
		
		if (angle > 45 && angle < 135) {
			return ForgeDirection.UP;
		} else if (angle > 225 && angle < 315) {
			return ForgeDirection.DOWN;
		} else {
			return get2DDirectionFromPosition(pos1, pos2);
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param rot
	 * @return Metadata for sided block or 0 if something goes wrong
	 */
	public static int getMetadataForSidedBlock(int x, int y, int z, Rotation rot)
	{
		int l = MathHelper.floor_double((double)(rot.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		switch (l)
		{
		case 0:
			return 2;
		case 1:
			return 5;
		case 2:
			return 3;
		case 3:
			return 4;
		}
		
		return 0;
	}
	
	/**
	 * 
	 * @param dir ForgeDirection
	 * @return Side ID from ForgeDirection or -1 if direction is UNKNOWN
	 */
	public static int getSideIDFromDirection(ForgeDirection dir)
	{
		switch (dir)
		{
		case DOWN:
			return 0;
		case UP:
			return 1;
		case SOUTH:
			return 2;
		case NORTH:
			return 3;
		case EAST:
			return 4;
		case WEST:
			return 5;
		case UNKNOWN:
			return -1;
		}
		FMLLog.warning("Future/Unsupported ForgeDirection detected! Stacktrace is below:");
		new FutureUnsupportedDirectionException().printStackTrace();
		return -1;
	}
	
	public static Position getPositionFromEntity(Entity entity)
	{
		return new Position(entity.posX, entity.posY, entity.posZ);
	}
	
	public static Rotation getRotationFromEntity(Entity entity)
	{
		return new Rotation(entity.posX, entity.posY, entity.posZ, entity.rotationPitch, entity.rotationYaw);
	}
	
	static class Position
	{
		public double positionX;
		public double positionY;
		public double positionZ;
		
		public Position(double x, double y, double z)
		{
			positionX = x;
			positionY = y;
			positionZ = z;
		}
	}
	
	static class Rotation extends Position
	{
		public float rotationPitch;
		public float rotationYaw;
		
		public Rotation(double x, double y, double z, float pitch, float yaw)
		{
			super(x, y, z);
			rotationPitch = pitch;
			rotationYaw = yaw;
		}
	}
	
	static class FutureUnsupportedDirectionException extends Exception {}
}