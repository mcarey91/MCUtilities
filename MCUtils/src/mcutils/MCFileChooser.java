package mcutils;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFileChooser;

public class MCFileChooser extends JFileChooser{

	private static final long serialVersionUID = 3748923528795155803L;
	
	String myName;
	
	public MCFileChooser(String name)
	{
		super();
		myName = name;
		try {
			load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int showOpenDialog(Component file)
	{
		int value = super.showOpenDialog(file);
		if(value == MCFileChooser.APPROVE_OPTION)
		{
			try {
				save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}
	
	@Override
	public int showSaveDialog(Component file)
	{
		int value = super.showSaveDialog(file);
		if(value == MCFileChooser.APPROVE_OPTION)
		{
			try {
				save();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}
	
	private void load() throws IOException
	{
		Properties p = MCPropertyNexus.load(myName);
		if(p == null ||! p.containsKey("path"))
			return;
		String path = p.getProperty("path");
		super.setSelectedFile(new File(path));
	}
	
	private void save() throws IOException
	{
		Properties p = new Properties();
		p.setProperty("path", getSelectedFile().getPath());
		MCPropertyNexus.save(myName,p);
	}
}
