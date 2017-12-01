package mcutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MCPropertyNexus {
	
	public static Properties load(String name) throws IOException
	{
		String filename = System.getProperty("java.io.tmpdir") + name;
		File f = new File(filename);
		if(f.exists())
		{
			Properties p = new Properties();
			p.load(new FileInputStream(f));
			return p;
		}
		return null;
	}
	
	public static void save(String name, Properties p) throws IOException
	{
		String filename = System.getProperty("java.io.tmpdir") + name;
		File f = new File(filename);
		p.store(new FileOutputStream(f),null);
	}
}
