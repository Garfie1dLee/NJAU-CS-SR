public class KeyBoard extends Thread{  
	static boolean state = false;  //默认为false
	static int Lastusetime = 0;   //默认为0 
	static Process LoadingProcess=null;   //default =null
	static int Waittime= 0;
	public static void UseThis(Process process) { //用于装载需要调用资源的进程
		state=true;
		LoadingProcess=process;
		Lastusetime=CPU.GetTime();
		Waittime=process.GetIns().get(process.getPC()).getInRunTimes();  //get inruntime
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
			if(state&&((CPU.GetTime()-Lastusetime)==Waittime)) {
			LoadingProcess.AwakeProcess();
			LoadingProcess.RuningIRelse(true);
			LoadingProcess=PCB.pcbTOprocess(PCB.ChooseBQout(1));
		    if(LoadingProcess!=null)
		    {Lastusetime=CPU.GetTime();
	        Waittime=LoadingProcess.GetIns().get(LoadingProcess.getPC()).getInRunTimes();}
		    else state=false;}
			
			if(state==false&&(PCB.ChooseBQout(1)!=null))
			{
		          UseThis(PCB.pcbTOprocess(PCB.ChooseBQout(1)));
			}
		
	}
	public static void PrintResourceState() {    //输出当前的资源占用状态 备用
	if(!state)
	{
		System.out.println("KEYBOARD FREE");
	}
	else System.out.println("KEYBOARD NOW OCCUPIED");
	}
	public static boolean If()
	{
		return state;
	}
}
