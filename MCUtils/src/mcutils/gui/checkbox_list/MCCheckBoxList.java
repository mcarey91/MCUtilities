package mcutils.gui.checkbox_list;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import mcutils.MCFrameUtils;
import mcutils.gui.checkbox_list.MCCheckBoxListItem.CHECK_STATE;

public class MCCheckBoxList extends JList<MCCheckBoxListItem<?>>
{
	private static final long serialVersionUID = 1L;
	
	private Consumer<Map<MCCheckBoxListItem<?>,MCCheckBoxListItem.CHECK_STATE>> prefixAction = null;
	
	private Consumer<List<MCCheckBoxListItem<?>>> postfixAction = null;
	
	private DefaultListModel<MCCheckBoxListItem<?>> model = new DefaultListModel<MCCheckBoxListItem<?>>();
	
	public MCCheckBoxList()
	{
		setModel(model);
		setCellRenderer(new MCCheckBoxListRenderer());
		addMouseListener(new MouseAdapter()
		{
			private Set<Integer> indices = new HashSet<>();
			
			@Override
			public void mousePressed(MouseEvent e) { check(e); }
			
			@Override
			public void mouseDragged(MouseEvent e) { check(e); }
			
			@Override
			public void mouseExited(MouseEvent event){ finish(event); }
			
			@Override
			public void mouseReleased(MouseEvent event){ finish(event); }
			
			private void check(MouseEvent event)
			{
				Point p = event.getPoint();
				MCCheckBoxList list = MCCheckBoxList.this;
				int index = list.locationToIndex(p);
				if(index == -1 || indices.contains(index))
					return;
				MCCheckBoxListItem<?> cb = list.getModel().getElementAt(index);
				int min = 14 + cb.getTier()*20;
				int max = min + 16;
				if(p.x >= min && p.x <= max)
				{
					indices.add(index);
					System.out.println("check " + index);
				}
			}
			
			private void finish(MouseEvent event)
			{
				check(event);
				//grab the last index, if possible
				MCCheckBoxList list = MCCheckBoxList.this;
				Set<MCCheckBoxListItem<?>> items = new HashSet<>();
				indices.forEach(i -> {
					int index = i.intValue();
					MCCheckBoxListItem<?> cb = list.getModel().getElementAt(index);
					items.add(cb);
				});
				indices.clear();
				Set<MCCheckBoxListItem<?>> toRemove = new HashSet<>();
				
				for(MCCheckBoxListItem<?> i : items)
				{
					Set<MCCheckBoxListItem<?>> allParents = new HashSet<>();
					i.getAllParents(allParents);
					for(MCCheckBoxListItem<?> p : allParents)
					{
						if(items.contains(p))
						{
							toRemove.add(i);
							break;
						}
					}
				}
				
				items.removeAll(toRemove);
				for(MCCheckBoxListItem<?> i : items)
				{
					processCheckbox(i);
				}
			}
		});
	}
	
	public void addItem(MCCheckBoxListItem<?> item)
	{
		item.fixCheckboxes();
		List<MCCheckBoxListItem<?>> flatten = item.flatten();
		for (MCCheckBoxListItem<?> i : flatten) {
			model.addElement(i);	
		}
		
	}
	
	private void processCheckbox(MCCheckBoxListItem<?> cb)
	{
		boolean val = !cb.isChecked();
		Map<MCCheckBoxListItem<?>, CHECK_STATE> checkState = cb.setChecked(val,false);
		if(prefixAction != null)
			prefixAction.accept(checkState);
		
		checkState.entrySet().stream().forEach(e -> {
			e.getKey().setCheckState(e.getValue());
			if(e.getKey().getAction() != null)
				e.getKey().getAction().accept(e.getKey());
		});

		if(postfixAction != null)
			postfixAction.accept(checkState.keySet().stream().collect(Collectors.toList()));
		
		SwingUtilities.invokeLater(() -> {
			revalidate();
			repaint();
		});
	}
	
	public static void main(String[] args) {
		JFrame f = MCFrameUtils.newFrame();
		
		JPanel jpl = new JPanel(new BorderLayout());
		
		jpl.add(new JButton("TEST"),BorderLayout.NORTH);
		jpl.add(new JButton("TEST"),BorderLayout.WEST);
		
		MCCheckBoxList l = new MCCheckBoxList();
		
		MCCheckBoxListItem<String> a = new MCCheckBoxListItem<>("a",false);
		MCCheckBoxListItem<String> a_1 = new MCCheckBoxListItem<>("a_1",true);
		MCCheckBoxListItem<String> a_1_1 = new MCCheckBoxListItem<>("a_1_1",true);
		MCCheckBoxListItem<String> a_2 = new MCCheckBoxListItem<>("a_2",true);
		MCCheckBoxListItem<String> a_3 = new MCCheckBoxListItem<>("a_3",true);
		a.addChild(a_1);
		a_1.addChild(a_1_1);
		a.addChild(a_2);
		a.addChild(a_3);
		
		MCCheckBoxListItem<String> b = new MCCheckBoxListItem<>("b",true);
		MCCheckBoxListItem<String> b_1 = new MCCheckBoxListItem<>("b_1",false);
		MCCheckBoxListItem<String> b_2 = new MCCheckBoxListItem<>("b_2",false);
		MCCheckBoxListItem<String> b_3 = new MCCheckBoxListItem<>("b_3",false);
		b.addChild(b_1);
		b.addChild(b_2);
		b.addChild(b_3);
		
		MCCheckBoxListItem<String> c = new MCCheckBoxListItem<>("c",true);
		MCCheckBoxListItem<String> c_1 = new MCCheckBoxListItem<>("c_1",false);
		MCCheckBoxListItem<String> c_2 = new MCCheckBoxListItem<>("c_2",true);
		MCCheckBoxListItem<String> c_3 = new MCCheckBoxListItem<>("c_3",false);
		c.addChild(c_1);
		c.addChild(c_2);
		c.addChild(c_3);
		
		l.addItem(a);
		l.addItem(b);
		l.addItem(c);
		
		jpl.add(new JScrollPane(l),BorderLayout.CENTER);
		
		l.setBorder(new LineBorder(Color.BLACK));
		
		f.setContentPane(jpl);
		f.setVisible(true);
	}
}
