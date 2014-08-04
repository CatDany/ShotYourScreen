package shotyourscreen.command;

import java.io.File;

import shotyourscreen.awt.SYSFrame;
import shotyourscreen.core.ImgurUploader;
import danylibs.InternetHelper;
import danylibs.Paragraph;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

public class CommandBrowseImg extends CommandBase
{
	public CommandBrowseImg()
	{
		super();
	}
	
	@Override
	public String getCommandName()
	{
		return "browseimg";
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/browseimg [imgurId]";
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if (args.length != 1)
		{
			sender.addChatMessage(new ChatComponentText("Usage: " + getCommandUsage(sender)).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
			return;
		}
		String imgurId = args[0];
		
		File image = new File(String.format("downloaded-screenshots\\imgur_%s.png", imgurId));
		if (!image.exists())
		{
			InternetHelper.downloadRemoteFile(image.getAbsolutePath(), ImgurUploader.getImgurUrl(imgurId));
		}		
		new SYSFrame(image);
	}
	
	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
}