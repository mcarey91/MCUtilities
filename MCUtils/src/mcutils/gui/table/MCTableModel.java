package mcutils.gui.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class MCTableModel implements TableModel{

	List<TableModelListener> tml = new ArrayList<>();
	List<MCTableColumn> cols = new ArrayList<>();
	List<MCTableRow> rows = new ArrayList<>();
	
	public MCTableModel(Collection<MCTableColumn> cols)
	{
		this.cols.addAll(cols);
	}
	
	public MCTableModel(MCTableColumn... cols)
	{
		for(MCTableColumn c : cols)
			this.cols.add(c);
	}
	
	
	@Override
	public void addTableModelListener(TableModelListener l)
	{
		tml.add(l);
	}

	@Override
	public Class<?> getColumnClass(int i)
	{
		return cols.get(i).getClazz();
	}

	@Override
	public int getColumnCount()
	{
		return cols.size();
	}

	@Override
	public String getColumnName(int i)
	{
		return cols.get(i).getTitle();
	}

	@Override
	public int getRowCount()
	{
		return rows.size();
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		return rows.get(row).get(col);
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return cols.get(col).isEditable();
	}

	@Override
	public void removeTableModelListener(TableModelListener l)
	{
		tml.remove(l);
	}

	@Override
	public void setValueAt(Object o, int row, int col)
	{
		rows.get(row).set(col, o);
	}
	
	public void addRows(MCTableRow[] rows)
	{
		addRows(rows,this.rows.size());
	}
	
	public void addRows(MCTableRow[] rows, int index)
	{
		
		this.rows.addAll(index, Arrays.asList(rows));
		fireTableRowsInserted(index,index+rows.length-1);
	}

	public MCTableRow removeRow(int i)
	{
		MCTableRow row = rows.remove(i);
		fireTableRowsRemoved(i,i);
		return row;
	}
	
	public void fireTableRowsInserted(int firstRow, int lastRow)
	{
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,TableModelEvent.INSERT));
	}
	
	public void fireTableRowsRemoved(int firstRow, int lastRow)
	{
		fireTableChanged(new TableModelEvent(this, firstRow, lastRow, TableModelEvent.ALL_COLUMNS,TableModelEvent.DELETE));
	}
	
	public void fireTableChanged(TableModelEvent e)
	{
		for(TableModelListener l : tml)
			l.tableChanged(e);
	}
}
