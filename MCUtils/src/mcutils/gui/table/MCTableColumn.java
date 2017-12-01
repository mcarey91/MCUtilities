package mcutils.gui.table;

import org.jdesktop.swingx.table.TableColumnExt;

public class MCTableColumn extends TableColumnExt{
	
	private Class<?> clazz;
	
	public MCTableColumn(String name, Class<?> clazz)
	{
		setTitle(name);
		setClazz(clazz);
	}


	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}
