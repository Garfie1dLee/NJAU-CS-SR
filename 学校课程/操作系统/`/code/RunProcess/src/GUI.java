import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ibm.icu.impl.CharacterPropertiesImpl;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.FlowLayout;

public class GUI extends JFrame {
	public static JPanel contentPane;
	public static JTextArea textArea=new JTextArea();
	public static JScrollPane scrollPane=new JScrollPane();
	public static JPanel panel;
	static Lock Lock = new ReentrantLock();
	static Condition Condition = Lock.newCondition();
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	static JTextArea textArea_1 = new JTextArea();
	public static int COUNT=0;
	static String filename="test/ProcessResults-";
	static String filename1=".txt";
	static JLabel lblNewLabel;
	static JTextField textField =new JTextField();
	static JLabel lblNewLabel_1;
	static JTextField textField_1 =new JTextField();
	static JLabel lblNewLabel_2;
	static JTextField textField_2=new JTextField();
	static JLabel lblNewLabel_3;
	static JTextField textField_3=new JTextField();
	static JLabel lblNewLabel_4;
	static JTextField textField_4=new JTextField();
	static JTextField textField_5=new JTextField();
	static JTextField textField_6=new JTextField();
	static JTextField textField_7 = new JTextField();
	static JTextField textField_8 = new JTextField();
	private JLabel lblNewLabel_8;
	static JOptionPane jop=new JOptionPane();
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JLabel lblNewLabel_9;
	static JTextField textField_9;
	private JLabel lblNewLabel_5;
	private JTextField textField_10;
	private JLabel lblNewLabel_6;
	static JTextField textField_11;
	Memoryp p=new Memoryp();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame=new GUI();
					frame.setVisible(true);
				} catch (NumberFormatException e) {
					jop.showMessageDialog(null, "请输入CPU运行速度", "请输入CPU运行速度", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}
	public GUI() {
		setResizable(false);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 353, 393);
		contentPane.add(scrollPane);
		textArea.setColumns(10);
		
		scrollPane.setViewportView(textArea);
		
		panel = new JPanel();
		panel.setBounds(353, 0, 436, 40);
		contentPane.add(panel);
		JButton btnNewButton = new JButton("\u5F00\u59CB/\u7EE7\u7EED");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {  //开始进程，或者继续
			public void actionPerformed(ActionEvent e) {
				TimeThread.GO();
				TimeThread a=new TimeThread();
				NewTask b=new NewTask();
				ProcessSchedule c=new ProcessSchedule();
				JobSchedule d=new JobSchedule();
				KeyBoard e1=new KeyBoard();
				Screen f=new Screen();
				ReadFile g=new ReadFile();
				WriteFile h =new WriteFile();
				//MissingPageInterrupt i=new MissingPageInterrupt();
				b.start();
				d.start();
				e1.start();
				f.start();
				g.start();
				h.start();
				c.start();
				a.start();
				//i.start();
				
				

			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("\u6682\u505C");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {  //暂停当前
				TimeThread.STOP();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 10));
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("\u521B\u5EFA\u65B0\u8FDB\u7A0B");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //新建作业，指令数目随机生成，优先级生成，指令随机生成
				int id=6;
				id+=COUNT++;
				int Priority=(int)(Math.random()*5);
				int InTimes=CPU.GetTime();
				int InsNum=(int)(Math.random()*50)+1;
				new JCB(id,Priority,InTimes,InsNum);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 10));
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u4FDD\u5B58");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //保存输出的文件
				Save();
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 10));
		panel.add(btnNewButton_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(353, 239, 322, 154);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(textArea_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(353, 35, 188, 206);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("\u5C31\u7EEA\u961F\u52171");
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("\u963B\u585E\u961F\u52171");
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_2 = new JLabel("\u963B\u585E\u961F\u52172");
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel_3 = new JLabel("\u963B\u585E\u961F\u52173");
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_1.add(textField_3);
		
		lblNewLabel_9 = new JLabel("\u963B\u585E\u961F\u52174");
		panel_1.add(lblNewLabel_9);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		panel_1.add(textField_9);
		
		lblNewLabel_5 = new JLabel("\u963B\u585E\u961F\u52175");
		panel_1.add(lblNewLabel_5);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		panel_1.add(textField_10);
		
		lblNewLabel_6 = new JLabel("\u7F13\u51B2\u533A       ");
		panel_1.add(lblNewLabel_6);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		panel_1.add(textField_11);
		
		JLabel lblNewLabel_7 = new JLabel("CPU\u8FD0\u884C\u901F\u5EA6");
		lblNewLabel_7.setBounds(585, 35, 108, 29);
		contentPane.add(lblNewLabel_7);
		textField_7.setText("1000");
		
		
		textField_7.setBounds(595, 76, 65, 29);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		lblNewLabel_8 = new JLabel("CPU\u8FD0\u884C\u72B6\u6001");
		lblNewLabel_8.setBounds(585, 117, 108, 29);
		contentPane.add(lblNewLabel_8);
		
		
		textField_8.setColumns(10);
		textField_8.setBounds(595, 158, 65, 29);
		contentPane.add(textField_8);
		
		btnNewButton_4 = new JButton("\u4F4D\u793A\u56FE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(681, 239, 79, 40);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("\u5185\u5B58\u56FE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemoryTable t=new MemoryTable();
				t.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(681, 291, 79, 40);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("QA");
		btnNewButton_5_1.setBounds(681, 339, 79, 40);
		contentPane.add(btnNewButton_5_1);
		
		lblNewLabel_4 = new JLabel("CPU\u65F6\u95F4   ");
		lblNewLabel_4.setBounds(701, 81, 59, 18);
		contentPane.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(695, 117, 65, 29);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
	}
	public static void aaa() {
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
		String temp= format.format(date);
		temp=filename+temp+filename1;
		try{FileOutputStream out=new FileOutputStream(temp);
	    String okk=textArea.getText();
		byte buffer[]=okk.getBytes();
		 out.write(buffer,0,buffer.length);
		 out.flush();
		 out.close();
		System.out.print("保存成功\n");
		}catch(Exception a) {a.printStackTrace();}
	}
	public static void Save() {
		String temp= String.valueOf(CPU.GetTime()-1);
		temp=filename+temp+filename1;
		try{FileOutputStream out=new FileOutputStream(temp);
	    String okk=textArea.getText();
		byte buffer[]=okk.getBytes();
		 out.write(buffer,0,buffer.length);
		 out.flush();
		 out.close();
		System.out.print("保存成功\n");
		}catch(Exception a) {a.printStackTrace();}
	}
}
