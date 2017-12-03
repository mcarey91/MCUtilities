package mcutils.gui.style;

import java.lang.reflect.Field;

public class StyleField<T> {
	
	private Class<? extends StyleInterface> parentClazz;
	private Class<T> clazz;
	private String name;
	private String displayName;
	private T defaultValue;
	private String description;
	
	private Field field;
	
	public StyleField(Class<? extends StyleInterface> parentClazz, Class<T> clazz)
	{
		this.parentClazz = parentClazz;
		this.clazz = clazz;
	}
	
	public void set(StyleInterface style, Object value)
	{
		cacheField();
		if(field != null)
		{
			try {
				field.set(style, value);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public T get(StyleInterface style)
	{
		cacheField();
		if(field != null)
		{
			try {
				return (T) field.get(style);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void cacheField()
	{
		if(field == null)
		{
			try {
				field = parentClazz.getDeclaredField(name);
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Class<T> getClazz() {
		return clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
