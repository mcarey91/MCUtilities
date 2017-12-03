package mcutils.gui.table;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MCTableCellRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		String val = "null";
		if(value != null)
			val = value.toString();
		
		if(c instanceof JLabel)
			((JLabel)c).setText(val);
		else if(c instanceof JCheckBox)
			((JCheckBox)c).setText(val);
		
		return c;
	}

}
