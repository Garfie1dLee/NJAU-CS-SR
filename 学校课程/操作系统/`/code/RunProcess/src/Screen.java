public class Screen extends Thread{  //��Դ��������̡���ʾ��PV��������
	static boolean state = false;  //Ĭ��Ϊfalse
	static int Lastusetime = 0;   //Ĭ��Ϊ0 
	static Process LoadingProcess=null;   //default =null
	static int Waittime= 0;
	public static void UseThis(Process process) { //����װ����Ҫ������Դ�Ľ���
		state=true;
		LoadingProcess=process;
		Lastusetime=CPU.GetTime();
		Waittime=process.GetIns().get(process.getPC()).getInRunTimes();
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
			LoadingProcess=PCB.pcbTOprocess(PCB.ChooseBQout(2));
		    if(LoadingProcess!=null)
		    {Lastusetime=CPU.GetTime();
	        Waittime=LoadingProcess.GetIns().get(LoadingProcess.getPC()).getInRunTimes();}
		    else state=false;}
			
			if(state==false&&(PCB.ChooseBQout(2)!=null))
			{
		          UseThis(PCB.pcbTOprocess(PCB.ChooseBQout(2)));
			}
		
	}
	public static void PrintResourceState() {    //�����ǰ����Դռ��״̬ ����
	if(!state)
	{
		System.out.println("SCREEN FREE");
	}
	else System.out.println("SCREEN  NOW OCCUPIED");
	}
	public static boolean If()
	{
		return state;
	}
	
}