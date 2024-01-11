public class ReadFile extends Thread{  //资源类包括键盘、显示和PV操作三种
	static int Lastusetime = 0;   //默认为0 
	static Process LoadingProcess=null;   //default =null
	static int Waittime= 0;
	static boolean RState=false; //defailt = false
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
	public static void UseThis(Process process) { //用于装载需要调用资源的进程
		Memory.P();
		RState=true;
		LoadingProcess=process;
		Lastusetime=CPU.GetTime();
		Waittime=process.GetIns().get(process.getPC()).getInRunTimes();
	}
	public static void DOSOMETHING() 
	{ 
		
			if(RState&&Memory.Buffer&&((CPU.GetTime()-Lastusetime)==Waittime)) {
				CPU.ReadFile(LoadingProcess);
			LoadingProcess.AwakeProcess();
			LoadingProcess.RuningIRelse(true);
			LoadingProcess=PCB.pcbTOprocess(PCB.ChooseBQout(3));
		    if(LoadingProcess!=null)
		    {Lastusetime=CPU.GetTime();
	        Waittime=LoadingProcess.GetIns().get(LoadingProcess.getPC()).getInRunTimes();}
		    else {Memory.V();RState=false;}
		    }
			
			if(RState==false&&Memory.Buffer==false&&(PCB.ChooseBQout(3)!=null))
					{
				          UseThis(PCB.pcbTOprocess(PCB.ChooseBQout(3)));
					}
			
			
		 
	}
	public static boolean If()
	{
		return RState;
	}

	
	
}

