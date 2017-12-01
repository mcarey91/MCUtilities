package mcutils.gui.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class MCTableCellEditor extends DefaultCellEditor {

	public MCTableCellEditor(JCheckBox jcb) {
		super(jcb);
	}
	
	public MCTableCellEditor(JComboBox<?> jcb)
	{
		super(jcb);

	}
	public MCTableCellEditor(JTextField jtf)
	{
		super(jtf);
	}

}
