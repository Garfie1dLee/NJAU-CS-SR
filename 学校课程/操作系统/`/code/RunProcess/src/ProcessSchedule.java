import java.awt.Point;
import java.util.ArrayList;

public class ProcessSchedule extends Thread {
	public void run() {
		 while(true) if(TimeThread.Sign){
			 {
				   GUI.Lock.lock();                    
				   try {
					   GUI.Condition.signalAll();   
				    }	
				   finally {	
				   }
				   dosomething();
					  try {GUI.Condition.await();} catch (InterruptedException e) {e.printStackTrace();}
					  GUI.Lock.unlock();}}
   }
	public  static void dosomething() { //ʱ��Ƭ��ת
		// TODO Auto-generated method stub
		PCB.SortRqQuene();   //���ȶ��ھ������н�������ȷ����ȷ�Ķ�ͷԪ��
		if(CPU.GetCPUState()== true )     //����CPU������״̬ѡ���Ƿ��ó���Ԫ�ػ��Ǿ͵�ǰ���̼���ִ��
		{
			if(CPU.loadingprocess.iftimesliceleft()) //�����ǰ���̵�ʱ��Ƭ����ʣ�࣬��ѡ�����ִ��
				CPU.DOSOMETHING();
			else { 
				CPU.loadingprocess.setPSW(2);    //�����ǰ���̵�ʱ��Ƭ�Ѿ����꣬��ô������Ϊ����̬Ȼ������½���
				PCB.joinRqQuene(CPU.loadingprocess);
				CPULoadProcess(PCB.pcbTOprocess(PCB.pollRQ()));
				CPU.loadingprocess.ResetTimeSlice();
				CPU.DOSOMETHING();
			}}
		else
		{
			PCB firstPcb=PCB.pollRQ();         //���CPU����ִ�У���ô���ݾ��������Ƿ��н�����ѡ��ִ�л��߲�ִ��
			if(firstPcb==null)
			{
				//GUI
				System.out.println("CPU FREE");
				GUI.textArea.append(CPU.GetTime()+":[CPU����]\n");
				GUI.textField_5.setText("����");
			}
			else
			{
				System.out.println("xxxxxxxx"+firstPcb.getProID());
				CPULoadProcess(PCB.pcbTOprocess(firstPcb));
				CPU.SetCPUState(true);
				CPU.loadingprocess.ResetTimeSlice();
				CPU.DOSOMETHING();
				
			}
		}
	}
	public static void CPULoadProcess(Process process) //CPU����װ�ؽ��̣���Ҫ�л�CPU״̬
	{
		CPU.GOTOCORE(true);
		process.setPSW(1);
		CPU.loadingprocess=process;
		CPU.GOTOUSER(true);
		CPU.SetIR(process.getIR());
		CPU.SetPC(process.getPC());
		CPU.SetPSW(process.getPSW());
	}
}
