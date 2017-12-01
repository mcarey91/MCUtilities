package mcutils.gui.table;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MCTableCellRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if(value instanceof Boolean || (value != null && value.getClass() == boolean.class))
		{
			if(value == null)
				return new JLabel("Not Set");
			boolean checkbox = false;
			if(table instanceof MCTable)
				checkbox = ((MCTable)table).getSettings().isRenderingBooleansAsCheckboxes();
			
			
			
		}
		
		String val = "null";
		if(value != null)
			val = value.toString();
		
		return new JLabel(val);
	}

}
