package filemaestro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.apache.commons.io.FilenameUtils;

import mcutils.MCFileChooser;
import mcutils.MCFrameUtils;

public class FileMaestro
{
	
	public static Set<String> VALID_EXTENSIONS = new HashSet<>();
	static
	{
		VALID_EXTENSIONS.add("wav");
		VALID_EXTENSIONS.add("mp3");
		VALID_EXTENSIONS.add("ogg");
	}
	
	public static void main(String[] args)
	{
		MCFrameUtils.setLookAndFeel();
		
		MCFileChooser fc = new MCFileChooser("test_file_maestro");
		fc.setFileSelectionMode(MCFileChooser.FILES_AND_DIRECTORIES);
		if(fc.showOpenDialog(null) == MCFileChooser.APPROVE_OPTION)
		{
			
			File f = fc.getSelectedFile();
			List<File> allFiles = new ArrayList<>();
			if(f.isDirectory())
			{
				try {
					Files.walk(f.toPath())
						.filter(file -> VALID_EXTENSIONS.contains(FilenameUtils.getExtension(file.toFile().getName())))
						.forEach(file -> allFiles.add(file.toFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				if(VALID_EXTENSIONS.contains(FilenameUtils.getExtension(f.getName())))
				{
					allFiles.add(f);
				}
			}
			
			if(allFiles.isEmpty())
			{
				System.err.println("No valid files found!");
				return;
			}
			
			FileMaestroPanel p = new FileMaestroPanel(allFiles);
			MCFrameUtils.newFrame(p,500,500);
			
		}
	}
}
