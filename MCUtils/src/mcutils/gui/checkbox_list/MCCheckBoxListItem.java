package mcutils.gui.checkbox_list;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class MCCheckBoxListItem<T> extends DefaultListCellRenderer
{
	private T item;
	private CHECK_STATE checked = CHECK_STATE.FALSE;
	private String displayValue = null;
	private MCCheckBoxListItem<?> parent = null;
	private List<MCCheckBoxListItem<?>> children = new ArrayList<>();
	private Consumer<MCCheckBoxListItem<?>> action = null;
	
	private JCheckBox jcb = new JCheckBox();
	private JPanel jpl = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel tabLabel = new JLabel();
	
	public enum CHECK_STATE
	{
		UNKNOWN("(?)"), FALSE("(F)"), TRUE("(T)");

		private String display;
		
		private CHECK_STATE(String display)
		{
			this.display = display;
		}
		
		@Override
		public String toString() {
			return display;
		}
	}
	
	public MCCheckBoxListItem(T item)
	{
		this(item,false);
	}
	
	public MCCheckBoxListItem(T item, boolean checked)
	{
		this.item = item;
		if(checked)
			this.checked = CHECK_STATE.TRUE;
		else
			this.checked = CHECK_STATE.FALSE;
	}
	
	public T getItem() {
		return item;
	}	
	
	public boolean isChecked() {
		return checked == CHECK_STATE.TRUE;
	}
	
	public void setCheckState(CHECK_STATE checked)
	{
		this.checked = checked;
	}
	
	public CHECK_STATE getCheckState()
	{
		return checked;
	}
	
	public List<MCCheckBoxListItem<?>> setChecked(boolean checked) {
		Map<MCCheckBoxListItem<?>, CHECK_STATE> result = setChecked(checked,true);
		return result.keySet().stream().collect(Collectors.toList());
	}
	
	public Map<MCCheckBoxListItem<?>,CHECK_STATE> setChecked(boolean checked, boolean doSet) {
		Map<MCCheckBoxListItem<?>,CHECK_STATE> results = new HashMap<>();
		
		CHECK_STATE state = checked ? CHECK_STATE.TRUE : CHECK_STATE.FALSE;
		results.put(this,state);
		//set this state
		if(doSet)
			this.checked = state;
		
		//set decendant states
		List<MCCheckBoxListItem<?>> kids = new ArrayList<>();
		getAllChildren(kids);
		kids.forEach(c ->{
			results.put(c,state);
			if(doSet)
				c.checked = state;
		});
		
		//re-adjust parent states
		if(parent != null)
			parent.setCheckedUpwards(results,checked,doSet);
		return results;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	
	@Override
	public String toString() {
		if(getDisplayValue() != null)
		{
			return getDisplayValue();
		}
		else
		{
			if(item == null)
				return "null";
			else
				return item.toString();
		}
	}
	
	private void setCheckedUpwards(Map<MCCheckBoxListItem<?>,CHECK_STATE> results, boolean checked, boolean doSet)
	{
		boolean foundATrue = false;
		boolean foundAFalse = false;
		for (MCCheckBoxListItem<?> c : children) {
			if(c.isChecked())
				foundATrue = true;
			else
				foundAFalse = true;
			
			if(foundATrue && foundAFalse)
				break;
		}
		
		CHECK_STATE state;
		
		if(foundATrue && foundAFalse)
			state = CHECK_STATE.UNKNOWN;
		else if(foundATrue)
			state = CHECK_STATE.TRUE;
		else
			state = CHECK_STATE.FALSE;

		results.put(this,state);
		
		if(doSet)
			this.checked = state;
		
		if(parent != null)
			parent.setCheckedUpwards(results,checked,doSet);
	}
	
	public void getAllChildren(List<MCCheckBoxListItem<?>> items)
	{
		if(items == null)
			return;
		items.addAll(children);
		for (MCCheckBoxListItem<?> c : children) {
			c.getAllChildren(items);
		}
	}
	
	public void getAllParents(Set<MCCheckBoxListItem<?>> items)
	{
		if(items == null)
			return;
		if(parent != null)
		{
			items.add(parent);
			parent.getAllParents(items);
		}
	}
	
	public void addChild(MCCheckBoxListItem<?> child)
	{
		child.parent = this;
		children.add(child);
	}
	
	public MCCheckBoxListItem<?> getParent()
	{
		return parent;
	}
	
	public List<MCCheckBoxListItem<?>> getChildren()
	{
		return children;
	}

	public Consumer<MCCheckBoxListItem<?>> getAction() {
		return action;
	}

	public void setAction(Consumer<MCCheckBoxListItem<?>> action) {
		this.action = action;
	}
	
	public void fixCheckboxes()
	{
		if(children.isEmpty())
			return;
		
		for (MCCheckBoxListItem<?> c : children) {
			c.fixCheckboxes();
		}
		
		boolean foundATrue = false;
		boolean foundAFalse = false;
		for (MCCheckBoxListItem<?> c : children) {
			if(c.isChecked())
				foundATrue = true;
			else
				foundAFalse = true;
			
			if(foundATrue && foundAFalse)
				break;
		}
		
		if(foundATrue && foundAFalse)
			this.checked = CHECK_STATE.UNKNOWN;
		else if(foundATrue)
			this.checked = CHECK_STATE.TRUE;
		else
			this.checked = CHECK_STATE.FALSE;
		
		
	}
	
	public List<MCCheckBoxListItem<?>> flatten()
	{
		List<MCCheckBoxListItem<?>> items = new ArrayList<>();
		flatten(items);
		return items;
	}
	
	private void flatten(List<MCCheckBoxListItem<?>> results)
	{
		results.add(this);
		for (MCCheckBoxListItem<?> c : children) {
			c.flatten(results);
		}
	}
	
	public int getTier()
	{
		int i = 0;
		MCCheckBoxListItem<?> p = parent;
		while(p != null)
		{
			i++;
			p = p.getParent();
		}
		return i;
	}
	
	public String getTabs()
	{
		StringBuilder sb = new StringBuilder();
		int tier = getTier();
		for(int i = 0; i < tier; i++)
			sb.append("     ");
		return sb.toString();
	}
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		jpl.setBackground(label.getBackground());
		
		jcb.setSelected(isChecked());
		
		String tabs = getTabs();
		tabLabel.setText(tabs);
		
		if(jpl.getComponentCount() == 0)
		{
			jpl.add(tabLabel);
			jpl.add(jcb);
			jpl.add(label);
		}
		
		return jpl;
	}

	
}
