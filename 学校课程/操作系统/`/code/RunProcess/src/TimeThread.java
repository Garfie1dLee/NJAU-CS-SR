import java.awt.Point;

public class TimeThread extends Thread{
	public static int speed;
	public static boolean Sign=false;
	public void run() {
		
		while(true) if(TimeThread.Sign){
			{
			   GUI.Lock.lock();                       //请求锁
			   try {
			    	
				   GUI.Condition.signalAll();   //唤醒等待队列的进程
			    }	
			   finally {	
			   }
			   try {
				    speed=Integer.valueOf(GUI.textField_7.getText());
					System.out.println(CPU.GetTime()+"---------------------------------------------------------");  //输出时间
					GUI.textField_4.setText(String.valueOf(CPU.GetTime()));  //输出GUI时间
					KeyBoard.PrintResourceState();
					Screen.PrintResourceState();
					System.out.println("BuffState "+Memory.Buffer);
					System.out.println("WState "+WriteFile.WState);
					System.out.println("RState "+ReadFile.RState);
					Memory.printstate();
					Memoryp.update();
					PCB.ShowQuene();  //GUI输出列表 
					if(PCB.number==IO.JobList.size())
					{
						GUI.Save(); PCB.number++;
					}
					GUI.scrollPane.getViewport().setViewPosition(new Point(0,GUI.scrollPane.getVerticalScrollBar().getMaximum()));
					Thread.sleep(speed);  //沉睡1秒
					CPU.plustime();  //CPU运行时间加一
			   }
			  catch(InterruptedException e) {
				  e.printStackTrace();
			  }
				  try {//正常的控制一秒运行一次
						GUI.Condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  GUI.Lock.unlock();//释放锁
		 }
		}
	}
	public static void main(String args[]) {
	}
	public static void GO() {  //通过控制符控制时钟开启或结束
		Sign=true;
	}
	public static void STOP() {
		Sign=!Sign;
	}
}
	
	



	  