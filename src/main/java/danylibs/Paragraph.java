package danylibs;

public class Paragraph
{
	/** \u00a7 **/
	public static final String sign = "\u00a7";
	
	/** \00a7 + 'letterColor' **/
	public static String sign(String letterColor)
	{
		return sign + letterColor;
	}
	
	/** \00a7 + 'letterColor' **/
	public static String sign(char letterColor)
	{
		return sign + letterColor;
	}
	
	/** \00a7 + 'letterColor' **/
	public static String sign(int letterColor)
	{
		return sign + letterColor;
	}
	
	public static final String black = sign + 0;
	public static final String blue = sign + 1;
	public static final String green = sign + 2;
	public static final String cyan = sign + 3;
	public static final String red = sign + 4;
	public static final String dark_purple = sign + 5;
	public static final String gold = sign + 6;
	public static final String light_gray = sign + 7;
	public static final String gray = sign + 8;
	public static final String lapis = sign + 9;
	public static final String light_green = sign + 'a';
	public static final String light_blue = sign + 'b';
	public static final String rose = sign + 'c';
	public static final String purple = sign + 'd';
	public static final String yellow = sign + 'e';
	public static final String white = sign + 'f';
	
    public static final String bold = sign + 'l';
    public static final String italic = sign + 'o';
    public static final String strike = sign + 'm';
    public static final String magic = sign + 'k';
    public static final String underline = sign + 'b';
    public static final String reset = sign + 'r';
}