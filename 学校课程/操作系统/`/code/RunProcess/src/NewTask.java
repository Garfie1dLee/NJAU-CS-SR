import java.util.ArrayList;
 
public class NewTask extends Thread{   //��Ҫ���̽��̱�����ÿ������һ��
	public void run() {
		while(true) if(TimeThread.Sign){  
				   GUI.Lock.lock();                       //������
				   try {
					  GUI.Condition.signalAll();   //���ѵȴ����еĽ���
				    }
				   finally {
				   }
				   if(CPU.GetTime()%5==0)
					   CheckNewProcessANDIFCREATE(IO.JobList);
					  try {//�����Ŀ���һ������һ��
							GUI.Condition.await();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  GUI.Lock.unlock();//�ͷ���
			  }
			 
	}
			  
	public  static void CheckNewProcessANDIFCREATE(ArrayList<Job> jCList) {
		int currenttime=CPU.GetTime();        //�鿴��CPU��ȥ�������Ƿ�������ҵ����������оʹ�������ҵ
		for(Job i:jCList) {
			if(i.gettime()<=currenttime&&i.gettime()>currenttime-5) {
				new JCB(i.getid(),i.getpriority(),i.gettime(),i.getinsnum());
				GUI.textArea.append(CPU.GetTime()+":[������ҵ:��ҵ���"+i.getid()+"]"+"\n");
			}
		}}
}
	

