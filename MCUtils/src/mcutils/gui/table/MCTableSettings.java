package mcutils.gui.table;

import org.jdesktop.swingx.decorator.Highlighter;

public class MCTableSettings {
	
	private MCTableStyle style = new MCTableStyle();
	private boolean renderingBooleansAsCheckboxes = false;
	private MCTableHighlighter highligher = new MCTableHighlighter(this);
	
	public MCTableSettings()
	{
		
	}

	public MCTableStyle getStyle() {
		return style;
	}

	public void setStyle(MCTableStyle style) {
		this.style = style;
	}

	public boolean isRenderingBooleansAsCheckboxes() {
		return renderingBooleansAsCheckboxes;
	}

	public void setRenderingBooleansAsCheckboxes(boolean renderingBooleansAsCheckboxes) {
		this.renderingBooleansAsCheckboxes = renderingBooleansAsCheckboxes;
	}

	public Highlighter getHighLighter() {
		return highligher;
	}
	
}
