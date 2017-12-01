package mcutils.gui;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import mcutils.gui.table.MCTable;
import mcutils.gui.table.MCTableStyle;

public class MCStyle {
	public static Map<Class<? extends Component>, Class<? extends MCGUIProperties>> DEFAULT_STYLES = new HashMap<>();
	static
	{
		DEFAULT_STYLES.put(MCTable.class,MCTableStyle.class);
	}

}
