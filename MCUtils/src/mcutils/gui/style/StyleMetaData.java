package mcutils.gui.style;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StyleMetaData {

	private Map<String,StyleField<?>> fieldMap;

	public StyleMetaData(LinkedHashMap<String, StyleField<?>> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public List<StyleField<?>> getFields()
	{
		return fieldMap.values().stream().collect(Collectors.toList());
	}
}
