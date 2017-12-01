package mcutils;

public class MCStringUtils {
	
	private static boolean charCompare(char c, char... carr)
	{
		for(char ca : carr)
		{
			if(ca == c)
				return true;
		}
		return false;
	}
	
	public static String removePaddingCharFromTheEndOfString(String str,char... c) {
	    while (str != null && str.length() > 0 && charCompare(str.charAt(str.length()-1),c)) {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}
	
	public static String removePaddingCharFromTheBeginningOfString(String str,char... c) {
	    while (str != null && str.length() > 0 && charCompare(str.charAt(0),c)) {
	      str = str.substring(1, str.length());
	    }
	    return str;
	}
	
	public static String removePaddingCharFromBothSidesOfString(String str, char... c)
	{
		return removePaddingCharFromTheEndOfString(removePaddingCharFromTheBeginningOfString(str,c),c);
	}
	
	public static String capitalize(final String line)
	{
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
	
}
