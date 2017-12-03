package mcutils.gui.table;

import java.util.ArrayList;

public class MCTableRow extends ArrayList<Object> {

	private static final long serialVersionUID = 1L;
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
