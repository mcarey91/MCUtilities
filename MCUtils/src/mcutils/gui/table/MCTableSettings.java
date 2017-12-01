package mcutils.gui.table;

import mcutils.gui.MCGUIProperties;
import mcutils.gui.MCStyle;

public class MCTableSettings {
	
	private MCGUIProperties style;
	private boolean renderingBooleansAsCheckboxes = false;
	
	public MCTableSettings()
	{
		try {
			setStyle(MCStyle.DEFAULT_STYLES.get(MCTable.class).newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public MCGUIProperties getStyle() {
		return style;
	}

	public void setStyle(MCGUIProperties style) {
		this.style = style;
	}

	public boolean isRenderingBooleansAsCheckboxes() {
		return renderingBooleansAsCheckboxes;
	}

	public void setRenderingBooleansAsCheckboxes(boolean renderingBooleansAsCheckboxes) {
		this.renderingBooleansAsCheckboxes = renderingBooleansAsCheckboxes;
	}
	
}
