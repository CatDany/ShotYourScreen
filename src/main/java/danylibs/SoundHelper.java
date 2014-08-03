package danylibs;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import shotyourscreen.Refs;

public class SoundHelper
{
	public static float pitch = 1.0F;
	
	public static void playSoundBlock(World world, int x, int y, int z, String sound, float volume)
	{
		world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, sound, volume, pitch);
		pitch = 1.0F;
	}
	
	public static void playSoundEntity(Entity entity, String sound, float volume)
	{
		entity.worldObj.playSoundEffect(entity.posX, entity.posY + (entity.height / 2), entity.posZ, sound, volume, pitch);
		pitch = 1.0F;
	}
	
	public static void playModSoundBlock(World world, int x, int y, int z, String sound, float volume)
	{
		playSoundBlock(world, x, y, z, Refs.RESLOC + ":" + sound, volume);
	}
	
	public static void playModSoundEntity(Entity entity, String sound, float volume)
	{
		playSoundEntity(entity, Refs.RESLOC + ":" + sound, volume);
	}
	
	public static void setPitch(float p)
	{
		pitch = p;
	}
}