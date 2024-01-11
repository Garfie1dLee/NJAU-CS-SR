public class WriteFile extends Thread{ 
	static int Lastusetime = 0;   //default = 0
	static Process LoadingProcess=null;   //default = null
	static int Waittime= 0; //default = 0
	static boolean WState=false; //default=false
	public static void UseThis(Process process) { //用于装载需要调用资源的进程
		Memory.P();   //申请Memory缓冲区，仅在false情况下申请
		WState=true;
		LoadingProcess=process;
		Lastusetime=CPU.GetTime();
		Waittime=process.GetIns().get(process.getPC()).getInRunTimes();   //获得当前执行指令的执行时间
	}
	public void run() {
		 while(true) if(TimeThread.Sign){
			 {
				   GUI.Lock.lock();                    
				   try {
					   GUI.Condition.signalAll();   
				    }	
				   finally {	
				   }
				   DOSOMETHING();
					  try {GUI.Condition.await();} catch (InterruptedException e) {e.printStackTrace();}
					  GUI.Lock.unlock();}}
 }
	public static void DOSOMETHING() 
	{ 
			if(WState&&Memory.Buffer&&((CPU.GetTime()-Lastusetime)==Waittime)) {   //在缓冲区被占用时
			CPU.WriteFile(LoadingProcess);  
			LoadingProcess.AwakeProcess();
			LoadingProcess.RuningIRelse(true);
			
			LoadingProcess=PCB.pcbTOprocess(PCB.ChooseBQout(4));
		    if(LoadingProcess!=null)
		    	{Lastusetime=CPU.GetTime();
		        Waittime=LoadingProcess.GetIns().get(LoadingProcess.getPC()).getInRunTimes();}
		        //因为当前这秒作为使用的一秒
		    else {Memory.V();WState=false;}}
		 
			if(WState==false&&Memory.Buffer==false&&(PCB.ChooseBQout(4)!=null))
			{
		          UseThis(PCB.pcbTOprocess(PCB.ChooseBQout(4)));
			}
	}
	public static boolean If()
	{
		return WState;
	}
}
