package mcutils.gui.table;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import mcutils.gui.MCGUIProperties;

public class MCTableStyle extends MCGUIProperties{

	protected enum STYLE_ITEM
	{
		ROW_COLOR_A("ROW_COLOR_A",Color.WHITE),
		ROW_COLOR_B("ROW_COLOR_B",Color.BLUE.brighter().brighter().brighter()),
		DISABLED_COLOR_A("DISABLED_COLOR_A",Color.LIGHT_GRAY),
		DISABLED_COLOR_B("DISABLED_COLOR_B",Color.DARK_GRAY)
		;
		
		String name;
		Object value;
		
		STYLE_ITEM(String name, Object value)
		{
			this.name = name;
			this.value = value;
		}
		
		public String getName(){ return name; }
		public Object getValue(){ return value; }
		public void setValue(Object value) { this.value = value; } 
		
	}
	
	public MCTableStyle()
	{
		super(MCTable.class);
	}

	@Override
	public Map<String, Object> getDefaultProperties() {
		Map<String, Object> props = new HashMap<>();
		for (STYLE_ITEM i : STYLE_ITEM.values()) {
			props.put(i.getName(), i.getValue());
		}
		return props;
	}
}
