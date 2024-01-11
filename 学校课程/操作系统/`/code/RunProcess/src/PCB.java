import java.util.Collections;
import java.util.LinkedList;

public class PCB implements Comparable<PCB>{
	private int ProID; //进程编号
	private int Priority; //进程优先数
	private int InTimes; //进程创建时间
	private int EndTimes; //进程结束时间
	private int PSW; //进程状态0：未知 1：运行：2：就绪 3：阻塞
	private int Runtimes; //进程运行时间列表
	private int TurnTime; //进程周转时间统计
	private int InstrucNum; //进程包含的指令数目
	private int PC;  //程序计数器信息，记录下一条指令地址
	private int IR;  //指令计数器信息，记录当前执行的指令类型
	private int RqNum;  //就绪队列所处位置
	private int Rqtimes;  //就绪队列进入时间
	private int BqNum1; //阻塞队列1所处位置
	private int BqTimes1; //阻塞队列1进入时间
	private int BqNum2;  //阻塞队列2所处位置
	private int BqTimes2; //阻塞队列2进入时间
	private int BqNum3;  //阻塞队列3所处位置
	private int BqTimes3;  //阻塞队列3进入时间
	private int BqNum4;  //阻塞队列4所处位置
	private int BqTimes4;  //阻塞队列4进入时间
	private int BqNum5;  //阻塞队列5所处位置
	private int BqTimes5;  //阻塞队列5进入时间
	private int HarddiskLoc;  //外存地址
	private int MemoryLoc=0;    //内存起地址
	private int L_Address;    //偏移地址
	private int MPageCOunt=0;
	private int[] supercount=new int[100];  //针对0、1指令独有的计时
	private static LinkedList<PCB> ReadyQuene= new LinkedList<PCB>(); //就绪队列
	private static LinkedList<PCB> BlockedQuene1= new LinkedList<PCB>(); //就绪队列
	private static LinkedList<PCB> BlockedQuene2= new LinkedList<PCB>(); //就绪队列
	private static LinkedList<PCB> BlockedQuene3= new LinkedList<PCB>(); //就绪队列
	private static LinkedList<PCB> BlockedQuene4= new LinkedList<PCB>(); //就绪队列
	private static LinkedList<PCB> BlockedQuene5= new LinkedList<PCB>(); //就绪队列
	private int timesliceleft; //当前进程在cpu运行的剩余时间片秒数
	private static LinkedList<PCB> ProcessList=new LinkedList<PCB>(); //存储所有PCB
	public static int number=0;  //记录完成的进程
	@Override
	public int compareTo(PCB arg0) {  //便于使用collection的sort函数
		// TODO Auto-generated method stub
		if(this.Priority>arg0.Priority)
		return 1;
		else if(this.Priority<arg0.Priority)
		return -1;
		else 
		return 0;
	}
	public PCB(int ProID,int Priority,int InTimes,int InstrucNum,int memloc)
	{
		boolean iff=true;
		for(PCB e:ProcessList)
			if(ProID==e.getProID())
			{
				iff=false;
			}
		if(iff&&ProcessList.size()<=30) //限制最大进程数量为30
		CreateProcess(ProID,Priority,InTimes,InstrucNum,memloc);
	}
	public void setHarddiskLoc(int loc)
	{
		this.HarddiskLoc=loc;
	}
	public void setMemoryLoc(int loc)
	{
		this.MemoryLoc=loc;
	}
    protected synchronized void CreateProcess(int ProID,int Priority,int InTimes,int InstrucNum,int Memloc)
    {
    	//创建原语
    	this.ProID=ProID;
    	this.Priority=Priority;
    	this.InTimes=CPU.GetTime();
    	this.InstrucNum=InstrucNum;
    	this.Runtimes=0;
    	this.TurnTime=0;
    	this.PSW=2;
    	this.timesliceleft=0;
    	this.Rqtimes=CPU.GetTime();
    	this.BqNum1=-1;
    	this.BqTimes1=0;
    	this.BqNum2=-1;
    	this.BqTimes2=0;
    	this.BqNum3=-1;
    	this.BqTimes3=0;
    	this.PC=0;
    	this.IR=0;
        this.MemoryLoc=Memloc;
    	ReadyQuene.offer(this);
    	this.RqNum=ReadyQuene.indexOf(this);
    	ProcessList.add(this);
    	
    	//GUI........
    	
    	System.out.println("ProcessCreate!! ProcessID "+ProID+"Priority "+Priority+"InTimes "+InTimes+"InstruNum "+InstrucNum+""
    			);
    	GUI.textArea.append(CPU.GetTime()+":[创建进程:PCB内存块始地址"+this.MemoryLoc+"]"+"\n");
    	GUI.textArea_1.append("创建进程!! ProcessID "+String.valueOf(ProID)+"Priority "+String.valueOf(Priority)+"InTimes "+String.valueOf(InTimes)+"InstruNum "+String.valueOf(InstrucNum)+"\n");
    }
	public synchronized void CancelProcess() {  //撤销进程
		this.PSW=0;
		this.TurnTime=CPU.GetTime()-this.InTimes+1;
		Memory.FreeMemory(this.MemoryLoc);
		ProcessList.remove(this);
		ReadyQuene.remove(this);
		Process.GetProcessRoom().remove(pcbTOprocess(this));
		
		
		//Process...........
		//GUI.........
		number++;
		System.out.println("ProcessDONE!! ProcessID "+ProID+"TurnTime "+TurnTime+"InstruNum "+InstrucNum);
		GUI.textArea.append(CPU.GetTime()+":[终止进程编号"+ProID+"]"+"\n");
		GUI.textArea_1.append("终止作业!! ProcessID "+String.valueOf(ProID)+"Turntime "+String.valueOf(TurnTime)+"InstruNum "+String.valueOf(InstrucNum)+"\n");
	}
	public synchronized  void BlockProcess() {   //阻塞进程
		
		this.PSW=3;
		this.timesliceleft=0;
		switch(this.IR) {
		case 2: joinBQ1(this); break;
		case 3: joinBQ2(this); break;
		case 4: joinBQ3(this); break;
		case 5: joinBQ4(this); break;
		default: break;
		}
		
		GUI.textArea.append(CPU.GetTime()+":[阻塞进程:"+"进程编号:"+ProID+" 进入阻塞队列编号"+(this.IR-1)+"]\n");
		}
		
	public void joinBQ1(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene1.offer(pcb);
		pcb.BqNum1=BlockedQuene1.indexOf(pcb);
		pcb.BqTimes1=CPU.GetTime();
	}
	public void joinBQ2(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene2.offer(pcb);
		pcb.BqNum2=BlockedQuene2.indexOf(pcb);
		pcb.BqTimes2=CPU.GetTime();
	}
	public void joinBQ3(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene3.offer(pcb);
		pcb.BqNum3=BlockedQuene3.indexOf(pcb);
		pcb.BqTimes3=CPU.GetTime();
	}
	public void joinBQ4(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene4.offer(pcb);
		pcb.BqNum4=BlockedQuene4.indexOf(pcb);
		pcb.BqTimes4=CPU.GetTime();
	}
	public void joinBQ5(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene5.offer(pcb);
		pcb.BqNum5=BlockedQuene5.indexOf(pcb);
		pcb.BqTimes5=CPU.GetTime();
	}
	public  synchronized void AwakeProcess() { //唤醒进程
		this.PSW=2;
		switch(this.IR) {
		case 2: OutBQ1(this); break;
		case 3: OutBQ2(this); break;
		case 4: OutBQ3(this); break;
		case 5: OutBQ4(this); break;
		default: break;
	}
		ReadyQuene.offer(this);
		this.Rqtimes=CPU.GetTime();
		Collections.sort(ReadyQuene);
		this.RqNum=ReadyQuene.indexOf(this);
		GUI.textArea.append(CPU.GetTime()+":[重新进入就绪队列:"+"进程编号:"+ProID+"]\n");
		
}
	public void OutBQ5(PCB pcb) {  //有参数则将阻塞队列中列头排出
		// TODO Auto-generated method stub
		BlockedQuene5.poll();
		for(PCB e:BlockedQuene5)
			e.BqNum5=BlockedQuene5.indexOf(e);
			pcb.BqNum5=-1;
	}
	public void OutBQ4(PCB pcb) {  //有参数则将阻塞队列中列头排出
		// TODO Auto-generated method stub
		BlockedQuene4.poll();
		for(PCB e:BlockedQuene4)
			e.BqNum4=BlockedQuene4.indexOf(e);
			pcb.BqNum4=-1;
	}
	public void OutBQ3(PCB pcb) {  //有参数则将阻塞队列中列头排出
		// TODO Auto-generated method stub
		BlockedQuene3.poll();
		for(PCB e:BlockedQuene3)
			e.BqNum3=BlockedQuene3.indexOf(e);
			pcb.BqNum3=-1;
	}
	public void OutBQ2(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene2.poll();
		for(PCB e:BlockedQuene2)
			e.BqNum2=BlockedQuene2.indexOf(e);
		pcb.BqNum2=-1;
	}
	public void OutBQ1(PCB pcb) {
		// TODO Auto-generated method stub
		BlockedQuene1.poll();
		for(PCB e:BlockedQuene1)
			e.BqNum1=BlockedQuene1.indexOf(e);
		pcb.BqNum1=-1;
	}
	public static void SortRqQuene() {   //优先级排列
		Collections.sort(ReadyQuene);
	}
	public int getProID ()
	{
		return this.ProID;
	}
	public int getPriority ()
	{
		return this.Priority;
	}
	
	public int getInTimes ()
	{
		return this.InTimes;
	}
	public int getEndTimes ()
	{
		return this.EndTimes;
	}
	public int getPSW ()
	{
		return this.PSW;
	}
	public int getRuntimes ()
	{
		return this.Runtimes;
	}
	public int getTurnTime ()
	{
		return this.TurnTime;
	}
	public int getInstrucNum ()
	{
		return this.InstrucNum;
	}
	public int getPC ()
	{
		return this.PC;
	}
	public int getIR ()
	{
		return this.IR;
	}
	
	public int getRqNum ()
	{
		return this.RqNum;
	}
	public int getMemoryLoc()
	{
		return this.MemoryLoc;
	}
	public int getHarddiskLoc()
	{
		return this.HarddiskLoc;
	}
	public int getRqtimes ()
	{
		return this.Rqtimes;
	}
	public int getBqNum1 ()
	{
		return this.BqNum1;
	}
	public int getBqTimes1 ()
	{
		return this.BqTimes1;
	}
	public int getBqNum2 ()
	{
		return this.BqNum2;
	}
	public int getBqTimes2 ()
	{
		return this.BqTimes2;
	}
	public int setBqNum3 ()
	{
		return this.BqNum3;
	}
	public int getBqTimes3 ()
	{
		return  this.BqTimes3;
	}
	public void setProID (int ProID)
	{
		this.ProID=ProID;
	}
	public void setPriority (int Priority)
	{
		this.Priority=Priority;
	}
	
	public void setInTimes (int InTimes)
	{
		this.InTimes=InTimes;
	}
	public void setEndTimes (int EndTimes)
	{
		this.EndTimes=EndTimes;
	}
	public void setPSW (int PSW)
	{
		this.PSW=PSW;
	}
	public void setRuntimes (int Runtimes)
	{
		this.Runtimes=Runtimes;
	}
	public void setTurnTime (int TurnTime)
	{
		this.TurnTime=TurnTime;
	}
	public void setInstrucNum (int InstrucNum)
	{
		this.InstrucNum=InstrucNum;
	}
	public void setPC (int PC)
	{
		this.PC=PC;
	}
	public void setIR (int IR)
	{
		this.IR=IR;
	}
	
	public void setRqNum (int RqNum)
	{
		this.RqNum=RqNum;
	}
	public void setRqtimes (int Rqtimes)
	{
		this.Rqtimes=Rqtimes;
	}
	public void setBqNum1 (int BqNum1)
	{
		this.BqNum1=BqNum1;
	}
	public void setBqTimes1 (int BqTimes1)
	{
		this.BqTimes1=BqTimes1;
	}
	public void setBqNum2 (int BqNum2)
	{
		this.BqNum2=BqNum2;
	}
	public void setL_Add(int e)
	{
		this.L_Address=e;
	}
	public void setBqTimes2 (int BqTimes2)
	{
		this.BqTimes2=BqTimes2;
	}
	public void setBqNum3 (int BqNum3)
	{
		this.BqNum3=BqNum3;
	}
	public void setBqTimes3 (int BqTimes3)
	{
		this.BqTimes3=BqTimes3;
	}
	public int getaddress()
	{
		return this.L_Address;
	}
	public static PCB pollRQ() {       //用于进程进入运行，然后更新序号
		// TODO Auto-generated method stub
		PCB i=ReadyQuene.poll();
		for(PCB e:ReadyQuene)
			e.RqNum=ReadyQuene.indexOf(e);
		return i;
	}
	public void ResetTimeSlice() {   //resettime
		this.timesliceleft=2;
	}
	public static  Process pcbTOprocess(PCB pcb)  //用于process到pcb的转换
	{
		if(pcb==null)
			return null;
		for(Process e : Process.GetProcessRoom())
		{
			if(pcb.getProID()==e.getProID())
				return e;
		}
		return null;
	}
	public boolean iftimesliceleft()    //检查是否时间片还有剩余
	{
		if(this.timesliceleft!=0)
			return true;
		else return false;
	}
	public static void joinRqQuene(Process loadingprocess) {  //加入就绪队列，然后更新进入时间
		// TODO Auto-generated method stub
		ReadyQuene.offer(loadingprocess);
		loadingprocess.setRqtimes(CPU.GetTime());
		SortRqQuene();
	} 
	public void Runing() {  //初步运行指令
		Instructions temp=pcbTOprocess(this).GetIns().get(this.getPC());
    	this.setIR(temp.getins());
    	this.setL_Add(temp.getAddress());
		this.Runtimes++;
		this.timesliceleft--;
	} 
	public void RuningIR0(int b) {  
		//对于指令为0运行后pc加一，然后判断是否已完成
		 
		if(this.PC<  this.InstrucNum-1)
			{this.supercount[PC]++;     //this ins 检查第几次运行 保证运行时间正确 仅有0、1指令需要
			if(this.supercount[PC]==b)
			this.PC++; //next ins
    	CPU.SetCPUState(true);
    	}
		else {
			this.CancelProcess();
			CPU.SetCPUState(false);
		}
	}
	public static PCB pollBQ1() {    //退出阻塞队列，然后更新序号
		// TODO Auto-generated method stub
		PCB i=BlockedQuene1.poll();
		for(PCB e: BlockedQuene1)
			e.BqNum1=BlockedQuene1.indexOf(e);
		return i;
	}	
	public static PCB pollBQ2() {
		// TODO Auto-generated method stub
		PCB i=BlockedQuene2.poll();
		for(PCB e: BlockedQuene2)
			e.BqNum2=BlockedQuene2.indexOf(e);
		return i;
	}
	public static PCB pollBQ3() {
		// TODO Auto-generated method stub
		PCB i=BlockedQuene3.poll();
		for(PCB e: BlockedQuene3)
			e.BqNum3=BlockedQuene3.indexOf(e);
		return i;
	}
	public static PCB pollBQ4() {
		// TODO Auto-generated method stub
		PCB i=BlockedQuene4.poll();
		for(PCB e: BlockedQuene4)
			e.BqNum4=BlockedQuene4.indexOf(e);
		return i;
	}
	public static PCB pollBQ5() {
		// TODO Auto-generated method stub
		PCB i=BlockedQuene5.poll();
		for(PCB e: BlockedQuene5)
			e.BqNum5=BlockedQuene5.indexOf(e);
		return i;
	}
	public  void pageplus() {
		this.MPageCOunt++;
	}
	public static PCB OutBQ5() {   //不带参数代表只返回队列的头
		// TODO Auto-generated method stub
		return BlockedQuene5.peek();
	}
	public static PCB OutBQ4() {   //不带参数代表只返回队列的头
		// TODO Auto-generated method stub
		return BlockedQuene4.peek();
	}
	public static PCB OutBQ3() {   //不带参数代表只返回队列的头
		// TODO Auto-generated method stub
		return BlockedQuene3.peek();
	}
	public static PCB OutBQ2() {   //不带参数代表只返回队列的头
		// TODO Auto-generated method stub
		return BlockedQuene2.peek();
	}	

	public static PCB OutBQ1() {   //不带参数代表只返回队列的头
		// TODO Auto-generated method stub
		return BlockedQuene1.peek();
	}
	public void RuningIRelse(boolean b)   //作为资源类执行指令不改变CPU状态
	{
		if(b)
		if (this.PC < this.InstrucNum-1)
			{this.PC++;
		}
		else
		this.CancelProcess();
	} 
	public void CHECKIFDONEANDDO()  //对于处于资源中的进程判断是否需要终止
	{    if(this.PC==this.InstrucNum-1)
	{
		this.CancelProcess();
		 CPU.SetCPUState(false);
	}
	}
    public static PCB ChooseBQout(int number)   //返回阻塞队列的队头，但不进行poll
    {
    	switch(number)
    	{
    	case 1: return BlockedQuene1.peek();
    	case 2: return BlockedQuene2.peek();
    	case 3:	return BlockedQuene3.peek();
    	case 4:	return BlockedQuene4.peek();
    	case 5:	return BlockedQuene5.peek();
    	default:break;
    	}
		return null;
    }
    public static void ShowQuene() {   //输出队列中的进程
    	String temp=new String();
    	String temp1=new String();
    	String temp2=new String();
    	String temp3=new String();
    	String temp4=new String();
    	String temp5=new String();
    	String temp123=new String();
    	String temp1123=new String();
    	String temp2123=new String();
    	String temp3123=new String();
    	String temp4123=new String();
    	String temp5123=new String();
    	for(PCB e:ReadyQuene)
    	{
    		temp=temp+" 进入时间"+e.Rqtimes+"=进程编号"+e.getProID()+";";
    		temp123=temp123+"进程"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[就绪队列1"+temp+"]"+"\n");
    	GUI.textField.setText(temp123);
    	
    	for(PCB e:BlockedQuene1)
    	{
    		temp1=temp1+" 进入时间"+e.Rqtimes+"=进程编号"+e.getProID()+";";
    		temp1123=temp1123+"进程"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[阻塞队列1"+temp1+"]"+"\n");
    	GUI.textField_1.setText(temp1123);
    	
    	for(PCB e:BlockedQuene2)
    	{
    		temp2=temp2+" 进入时间"+e.Rqtimes+"=进程编号"+e.getProID()+";";
    		temp2123=temp2123+"进程"+e.getProID()+";";
    	}
    	GUI.textField_2.setText(temp2123);
    	GUI.textArea.append(CPU.GetTime()+":[阻塞队列2"+temp2+"]"+"\n");
    	
    	for(PCB e:BlockedQuene3)
    	{
    		temp3=temp3+" 进入时间"+e.Rqtimes+"=进程编号"+e.getProID()+";";
    		temp3123=temp3123+"进程"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[阻塞队列3"+temp3+"]"+"\n");
    	GUI.textField_3.setText(temp3123);
    	
    	for(PCB e:BlockedQuene4)
    	{
    		temp4=temp4+" 进入时间"+e.Rqtimes+"=进程编号"+e.getProID()+";";
    		temp4123=temp4123+"进程"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[阻塞队列4"+temp4+"]"+"\n");
    	GUI.textField_9.setText(temp4123);
    	
    	for(PCB e:BlockedQuene5)
    	{
    		temp5=temp5+" 进入时间"+e.Rqtimes+"=进程编号"+e.getProID()+";";
    	}
    	
    	//GUI.textArea.append(CPU.GetTime()+":[阻塞队列3"+temp3+"]"+"\n");
    	//GUI.textField_3.setText(temp3);
    	System.out.println("ready"+temp);
    	System.out.println("b1"+temp1);
    	System.out.println("b2"+temp2);
    	System.out.println("b3"+temp3);
    	System.out.println("b4"+temp4);
    	//System.out.println("b5"+temp5);
    }
    
	



	
}
