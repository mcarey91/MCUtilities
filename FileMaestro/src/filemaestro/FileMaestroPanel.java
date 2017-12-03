package filemaestro;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import mcutils.gui.table.MCTable;
import mcutils.gui.table.MCTableColumn;
import mcutils.gui.table.MCTableRow;
import mcutils.gui.table.MCTableSettings;

public class FileMaestroPanel extends JPanel
{
	
	private MCTable table;
	
	public FileMaestroPanel(List<File> allFiles)
	{
		setLayout(new BorderLayout());
		MCTableSettings s = new MCTableSettings();
		
		List<MCTableColumn> cols = new ArrayList<>();
		FieldKey[] keys = new FieldKey[] {FieldKey.ARTIST,FieldKey.TITLE,FieldKey.ALBUM,FieldKey.GENRE,FieldKey.YEAR};
		for(FieldKey k : keys)
			cols.add(buildColumn(k));
		table = new MCTable(cols,s);
		
		List<MCTableRow> rows = new ArrayList<>();
		
		for(File f : allFiles)
		{
			MCTableRow row = new MCTableRow(f);
			try {
				AudioFile af = AudioFileIO.read(f);
				Tag tag = af.getTag();
				for(FieldKey k : keys)
				{
					row.add(tag.getFirst(k));
				}
				rows.add(row);
			} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
					| InvalidAudioFrameException e) {
				System.err.println("Failed to read " + f.getPath());
				e.printStackTrace();
			}
			
		}
		
		if(!rows.isEmpty())
			table.addRows(rows);
		
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
	
	private static MCTableColumn buildColumn(FieldKey fk)
	{
		return new MCTableColumn(fk.toString(), String.class);
	}
}
