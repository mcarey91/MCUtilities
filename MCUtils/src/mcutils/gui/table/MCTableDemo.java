package mcutils.gui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import mcutils.MCFrameUtils;
import mcutils.MCRandomUtils;

public class MCTableDemo {
	
	public enum TEST_ENUM
	{
		A,B,C,A_X,B_X,C_X;
	}
	
	public static void main(String[] args) {
		
		MCFrameUtils.setLookAndFeel();
		
		List<MCTableColumn> cols = new ArrayList<>();
		cols.add(new MCTableColumn("String",String.class));
		cols.add(new MCTableColumn("Integer",Integer.class));
		cols.add(new MCTableColumn("int",int.class));
		cols.add(new MCTableColumn("Double",Double.class));
		cols.add(new MCTableColumn("double",double.class));
		cols.add(new MCTableColumn("Float",Float.class));
		cols.add(new MCTableColumn("float",float.class));
		cols.add(new MCTableColumn("Long",Long.class));
		cols.add(new MCTableColumn("long",long.class));
		cols.add(new MCTableColumn("Enum",TEST_ENUM.class));
		
		MCTable table = new MCTable(cols);
		
		for(int i = 0; i < 25; i++)
		{
			MCTableRow row = new MCTableRow();
			row.add(MCRandomUtils.randomString(20));
			row.add(new Integer(MCRandomUtils.nextInt()));
			row.add(MCRandomUtils.nextInt());
			row.add(new Double(MCRandomUtils.nextDouble()));
			row.add(MCRandomUtils.nextDouble());
			row.add(new Float(MCRandomUtils.nextFloat()));
			row.add(MCRandomUtils.nextFloat());
			row.add(new Long(MCRandomUtils.nextLong()));
			row.add(MCRandomUtils.nextLong());
			row.add(TEST_ENUM.values()[MCRandomUtils.nextInt(TEST_ENUM.values().length)]);
			table.addRow(row);
		}
		
		MCFrameUtils.newFrame(new JScrollPane(table), 500, 500);
		
	}
}
