package danylibs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityHelper
{
	public static ArrayList<Entity> getEntitiesInRange(World world, double x, double y, double z, int radius)
	{
		List list = world.getEntitiesWithinAABB(
				Entity.class,
				AxisAlignedBB.getBoundingBox(
						x-0.5f,
						y-0.5f,
						z-0.5f,
						x + 0.5f,
						y + 0.5f,
						z + 0.5f
						).expand(
						radius, radius, radius
						));
		ArrayList<Entity> listEntities = new ArrayList<Entity>();
		listEntities.addAll(list);
		return listEntities;
	}
}