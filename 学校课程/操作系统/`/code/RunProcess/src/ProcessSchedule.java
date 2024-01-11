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
	public  static void dosomething() { //时间片轮转
		// TODO Auto-generated method stub
		PCB.SortRqQuene();   //首先对于就绪队列进行排序，确定正确的队头元素
		if(CPU.GetCPUState()== true )     //根据CPU的运行状态选择是否拿出新元素还是就当前进程继续执行
		{
			if(CPU.loadingprocess.iftimesliceleft()) //如果当前进程的时间片还有剩余，就选择继续执行
				CPU.DOSOMETHING();
			else { 
				CPU.loadingprocess.setPSW(2);    //如果当前进程的时间片已经用完，那么就设置为就绪态然后加载新进程
				PCB.joinRqQuene(CPU.loadingprocess);
				CPULoadProcess(PCB.pcbTOprocess(PCB.pollRQ()));
				CPU.loadingprocess.ResetTimeSlice();
				CPU.DOSOMETHING();
			}}
		else
		{
			PCB firstPcb=PCB.pollRQ();         //如果CPU不再执行，那么根据就绪队列是否有进程来选择执行或者不执行
			if(firstPcb==null)
			{
				//GUI
				System.out.println("CPU FREE");
				GUI.textArea.append(CPU.GetTime()+":[CPU空闲]\n");
				GUI.textField_5.setText("空闲");
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
	public static void CPULoadProcess(Process process) //CPU用于装载进程，需要切换CPU状态
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
