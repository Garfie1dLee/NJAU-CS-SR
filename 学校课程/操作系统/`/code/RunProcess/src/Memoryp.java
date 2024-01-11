import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Memoryp extends JFrame {

	static JScrollPane scrollPane;
	private static JPanel contentPane;
	private static JTable table;
	private static Object[][] tableinfo=new Object[2][17];
	private static String[] name= {" ","0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
	private JButton button;
	public Memoryp() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 159);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		tableinfo[0][0]="0";
		tableinfo[1][0]="1";
		for(int i=0;i<2;i++)
			for(int j=0;j<16;j++)
				tableinfo[i][j+1]=Memory.Memory[i*16+j]==false?0:1;
		table = new JTable(tableinfo,name);
		scrollPane.setViewportView(table);
		
		button = new JButton("New button");
		scrollPane.setColumnHeaderView(button);
		
	}
	public static void update()
	{
		for(int i=0;i<2;i++)
			for(int j=0;j<16;j++)
				tableinfo[i][j+1]=Memory.Memory[i*16+j]==false?0:1;
		table = new JTable(tableinfo,name);
		scrollPane.setViewportView(table);

}
}
