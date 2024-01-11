import java.util.Stack;

public class CPU {
	private static int PC; //对于CPU中的量存储为静态变量PC，存储当前指令的地址
	private static int IR; //对于CPU中的量存储为静态变量IR，存储当前指令的类型
	private static int PSW; //存储当前执行的进程的状态
    private static  int time=0;  //存储CPU运行时间
    private static boolean CPUState=false;  //CPU状态默认为关闭
    private static boolean CPUCoreState= false;  //false代表CPU处于用户态，true为核心态
    private static boolean CPUInterrupt=false;   //关中断flag
    static boolean DoMP=false;  //domp
    static Process loadingprocess= null;    //存储为CPU当时所加载的进程，该进程只处于CPU内
    static int[] ProtectCPU= new int[3];  //用于临时存储CPU的PC、IR、PSW
    static void SetInturrupt()  //切换关中断
    {
    	CPUInterrupt=!CPUInterrupt;
    }
    static void plustime()  //CPU运行时间加一
    {  CPU.time=CPU.time+1;
    }
    static void PrintTime() {  //输出CPU的运行时间
    	System.out.print(CPU.time+" ");
    }
	public static  int GetTime() {
		// TODO Auto-generated method stub
		return CPU.time;
	}
	public static  int GetIR() {
		// TODO Auto-generated method stub
		return CPU.IR;
	}
	public static  int GetPSW() {
		// TODO Auto-generated method stub
		return CPU.PSW;
	}
	public static  boolean GetCPUState() {
		// TODO Auto-generated method stub
		return CPU.CPUState;
	}
    public static void SetPC(int pc) {
    	PC=pc;
    }
    public static void SetIR(int ir) {
    	IR=ir;
    }
    public static void SetPSW(int psw) {
    	PSW=psw;
    }
    public static void SetCPUState(boolean cpustate) {
    	CPUState=cpustate;
    }
    public static void SetCPUCoreState(boolean cpucorestate) {
    	CPUCoreState=cpucorestate;
    }
	public static void GOTOCORE(boolean b) {      //切换到核心态，将当前CPU内容保存
		// TODO Auto-generated method stub
		if(b) {
		CPU.SetCPUCoreState(true);
		ProtectCPU[0]=IR;
		ProtectCPU[1]=PC;
	    ProtectCPU[2]=PSW;
	    GUI.textField_8.setText("核心态");
		}
	}
	public static void GOTOUSER(boolean b) {      //切换到用户态，将内容还原到CPU
		// TODO Auto-generated method stub
		if(b)
		{
		IR=ProtectCPU[0];
		PC=ProtectCPU[1];
		PSW=ProtectCPU[2];
		CPU.SetCPUCoreState(false);
		 GUI.textField_8.setText("用户态");
	}}
	public static boolean WriteFile(Process process)  //操作系统把内存缓存区写出
	{
		int data=Memory.RangeBuffIn();  //读到缓存区
		int HLoc=Memory.BuffOutDisk();
		Harddisk.superwrite(HLoc,1);  //写入指定位置的外存
		int ActualLoc=MMU.visitMMU(process.getaddress(),process.getMemoryLoc());
		GUI.textArea.append(CPU.GetTime()+":[入缓冲区:进程编号:"+process.getProID()+"指令段编号，类型 5，磁盘文件写操作函数:逻辑地址"+process.getaddress()+" 物理地址"+ActualLoc+" 缓冲区地址"
				+data+" 外存物理块地址"+HLoc+"]"+"\n");
		//write PCB data
		return true;
	}
	public static boolean ReadFile(Process process)      //操作系统把内存缓存读出
	{
		int HLoc=Harddisk.write(1);
		Harddisk.read(HLoc, 1);  
		int data=Memory.RangeBuffIn();   //从外存读到缓存区
		int ActualLoc=MMU.visitMMU(process.getaddress(),process.getMemoryLoc());
		GUI.textArea.append(CPU.GetTime()+":[入缓冲区:进程编号:"+process.getProID()+"指令段编号，类型 4，磁盘文件读操作函数:逻辑地址"+process.getaddress()+" 物理地址"+ActualLoc+" 缓冲区地址"
				+data+" 外存物理块地址"+HLoc+"]"+"\n");
		Memory.BuffOutPCB();    //写入PCB
		return true;
	}
	public static void DOSOMETHING() {
		// TODO Auto-generated method stub 进入CPU代表指令成功运行
		CPU.loadingprocess.Runing();  //更新进程运行时间\时间片\IR\PC
		CPU.SetIR(CPU.loadingprocess.getIR());   //装载CPU的IR
		int ActualLoc=MMU.visitMMU(CPU.loadingprocess.getaddress(),CPU.loadingprocess.getMemoryLoc()); //获得物理地址
		System.out.println("Loc="+ActualLoc);
		if(ActualLoc<0)  //缺页中断
		//GUI
		{
			CPU.loadingprocess.pageplus();
			CPU.GOTOCORE(true);
	        System.out.println("NO PAGE!!");
	        Harddisk.read(CPU.loadingprocess.getHarddiskLoc(), CPU.loadingprocess.getaddress());
	        System.out.println("visitharddisk="+(CPU.loadingprocess.getHarddiskLoc()+CPU.loadingprocess.getaddress()));
	        ActualLoc=MMU.update(CPU.loadingprocess.getaddress(),CPU.loadingprocess.getMemoryLoc());
	        System.out.println("MemoryLoc="+ActualLoc);
	        CPU.GOTOUSER(true);
	           
		}
		Memory.visit(ActualLoc);  //访问内存
		int flag=CPU.loadingprocess.GetIns().get(CPU.loadingprocess.getPC()).getInRunTimes();  //根据指令执行时间
		System.out.println("CPU is doing"+"ProcessID::"+CPU.loadingprocess.getProID()+"Insturctions::"
				+(CPU.loadingprocess.getPC()+1)+"InstructionsType"+CPU.GetIR()+"InRunTime!"+flag);
		GUI.textArea.append(CPU.GetTime()+":[运行进程:进程编号:"+CPU.loadingprocess.getProID()+" 指令段编号"+(CPU.loadingprocess.getPC()+1)
				+",指令类型编号:"+CPU.GetIR()+",指令类型说明:逻辑地址"+CPU.loadingprocess.getaddress()+",物理地址"+ActualLoc+",运行时长"
						+flag+"]\n");
		GUI.textField_5.setText("用户态");
		GUI.textField_6.setText(String.valueOf(CPU.loadingprocess.getPC()+1)+"/"+String.valueOf(CPU.GetIR()));
		switch(CPU.IR)  //根据指令类型执行不同操作
		{
		case 0: CPU.loadingprocess.RuningIR0(flag);break;
		case 1: CPU.loadingprocess.RuningIR0(flag);break;
		case 2:CPU.GOTOCORE(true); doing2();CPU.GOTOUSER(true);CPU.SetCPUState(false);break;
		case 3:CPU.GOTOCORE(true); doing3();CPU.GOTOUSER(true);CPU.SetCPUState(false);break;
		case 4:CPU.SetInturrupt(); doing4();CPU.SetCPUState(false);CPU.SetInturrupt();break;
		case 5:CPU.SetInturrupt(); doing5();CPU.SetCPUState(false);CPU.SetInturrupt();break;
		default:break;
		}
	}
	public static void doing2() {          //针对指令2的特有的判断阻塞
		if(KeyBoard.If())
		    CPU.loadingprocess.BlockProcess();
		else
		{
			KeyBoard.UseThis(CPU.loadingprocess);
		    CPU.loadingprocess.BlockProcess();}
	}
	public static void doing3() {
		if(Screen.If())
		    CPU.loadingprocess.BlockProcess();
		else
		{
			Screen.UseThis(CPU.loadingprocess);
		    CPU.loadingprocess.BlockProcess();}
	}
	public static void doing4() {
		if(Memory.Buffer)
		    CPU.loadingprocess.BlockProcess();
		else
		{
			ReadFile.UseThis(CPU.loadingprocess);
		    CPU.loadingprocess.BlockProcess();}
	}
	public static void doing5() {
		if(Memory.Buffer)
		    CPU.loadingprocess.BlockProcess();
		else
		{
			WriteFile.UseThis(CPU.loadingprocess);
		    CPU.loadingprocess.BlockProcess();}
	}
	
}