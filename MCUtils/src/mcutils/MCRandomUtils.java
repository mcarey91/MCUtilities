package mcutils;

import java.util.Date;
import java.util.Random;

public class MCRandomUtils {
	
	private static Random instance = null;
	
	private static Random getInstance()
	{
		if(instance == null) instance = new Random((new Date()).getTime());
		return instance;
	}
	
	public static boolean nextBoolean()
	{
		return getInstance().nextBoolean();
	}
	
	public static void nextBytes(byte[] bytes)
	{
		getInstance().nextBytes(bytes);
	}
	
	public static double nextDouble()
	{
		return getInstance().nextDouble();
	}
	
	public static float nextFloat()
	{
		return getInstance().nextFloat();
	}
	
	public static double nextGaussian()
	{
		return getInstance().nextGaussian();
	}
	
	public static int nextInt()
	{
		return getInstance().nextInt();
	}
	
	public static int nextInt(int max)
	{
		return getInstance().nextInt(max);
	}
	
	public static int nextInt(int min, int max)
	{
		return min + getInstance().nextInt(max-min);
	}
	
	public static long nextLong()
	{
		return getInstance().nextLong();
	}
	
	public static void setSeed(long seed)
	{
		getInstance().setSeed(seed);
	}
	
	public static String randomJunkString(int maxLength)
	{
		int numChars = nextInt(maxLength);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numChars; i++)
			sb.append((char)nextInt(128));
		return sb.toString();
	}
	
	public static String randomString(int maxLength)
	{
		int numChars = nextInt(maxLength);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numChars; i++)
			sb.append((char)nextInt(32,127));
		return sb.toString();
	}
}
