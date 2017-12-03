package mcutils.gui.table;

import java.util.Collection;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.DefaultTableColumnModelExt;

public class MCTable extends JXTable {

	private MCTableSettings settings;
	
	public MCTable(List<MCTableColumn> cols)
	{
		this(cols,new MCTableSettings());
	}
	
	public MCTable(List<MCTableColumn> cols, MCTableSettings settings)
	{
		super();
		this.settings = settings;
		initModel(cols);
		initRenderers();
		initEditors();
		setHighlighters(settings.getHighLighter());
		
		setRowSelectionAllowed(false);
		setCellSelectionEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
	
	private void initModel(List<MCTableColumn> cols)
	{
		setModel(new MCTableModel(cols));
		DefaultTableColumnModelExt colModel = new DefaultTableColumnModelExt();
		setColumnModel(colModel);
		for(int i = 0; i < cols.size(); i++)
		{
			cols.get(i).setModelIndex(i);
			colModel.addColumn(cols.get(i));
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
		
		setDefaultEditor(Enum.class, new MCTableCellEditor(new JComboBox()));
	}
	
	public MCTableModel getTableModel()
	{
		return (MCTableModel)getModel();
	}
	
	public void addRow(MCTableRow row)
	{
		addRows(row);
	}

	public void addRows(Collection<MCTableRow> rows)
	{
		addRows(rows.toArray(new MCTableRow[rows.size()]));
	}
	
	public void addRows(MCTableRow... rows)
	{
		getTableModel().addRows(rows);
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

}
