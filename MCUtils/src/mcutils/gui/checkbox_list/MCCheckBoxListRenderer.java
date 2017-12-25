package mcutils.gui.checkbox_list;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MCCheckBoxListRenderer implements ListCellRenderer<MCCheckBoxListItem<?>>
{

	@Override
	public Component getListCellRendererComponent(JList<? extends MCCheckBoxListItem<?>> list,
			MCCheckBoxListItem<?> value, int index, boolean isSelected, boolean cellHasFocus) {
		return value.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}

}
