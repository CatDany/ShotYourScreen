package shotyourscreen.command;

import java.io.File;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import shotyourscreen.awt.SYSFrame;
import shotyourscreen.core.ImgurUploader;
import danylibs.InternetHelper;

// Required to not cause "Unknown command" output on server
public class CommandBrowseImgServer extends CommandBrowseImg
{
	@Override
	public void processCommand(ICommandSender sender, String[] args) {}
}