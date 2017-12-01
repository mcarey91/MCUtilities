package mcutils.gui.table;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;

import org.jdesktop.swingx.table.TableColumnExt;
import org.jdesktop.swingx.table.TableColumnModelExt;

public class MCTableColumnModel implements TableColumnModelExt{

	List<TableColumn> cols = new ArrayList<>();
	
	@Override
	public void addColumn(TableColumn aColumn) {
		cols.add(aColumn);
	}

	@Override
	public TableColumn getColumn(int columnIndex) {
		return cols.get(columnIndex);
	}

	@Override
	public int getColumnCount() {
		return cols.size();
	}

	@Override
	public int getColumnIndex(Object columnIdentifier) {
		
		return 0;
	}

	@Override
	public int getColumnIndexAtX(int xPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnMargin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getColumnSelectionAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration<TableColumn> getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSelectedColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] getSelectedColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListSelectionModel getSelectionModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalColumnWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveColumn(int columnIndex, int newIndex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeColumn(TableColumn column) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeColumnModelListener(TableColumnModelListener x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColumnMargin(int newMargin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColumnSelectionAllowed(boolean flag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSelectionModel(ListSelectionModel newModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getColumnCount(boolean includeHidden) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TableColumn> getColumns(boolean includeHidden) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableColumnExt getColumnExt(Object identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TableColumnExt getColumnExt(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addColumnModelListener(TableColumnModelListener x) {
		// TODO Auto-generated method stub
		
	}

}
