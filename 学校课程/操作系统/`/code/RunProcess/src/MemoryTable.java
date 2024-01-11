import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MemoryTable extends JFrame{
		 
	    private static final long serialVersionUID = 1L;
	  //初始化窗体
	    public MemoryTable(){
	    	 
	        super();
	   
	        initialize();
	   
	     }
	    
	    private  void initialize(){
	  
	       this.setSize(200,450);
	  
	       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置窗体关闭模式
	  
	       setContentPane(new Plot());
	       Dimension preferredSize = new Dimension(80,20);
	  
	       this.setTitle("MemoryList");
	  
	       this.setLocationRelativeTo(null);//窗体居中
	  
	       this.setVisible(true);//设置窗体的可见性
	  
	    
	  
	    }
	 
	    //创建绘图面板
	       class Plot extends JPanel{
	    	 
	        private static final long serialVersionUID = 1L;
	   
	        public void paint(Graphics gp){
	            BasicStroke bs = new BasicStroke( 10.1f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
	            super.paint(gp);
	            Graphics2D gp2d = (Graphics2D) gp;
	            
	            int start=50;
	            gp2d.setColor(Color.black);
	            gp2d.drawString("内存连续空间分配表", 20, 35);
	            gp2d.setColor(Color.black);
	            gp2d.drawString("0", 125,start+10);
	            start=start+10*8;
	            gp2d.drawString("7", 125,start);
	            gp2d.setColor(Color.pink);
	            for(MemoryDataS e:Memory.MemoryList)
	            	if(e.getOccupySta())
	            		
	    	            {
	            		gp2d.setColor(Color.black);
	    	            gp2d.drawString(String.valueOf(e.getStartLoc()), 125, start+10);
	            		gp2d.setColor(Color.pink);
	            		gp2d.fillRect(20,start,100,10*e.getDSize());
	    	            start=start+10*e.getDSize();
	    	            gp2d.setColor(Color.black);
	    	            gp2d.drawString(String.valueOf(e.getStartLoc()+e.getDSize()-1), 125, start);
	    	            }
	            	else
	            	{
	            		gp2d.setColor(Color.black);
	    	            gp2d.drawString(String.valueOf(e.getStartLoc()), 125, start+10);
	            		gp2d.setColor(Color.pink);
	            		gp2d.drawRect(20,start,100,10*e.getDSize());
	            		start=start+10*e.getDSize();
	            		gp2d.setColor(Color.black);
	    	            gp2d.drawString(String.valueOf(e.getStartLoc()+e.getDSize()-1), 125, start);
	            	}
	            for(int i=1;i<=32;i++)
		            gp2d.drawRect(20,50,100,10*i);
	            		

	        }
	     }
	    public  void diaoyong()
	    {
	    	 initialize();
	    }
	    public static void main(String[] args) {
	    	Memory.GetMemory();
	    	Memory.GetMemory();
	        new MemoryTable();
	     }
}
