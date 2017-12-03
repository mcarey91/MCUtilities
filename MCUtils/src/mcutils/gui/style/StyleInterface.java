package mcutils.gui.style;

import java.util.List;

public interface StyleInterface {
	
	public StyleMetaData getMetaData();

	
	public default List<StyleField<?>> getFields()
	{
		return getMetaData().getFields();
	}	
}
