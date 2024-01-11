import java.util.ArrayList;

public class JobSchedule extends Thread{   
	public void run() {
		 while(true) if(TimeThread.Sign){
			 {
				   GUI.Lock.lock();                    
				   try {
					   GUI.Condition.signalAll();   
				    }	
				   finally {	
				   }
				   if(JCB.Ifempty()==false)  //作业等待队列不为空执行
					   DO();
					  try {GUI.Condition.await();} catch (InterruptedException e) {e.printStackTrace();}
					  GUI.Lock.unlock();}}
}
	public static void DO() {
		if((JCB.Ifempty()==false)&&(Memory.free1memory()>=3))
		{
			int loc=Memory.GetMemory();//查看是否在内存中有空闲内存
			JCB jcb=JCB.poll();
			jcb.JobToProcess(loc);
		}
		}
	
}
	

