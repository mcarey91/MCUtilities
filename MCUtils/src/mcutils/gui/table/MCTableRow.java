package mcutils.gui.table;

import java.util.ArrayList;

public class MCTableRow extends ArrayList<Object> {

	Object data = null;
	
	public MCTableRow()
	{
		this(null);
	}
	
	public MCTableRow(Object data)
	{
		super();
		this.data = data;
	}
	
}
