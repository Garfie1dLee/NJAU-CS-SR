import java.util.ArrayList;
 
public class NewTask extends Thread{   //需要进程进程保护，每五秒检查一次
	public void run() {
		while(true) if(TimeThread.Sign){  
				   GUI.Lock.lock();                       //请求锁
				   try {
					  GUI.Condition.signalAll();   //唤醒等待队列的进程
				    }
				   finally {
				   }
				   if(CPU.GetTime()%5==0)
					   CheckNewProcessANDIFCREATE(IO.JobList);
					  try {//正常的控制一秒运行一次
							GUI.Condition.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  GUI.Lock.unlock();//释放锁
			  }
			 
	}
			  
	public  static void CheckNewProcessANDIFCREATE(ArrayList<Job> jCList) {
		int currenttime=CPU.GetTime();        //查看在CPU过去五秒内是否有新作业产生，如果有就创建该作业
		for(Job i:jCList) {
			if(i.gettime()<=currenttime&&i.gettime()>currenttime-5) {
				new JCB(i.getid(),i.getpriority(),i.gettime(),i.getinsnum());
				GUI.textArea.append(CPU.GetTime()+":[新增作业:作业编号"+i.getid()+"]"+"\n");
			}
		}}
}
	

