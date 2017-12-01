package mcutils;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MCFrameUtils {
	
	public static void setLookAndFeel()
	{
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
		}
	}
	
	public static void setPosition(JFrame frame)
	{
		frame.setLocationRelativeTo(null);
	}
	
	public static void newFrame(JComponent comp)
	{
		newFrame(comp,500,500);
	}
	
	public static void newFrame(JComponent comp, int width, int height)
	{
		JFrame myFrame = new JFrame();
		myFrame.setContentPane(comp);
		myFrame.setSize(width, height);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPosition(myFrame);
		myFrame.setVisible(true);
	}
	
	public static JFrame newFrame()
	{
		setLookAndFeel();
		JFrame myFrame = new JFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return myFrame;
	}
}
