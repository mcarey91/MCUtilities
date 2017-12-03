package mcutils.gui.table;

import mcutils.gui.style.StyleField;
import mcutils.gui.style.StyleInterface;
import mcutils.gui.style.StyleMetaData;
import java.util.LinkedHashMap;
import java.awt.Color;

public class MCTableStyle implements StyleInterface
{
	public static StyleField<Color> fd_rowHighlightEven = new StyleField<Color>(MCTableStyle.class, Color.class);
	public static StyleField<Color> fd_rowHighlightOdd = new StyleField<Color>(MCTableStyle.class, Color.class);
	
	private static LinkedHashMap<String,StyleField<?>> fieldMap = new LinkedHashMap<>();
	
	static
	{
		fd_rowHighlightEven.setName("rowHighlightEven");
		fd_rowHighlightEven.setDisplayName("Row Highlight Even");
		fd_rowHighlightEven.setDefaultValue(new Color(166,166,255));
		fd_rowHighlightEven.setDescription("This is a thing!");
		addField(fd_rowHighlightEven);
		fd_rowHighlightOdd.setName("rowHighlightOdd");
		fd_rowHighlightOdd.setDisplayName("Row Highlight Odd");
		fd_rowHighlightOdd.setDefaultValue(Color.WHITE);
		fd_rowHighlightOdd.setDescription("This is a different thing!");
		addField(fd_rowHighlightOdd);
	}
	private StyleMetaData metaData = new StyleMetaData(fieldMap);
	
	private Color rowHighlightEven = new Color(166,166,255);
	private Color rowHighlightOdd = Color.WHITE;
	
	public Color getRowHighlightEven(){ return rowHighlightEven; }
	public Color getRowHighlightOdd(){ return rowHighlightOdd; }
	
	public void setRowHighlightEven(Color rowHighlightEven){ this.rowHighlightEven = rowHighlightEven; }
	public void setRowHighlightOdd(Color rowHighlightOdd){ this.rowHighlightOdd = rowHighlightOdd; }
	
	public StyleMetaData getMetaData() { return metaData; }
	
	public static void addField(StyleField<?> f) { fieldMap.put(f.getName(),f); }
}