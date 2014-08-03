package danylibs;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class PermissionHelper
{
	public static CommandPermissionLevel getPermissionLevel(ICommandSender sender)
	{
		if (sender instanceof MinecraftServer)
		{
			return CommandPermissionLevel.CONSOLE;
		}
		else if (sender instanceof EntityPlayer)
		{
			boolean isOP = MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(sender.getCommandSenderName());
			int level = isOP ? MinecraftServer.getServer().getOpPermissionLevel() : 0;
			return getEnumFromPermissionLevel(level);
		}
		else if (sender instanceof CommandBlockLogic)
		{
			return CommandPermissionLevel.COMMAND_BLOCK;
		}
		else
		{
			return CommandPermissionLevel.USER;
		}
	}
	
	public static CommandPermissionLevel getEnumFromPermissionLevel(int permissionLevel)
	{
		switch (permissionLevel)
		{
		case 0:
			return CommandPermissionLevel.USER;
		case 1:
			return CommandPermissionLevel.USER;
		case 2:
			return CommandPermissionLevel.COMMAND_BLOCK;
		case 3:
			return CommandPermissionLevel.OPERATOR;
		case 4:
			return CommandPermissionLevel.CONSOLE;
		}
		return CommandPermissionLevel.USER;
	}
	
	static enum CommandPermissionLevel
	{
		USER(0),
		COMMAND_BLOCK(2),
		OPERATOR(3),
		CONSOLE(4);
		
		private final int permLevel;
		
		private CommandPermissionLevel(int permissionLevel)
		{
			this.permLevel = permissionLevel;
		}
		
		public int value()
		{
			return permLevel;
		}
	}
}