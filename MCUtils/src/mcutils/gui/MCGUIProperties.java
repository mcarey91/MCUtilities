package mcutils.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public abstract class MCGUIProperties
{
	private static final String COLOR = "COLOR:";
	private static final String FONT = "FONT:";
	
	protected Class<? extends Component> componentType;
	protected Map<String,Object> props = new HashMap<>();
	
	public MCGUIProperties(Class<? extends Component> componentType)
	{
		this.componentType = componentType;
		props.putAll(getDefaultProperties());
	}
	
	public abstract Map<String,Object> getDefaultProperties();
	
	
	public <A> A get(String id, Class<A> type)
	{
		return (A)props.get(id);
	}
	
	public Map<String,String> getPropertiesToWrite()
	{
		Map<String,String> results = new HashMap<>();
		props.entrySet().stream().forEach(e -> {
			String key = componentType.getSimpleName() + "_" + e.getKey();
			String value = null;
			Object objval = e.getValue();
			if(objval instanceof Color)
			{
				value = Integer.toString(((Color) objval).getRGB());
			}
			else if(objval instanceof Font)
			{
				int size = ((Font) objval).getSize();
				String face = ((Font) objval).getFamily();
				int style = ((Font) objval).getStyle();
				value = Integer.toString(size) + "," + face + "," + Integer.toString(style);
			}
			if(value == null)
				return;
			results.put(key,value);
			
		});
		return results;
	}
	
	public Object parseValue(String val)
	{
		if(val.startsWith(COLOR))
		{
			return new Color(Integer.parseInt(val.substring(COLOR.length())));
		}
		if(val.startsWith(FONT))
		{
			String[] vals = val.substring(FONT.length()).split(",");
			int size = Integer.parseInt(vals[0]);
			String name = vals[1];
			int style = Integer.parseInt(vals[1]);
			return new Font(name,style,size);
		}
		return null;
	}
	
}
