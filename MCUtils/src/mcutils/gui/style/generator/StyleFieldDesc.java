package mcutils.gui.style.generator;

import java.util.ArrayList;
import java.util.List;

import mcutils.gui.style.StyleField;

public class StyleFieldDesc {
	
	private String parentClass;
	private String name;
	private String type;
	private String displayName = "NO DISPLAY NAME SET";
	private String defaultValue = "";
	private String description = "NO DESCRIPTION SET";
	
	public StyleFieldDesc(String parentClass, String name)
	{
		this.parentClass = parentClass;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	private String generateFieldName()
	{
		return "fd_" + name;
	}
	
	public String getInitializer()
	{
		return "public static StyleField<"+type+"> " + generateFieldName()
					+ " = new StyleField<"+type+">("+parentClass + ".class, " + type +".class)";
	}

	public List<String> getProperties() {
		String fn = generateFieldName();
		List<String> props = new ArrayList<>();
		props.add(fn + ".setName(\""+name+"\")");
		props.add(fn + ".setDisplayName(\""+displayName+"\")");
		if(type.equals("String"))
			props.add(fn + ".setDefaultValue(\""+defaultValue+"\")");
		else
			props.add(fn + ".setDefaultValue("+defaultValue+")");
		props.add(fn + ".setDescription(\""+description+"\")");
		props.add("addField(" + fn + ")");
		return props;
	}
	
	public String getField()
	{
		if(defaultValue.isEmpty())
			return "private " + type + " " + name + ";";
		else
			return "private " + type + " " + name + " = " + defaultValue + ";";
	}
	
	private String getUpperCaseName()
	{
		return name.toUpperCase().substring(0, 1) + name.substring(1);
	}
	
	public String getGetter()
	{
		return "public " + type + " get" + getUpperCaseName() + "(){ return " + name + "; }";
	}
	
	public String getSetter()
	{
		return "public void set" + getUpperCaseName() 
				+ "(" + type + " " + name + "){ this." + name + " = " + name + "; }";
	}
}
