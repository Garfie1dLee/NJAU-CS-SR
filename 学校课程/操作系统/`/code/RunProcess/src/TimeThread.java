import java.awt.Point;

public class TimeThread extends Thread{
	public static int speed;
	public static boolean Sign=false;
	public void run() {
		
		while(true) if(TimeThread.Sign){
			{
			   GUI.Lock.lock();                       //������
			   try {
			    	
				   GUI.Condition.signalAll();   //���ѵȴ����еĽ���
			    }	
			   finally {	
			   }
			   try {
				    speed=Integer.valueOf(GUI.textField_7.getText());
					System.out.println(CPU.GetTime()+"---------------------------------------------------------");  //���ʱ��
					GUI.textField_4.setText(String.valueOf(CPU.GetTime()));  //���GUIʱ��
					KeyBoard.PrintResourceState();
					Screen.PrintResourceState();
					System.out.println("BuffState "+Memory.Buffer);
					System.out.println("WState "+WriteFile.WState);
					System.out.println("RState "+ReadFile.RState);
					Memory.printstate();
					Memoryp.update();
					PCB.ShowQuene();  //GUI����б� 
					if(PCB.number==IO.JobList.size())
					{
						GUI.Save(); PCB.number++;
					}
					GUI.scrollPane.getViewport().setViewPosition(new Point(0,GUI.scrollPane.getVerticalScrollBar().getMaximum()));
					Thread.sleep(speed);  //��˯1��
					CPU.plustime();  //CPU����ʱ���һ
			   }
			  catch(InterruptedException e) {
				  e.printStackTrace();
			  }
				  try {//�����Ŀ���һ������һ��
						GUI.Condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  GUI.Lock.unlock();//�ͷ���
		 }
		}
	}
	public static void main(String args[]) {
	}
	public static void GO() {  //ͨ�����Ʒ�����ʱ�ӿ��������
		Sign=true;
	}
	public static void STOP() {
		Sign=!Sign;
	}
}
	
	



	  