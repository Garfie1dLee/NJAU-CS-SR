public class WriteFile extends Thread{ 
	static int Lastusetime = 0;   //default = 0
	static Process LoadingProcess=null;   //default = null
	static int Waittime= 0; //default = 0
	static boolean WState=false; //default=false
	public static void UseThis(Process process) { //����װ����Ҫ������Դ�Ľ���
		Memory.P();   //����Memory������������false���������
		WState=true;
		LoadingProcess=process;
		Lastusetime=CPU.GetTime();
		Waittime=process.GetIns().get(process.getPC()).getInRunTimes();   //��õ�ǰִ��ָ���ִ��ʱ��
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
			if(WState&&Memory.Buffer&&((CPU.GetTime()-Lastusetime)==Waittime)) {   //�ڻ�������ռ��ʱ
			CPU.WriteFile(LoadingProcess);  
			LoadingProcess.AwakeProcess();
			LoadingProcess.RuningIRelse(true);
			
			LoadingProcess=PCB.pcbTOprocess(PCB.ChooseBQout(4));
		    if(LoadingProcess!=null)
		    	{Lastusetime=CPU.GetTime();
		        Waittime=LoadingProcess.GetIns().get(LoadingProcess.getPC()).getInRunTimes();}
		        //��Ϊ��ǰ������Ϊʹ�õ�һ��
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
