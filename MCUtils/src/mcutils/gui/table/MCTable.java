package mcutils.gui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.DefaultTableColumnModelExt;

import mcutils.MCFrameUtils;
import mcutils.MCRandomUtils;

public class MCTable extends JXTable {

	private MCTableSettings settings;
	
	public MCTable(List<MCTableColumn> cols)
	{
		this(cols,new MCTableSettings());
	}
	
	public MCTable(List<MCTableColumn> cols, MCTableSettings settings)
	{
		this.settings = settings;
		initModel(cols);
		initRenderers();
		initEditors();
	}
	
	private void initModel(List<MCTableColumn> cols)
	{
		setModel(new MCTableModel());
		setColumnModel(new DefaultTableColumnModelExt());
		for(int i = 0; i < cols.size(); i++)
		{
			cols.get(i).setModelIndex(i);
			getColumnModel().addColumn(cols.get(i));
		}
		
	}

	private void initRenderers()
	{
		
	}
	
	private void initEditors()
	{
		setDefaultEditor(Integer.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(int.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(Double.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(double.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(Float.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(float.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(Long.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(long.class, new MCTableCellEditor(new JTextField()));
		setDefaultEditor(String.class, new MCTableCellEditor(new JTextField()));
		
		if(getSettings().isRenderingBooleansAsCheckboxes())
		{
			setDefaultEditor(Boolean.class, new MCTableCellEditor(new JCheckBox()));
			setDefaultEditor(boolean.class, new MCTableCellEditor(new JCheckBox()));
		}
		else
		{
			setDefaultEditor(Boolean.class, new MCTableCellEditor(new JComboBox()));
			setDefaultEditor(boolean.class, new MCTableCellEditor(new JComboBox()));
		}
	}
	
	public void addRow(MCTableRow row)
	{
		MCTableModel model = (MCTableModel)getModel();
		model.addRow(row);
	}

	
	public MCTableRow removeRow(int i)
	{
		MCTableModel model = (MCTableModel)getModel();
		return model.removeRow(this.convertRowIndexToModel(i));
	}
	
	
	
	public MCTableSettings getSettings() {
		if(settings == null)
			settings = new MCTableSettings();
		return settings;
	}

	public void setSettings(MCTableSettings settings) {
		this.settings = settings;
	}

	public static void main(String[] args) {
		
		MCFrameUtils.setLookAndFeel();
		
		List<MCTableColumn> cols = new ArrayList<>();
		cols.add(new MCTableColumn("String",String.class));
		cols.add(new MCTableColumn("Integer",Integer.class));
		cols.add(new MCTableColumn("int",int.class));
		cols.add(new MCTableColumn("Double",Double.class));
		cols.add(new MCTableColumn("double",double.class));
		cols.add(new MCTableColumn("Float",Float.class));
		cols.add(new MCTableColumn("float",float.class));
		cols.add(new MCTableColumn("Long",Long.class));
		cols.add(new MCTableColumn("long",long.class));
		
		MCTable table = new MCTable(cols);
		
		for(int i = 0; i < 25; i++)
		{
			MCTableRow row = new MCTableRow();
			row.add(MCRandomUtils.randomString(20));
			row.add(new Integer(MCRandomUtils.nextInt()));
			row.add(MCRandomUtils.nextInt());
			row.add(new Double(MCRandomUtils.nextDouble()));
			row.add(MCRandomUtils.nextDouble());
			row.add(new Float(MCRandomUtils.nextFloat()));
			row.add(MCRandomUtils.nextFloat());
			row.add(new Long(MCRandomUtils.nextLong()));
			row.add(MCRandomUtils.nextLong());
			table.addRow(row);
		}
		
		MCFrameUtils.newFrame(new JScrollPane(table), 500, 500);
		
	}

}
