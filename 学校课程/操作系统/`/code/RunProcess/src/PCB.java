import java.util.Collections;
import java.util.LinkedList;

public class PCB implements Comparable<PCB>{
	private int ProID; //���̱��
	private int Priority; //����������
	private int InTimes; //���̴���ʱ��
	private int EndTimes; //���̽���ʱ��
	private int PSW; //����״̬0��δ֪ 1�����У�2������ 3������
	private int Runtimes; //��������ʱ���б�
	private int TurnTime; //������תʱ��ͳ��
	private int InstrucNum; //���̰�����ָ����Ŀ
	private int PC;  //�����������Ϣ����¼��һ��ָ���ַ
	private int IR;  //ָ���������Ϣ����¼��ǰִ�е�ָ������
	private int RqNum;  //������������λ��
	private int Rqtimes;  //�������н���ʱ��
	private int BqNum1; //��������1����λ��
	private int BqTimes1; //��������1����ʱ��
	private int BqNum2;  //��������2����λ��
	private int BqTimes2; //��������2����ʱ��
	private int BqNum3;  //��������3����λ��
	private int BqTimes3;  //��������3����ʱ��
	private int BqNum4;  //��������4����λ��
	private int BqTimes4;  //��������4����ʱ��
	private int BqNum5;  //��������5����λ��
	private int BqTimes5;  //��������5����ʱ��
	private int HarddiskLoc;  //����ַ
	private int MemoryLoc=0;    //�ڴ����ַ
	private int L_Address;    //ƫ�Ƶ�ַ
	private int MPageCOunt=0;
	private int[] supercount=new int[100];  //���0��1ָ����еļ�ʱ
	private static LinkedList<PCB> ReadyQuene= new LinkedList<PCB>(); //��������
	private static LinkedList<PCB> BlockedQuene1= new LinkedList<PCB>(); //��������
	private static LinkedList<PCB> BlockedQuene2= new LinkedList<PCB>(); //��������
	private static LinkedList<PCB> BlockedQuene3= new LinkedList<PCB>(); //��������
	private static LinkedList<PCB> BlockedQuene4= new LinkedList<PCB>(); //��������
	private static LinkedList<PCB> BlockedQuene5= new LinkedList<PCB>(); //��������
	private int timesliceleft; //��ǰ������cpu���е�ʣ��ʱ��Ƭ����
	private static LinkedList<PCB> ProcessList=new LinkedList<PCB>(); //�洢����PCB
	public static int number=0;  //��¼��ɵĽ���
	@Override
	public int compareTo(PCB arg0) {  //����ʹ��collection��sort����
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
		if(iff&&ProcessList.size()<=30) //��������������Ϊ30
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
    	//����ԭ��
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
    	GUI.textArea.append(CPU.GetTime()+":[��������:PCB�ڴ��ʼ��ַ"+this.MemoryLoc+"]"+"\n");
    	GUI.textArea_1.append("��������!! ProcessID "+String.valueOf(ProID)+"Priority "+String.valueOf(Priority)+"InTimes "+String.valueOf(InTimes)+"InstruNum "+String.valueOf(InstrucNum)+"\n");
    }
	public synchronized void CancelProcess() {  //��������
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
		GUI.textArea.append(CPU.GetTime()+":[��ֹ���̱��"+ProID+"]"+"\n");
		GUI.textArea_1.append("��ֹ��ҵ!! ProcessID "+String.valueOf(ProID)+"Turntime "+String.valueOf(TurnTime)+"InstruNum "+String.valueOf(InstrucNum)+"\n");
	}
	public synchronized  void BlockProcess() {   //��������
		
		this.PSW=3;
		this.timesliceleft=0;
		switch(this.IR) {
		case 2: joinBQ1(this); break;
		case 3: joinBQ2(this); break;
		case 4: joinBQ3(this); break;
		case 5: joinBQ4(this); break;
		default: break;
		}
		
		GUI.textArea.append(CPU.GetTime()+":[��������:"+"���̱��:"+ProID+" �����������б��"+(this.IR-1)+"]\n");
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
	public  synchronized void AwakeProcess() { //���ѽ���
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
		GUI.textArea.append(CPU.GetTime()+":[���½����������:"+"���̱��:"+ProID+"]\n");
		
}
	public void OutBQ5(PCB pcb) {  //�в�����������������ͷ�ų�
		// TODO Auto-generated method stub
		BlockedQuene5.poll();
		for(PCB e:BlockedQuene5)
			e.BqNum5=BlockedQuene5.indexOf(e);
			pcb.BqNum5=-1;
	}
	public void OutBQ4(PCB pcb) {  //�в�����������������ͷ�ų�
		// TODO Auto-generated method stub
		BlockedQuene4.poll();
		for(PCB e:BlockedQuene4)
			e.BqNum4=BlockedQuene4.indexOf(e);
			pcb.BqNum4=-1;
	}
	public void OutBQ3(PCB pcb) {  //�в�����������������ͷ�ų�
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
	public static void SortRqQuene() {   //���ȼ�����
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
	public static PCB pollRQ() {       //���ڽ��̽������У�Ȼ��������
		// TODO Auto-generated method stub
		PCB i=ReadyQuene.poll();
		for(PCB e:ReadyQuene)
			e.RqNum=ReadyQuene.indexOf(e);
		return i;
	}
	public void ResetTimeSlice() {   //resettime
		this.timesliceleft=2;
	}
	public static  Process pcbTOprocess(PCB pcb)  //����process��pcb��ת��
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
	public boolean iftimesliceleft()    //����Ƿ�ʱ��Ƭ����ʣ��
	{
		if(this.timesliceleft!=0)
			return true;
		else return false;
	}
	public static void joinRqQuene(Process loadingprocess) {  //����������У�Ȼ����½���ʱ��
		// TODO Auto-generated method stub
		ReadyQuene.offer(loadingprocess);
		loadingprocess.setRqtimes(CPU.GetTime());
		SortRqQuene();
	} 
	public void Runing() {  //��������ָ��
		Instructions temp=pcbTOprocess(this).GetIns().get(this.getPC());
    	this.setIR(temp.getins());
    	this.setL_Add(temp.getAddress());
		this.Runtimes++;
		this.timesliceleft--;
	} 
	public void RuningIR0(int b) {  
		//����ָ��Ϊ0���к�pc��һ��Ȼ���ж��Ƿ������
		 
		if(this.PC<  this.InstrucNum-1)
			{this.supercount[PC]++;     //this ins ���ڼ������� ��֤����ʱ����ȷ ����0��1ָ����Ҫ
			if(this.supercount[PC]==b)
			this.PC++; //next ins
    	CPU.SetCPUState(true);
    	}
		else {
			this.CancelProcess();
			CPU.SetCPUState(false);
		}
	}
	public static PCB pollBQ1() {    //�˳��������У�Ȼ��������
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
	public static PCB OutBQ5() {   //������������ֻ���ض��е�ͷ
		// TODO Auto-generated method stub
		return BlockedQuene5.peek();
	}
	public static PCB OutBQ4() {   //������������ֻ���ض��е�ͷ
		// TODO Auto-generated method stub
		return BlockedQuene4.peek();
	}
	public static PCB OutBQ3() {   //������������ֻ���ض��е�ͷ
		// TODO Auto-generated method stub
		return BlockedQuene3.peek();
	}
	public static PCB OutBQ2() {   //������������ֻ���ض��е�ͷ
		// TODO Auto-generated method stub
		return BlockedQuene2.peek();
	}	

	public static PCB OutBQ1() {   //������������ֻ���ض��е�ͷ
		// TODO Auto-generated method stub
		return BlockedQuene1.peek();
	}
	public void RuningIRelse(boolean b)   //��Ϊ��Դ��ִ��ָ��ı�CPU״̬
	{
		if(b)
		if (this.PC < this.InstrucNum-1)
			{this.PC++;
		}
		else
		this.CancelProcess();
	} 
	public void CHECKIFDONEANDDO()  //���ڴ�����Դ�еĽ����ж��Ƿ���Ҫ��ֹ
	{    if(this.PC==this.InstrucNum-1)
	{
		this.CancelProcess();
		 CPU.SetCPUState(false);
	}
	}
    public static PCB ChooseBQout(int number)   //�����������еĶ�ͷ����������poll
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
    public static void ShowQuene() {   //��������еĽ���
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
    		temp=temp+" ����ʱ��"+e.Rqtimes+"=���̱��"+e.getProID()+";";
    		temp123=temp123+"����"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[��������1"+temp+"]"+"\n");
    	GUI.textField.setText(temp123);
    	
    	for(PCB e:BlockedQuene1)
    	{
    		temp1=temp1+" ����ʱ��"+e.Rqtimes+"=���̱��"+e.getProID()+";";
    		temp1123=temp1123+"����"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[��������1"+temp1+"]"+"\n");
    	GUI.textField_1.setText(temp1123);
    	
    	for(PCB e:BlockedQuene2)
    	{
    		temp2=temp2+" ����ʱ��"+e.Rqtimes+"=���̱��"+e.getProID()+";";
    		temp2123=temp2123+"����"+e.getProID()+";";
    	}
    	GUI.textField_2.setText(temp2123);
    	GUI.textArea.append(CPU.GetTime()+":[��������2"+temp2+"]"+"\n");
    	
    	for(PCB e:BlockedQuene3)
    	{
    		temp3=temp3+" ����ʱ��"+e.Rqtimes+"=���̱��"+e.getProID()+";";
    		temp3123=temp3123+"����"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[��������3"+temp3+"]"+"\n");
    	GUI.textField_3.setText(temp3123);
    	
    	for(PCB e:BlockedQuene4)
    	{
    		temp4=temp4+" ����ʱ��"+e.Rqtimes+"=���̱��"+e.getProID()+";";
    		temp4123=temp4123+"����"+e.getProID()+";";
    	}
    	
    	GUI.textArea.append(CPU.GetTime()+":[��������4"+temp4+"]"+"\n");
    	GUI.textField_9.setText(temp4123);
    	
    	for(PCB e:BlockedQuene5)
    	{
    		temp5=temp5+" ����ʱ��"+e.Rqtimes+"=���̱��"+e.getProID()+";";
    	}
    	
    	//GUI.textArea.append(CPU.GetTime()+":[��������3"+temp3+"]"+"\n");
    	//GUI.textField_3.setText(temp3);
    	System.out.println("ready"+temp);
    	System.out.println("b1"+temp1);
    	System.out.println("b2"+temp2);
    	System.out.println("b3"+temp3);
    	System.out.println("b4"+temp4);
    	//System.out.println("b5"+temp5);
    }
    
	



	
}
