package shotyourscreen.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import shotyourscreen.ShotYourScreen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.istack.internal.Nullable;

public class ImgurUploader
{
	public static final String API_URL = "https://api.imgur.com/3/image.json";
	public static final String API_KEY = "a82b9bc0d7928fe";
	public static final String IMG_URL = "http://i.imgur.com/%s.png";
	
	private static final int BUFFER_SIZE = 1024;
	
	public static String getImgurUrl(String id)
	{
		return String.format(IMG_URL, id);
	}
	
	/**
	 * 
	 * @return
	 * <b>Imgur ID</b> of uploaded image<br>
	 * <b>null</b> if upload fails
	 */
	public static String takeScreenshotAndUpload()
	{
		String imgPath = takeScreenshot();
		String imgurId = uploadImage(imgPath);
		return imgurId;
	}
	
	private static String takeScreenshot()
	{
		Minecraft mc = Minecraft.getMinecraft();
		return ScreenShotHelper.saveScreenshot(mc.mcDataDir, mc.displayWidth, mc.displayHeight, mc.getFramebuffer());
	}
	
	private static String uploadImage(String imgPath)
	{
		try
		{
			File img = new File(imgPath);
			HttpURLConnection con = (HttpURLConnection)new URL(API_URL).openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Authorization", "Client-ID " + API_KEY);
			
			OutputStream out = con.getOutputStream();
			FileInputStream fis = new FileInputStream(img);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len;
			while ((len = fis.read(buffer)) != -1)
			{
				out.write(buffer, 0, len);
			}
			fis.close();
			out.flush();
			out.close();
			
			String result = null;
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK)
			{
				result = handleResponse(con.getInputStream());
			}
			else
			{
				ShotYourScreen.logger.warn("Unable to connect to Imgur!");
			}
			con.disconnect();
			return result;
		}
		catch (Throwable t)
		{
			ShotYourScreen.logger.warn("Unable to upload an image to Imgur!");
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Unable to upload an image to Imgur!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
			ShotYourScreen.logger.catching(t);
			return null;
		}
	}
	
	@Nullable
	private static String handleResponse(InputStream in)
	{
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(in);
		while (scanner.hasNext())
		{
			sb.append(scanner.next());
		}
		scanner.close();
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(sb.toString());
		if (element.isJsonObject())
		{
			JsonObject root = element.getAsJsonObject();
			JsonObject data = root.getAsJsonObject("data");
			String imgurId = data.get("id").getAsString();
			
			ShotYourScreen.logger.info(String.format("Uploaded image to Imgur with id %s", imgurId));
			return imgurId;
		}
		return null;
	}
}