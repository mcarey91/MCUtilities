package mcutils.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import mcutils.MCFrameUtils;

public class MCMemorySplitPane extends JSplitPane
{

	public static final int HEIGHT = 600;
	public static final int WIDTH = 700;
	
	
	public static void main(String[] args) {
		JFrame f = MCFrameUtils.newFrame();
		f.setSize(WIDTH,HEIGHT);
		
		MCMemorySplitPane masterSplit = new MCMemorySplitPane();
		
		JTabbedPane jtp = new JTabbedPane();
		for(int i = 0; i < 10; i++)
		{
			JPanel jpl = new JPanel();
			jpl.setLayout(new BorderLayout());
			jpl.add(new JLabel("Item"+i));
			jtp.addTab("Item"+i, jpl);
		}
		
		masterSplit.setRightComponent(jtp);
		
		
		MCMemorySplitPane leftSplit = new MCMemorySplitPane();
		leftSplit.setOrientation(MCMemorySplitPane.VERTICAL_SPLIT);
		masterSplit.setLeftComponent(leftSplit);
		
		JTabbedPane props = new JTabbedPane();
		props.addTab("Status",new JLabel("Status"));
		props.addTab("Properties",new JLabel("Properties"));
		leftSplit.setBottomComponent(props);
		
		MCMemorySplitPane treeSplit = new MCMemorySplitPane();
		treeSplit.setOrientation(MCMemorySplitPane.VERTICAL_SPLIT);
		leftSplit.setTopComponent(treeSplit);
		
		treeSplit.setTopComponent(new JTree());
		treeSplit.setBottomComponent(new JLabel("Thing"));
		
		f.setContentPane(masterSplit);
		
		
		MCFrameUtils.setPosition(f);
		f.setVisible(true);
		
		
		
	}
}
