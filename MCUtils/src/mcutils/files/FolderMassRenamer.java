package mcutils.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

import mcutils.MCFileChooser;
import mcutils.MCFrameUtils;

public class FolderMassRenamer {
	public static void main(String[] args)
	{
		MCFrameUtils.setLookAndFeel();
		MCFileChooser fc = new MCFileChooser("Folder Renamer");
		fc.setFileSelectionMode(MCFileChooser.DIRECTORIES_ONLY);
		if(fc.showOpenDialog(null) == MCFileChooser.APPROVE_OPTION)
		{
			File parent = fc.getSelectedFile();
			for(File f : parent.listFiles())
			{
				if(f.isDirectory())
				{
//					String name = f.getName().replace("_FLAC", "").replace("_", " ");
					
					int index = f.getName().indexOf('-');
					if(index == -1)
						continue;

					String name = f.getName().substring(index+1).trim();
					String newParent = f.getName().substring(0,index).trim();
					
					File newFile = new File(parent,newParent);
					File newNewFile = new File(newFile,name);
					try {
						Files.move(f.toPath(),newNewFile.toPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
	}
}
