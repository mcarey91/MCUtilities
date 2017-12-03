package mcutils.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.Highlighter;

public class MCTableHighlighter implements Highlighter{

	List<ChangeListener> changeListeners = new ArrayList<>();
	
	private MCTableSettings settings;
	
	public MCTableHighlighter(MCTableSettings settings)
	{
		this.settings = settings;
	}
	
	@Override
	public Component highlight(Component renderer, ComponentAdapter adapter) {
		boolean even = adapter.row % 2 == 0;
		if(!adapter.isSelected())
		{
			if(even)
				renderer.setBackground(settings.getStyle().getRowHighlightEven());
			else
				renderer.setBackground(settings.getStyle().getRowHighlightOdd());
		}
		return renderer;
	}

	@Override
	public void addChangeListener(ChangeListener l) {
		changeListeners.add(l);
	}

	@Override
	public void removeChangeListener(ChangeListener l) {
		changeListeners.remove(l);
	}

	@Override
	public ChangeListener[] getChangeListeners() {
		return changeListeners.toArray(new ChangeListener[changeListeners.size()]);
	}

}
