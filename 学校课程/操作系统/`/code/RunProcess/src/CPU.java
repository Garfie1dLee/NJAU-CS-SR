import java.util.Stack;

public class CPU {
	private static int PC; //����CPU�е����洢Ϊ��̬����PC���洢��ǰָ��ĵ�ַ
	private static int IR; //����CPU�е����洢Ϊ��̬����IR���洢��ǰָ�������
	private static int PSW; //�洢��ǰִ�еĽ��̵�״̬
    private static  int time=0;  //�洢CPU����ʱ��
    private static boolean CPUState=false;  //CPU״̬Ĭ��Ϊ�ر�
    private static boolean CPUCoreState= false;  //false����CPU�����û�̬��trueΪ����̬
    private static boolean CPUInterrupt=false;   //���ж�flag
    static boolean DoMP=false;  //domp
    static Process loadingprocess= null;    //�洢ΪCPU��ʱ�����صĽ��̣��ý���ֻ����CPU��
    static int[] ProtectCPU= new int[3];  //������ʱ�洢CPU��PC��IR��PSW
    static void SetInturrupt()  //�л����ж�
    {
    	CPUInterrupt=!CPUInterrupt;
    }
    static void plustime()  //CPU����ʱ���һ
    {  CPU.time=CPU.time+1;
    }
    static void PrintTime() {  //���CPU������ʱ��
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
	public static void GOTOCORE(boolean b) {      //�л�������̬������ǰCPU���ݱ���
		// TODO Auto-generated method stub
		if(b) {
		CPU.SetCPUCoreState(true);
		ProtectCPU[0]=IR;
		ProtectCPU[1]=PC;
	    ProtectCPU[2]=PSW;
	    GUI.textField_8.setText("����̬");
		}
	}
	public static void GOTOUSER(boolean b) {      //�л����û�̬�������ݻ�ԭ��CPU
		// TODO Auto-generated method stub
		if(b)
		{
		IR=ProtectCPU[0];
		PC=ProtectCPU[1];
		PSW=ProtectCPU[2];
		CPU.SetCPUCoreState(false);
		 GUI.textField_8.setText("�û�̬");
	}}
	public static boolean WriteFile(Process process)  //����ϵͳ���ڴ滺����д��
	{
		int data=Memory.RangeBuffIn();  //����������
		int HLoc=Memory.BuffOutDisk();
		Harddisk.superwrite(HLoc,1);  //д��ָ��λ�õ����
		int ActualLoc=MMU.visitMMU(process.getaddress(),process.getMemoryLoc());
		GUI.textArea.append(CPU.GetTime()+":[�뻺����:���̱��:"+process.getProID()+"ָ��α�ţ����� 5�������ļ�д��������:�߼���ַ"+process.getaddress()+" �����ַ"+ActualLoc+" ��������ַ"
				+data+" ���������ַ"+HLoc+"]"+"\n");
		//write PCB data
		return true;
	}
	public static boolean ReadFile(Process process)      //����ϵͳ���ڴ滺�����
	{
		int HLoc=Harddisk.write(1);
		Harddisk.read(HLoc, 1);  
		int data=Memory.RangeBuffIn();   //��������������
		int ActualLoc=MMU.visitMMU(process.getaddress(),process.getMemoryLoc());
		GUI.textArea.append(CPU.GetTime()+":[�뻺����:���̱��:"+process.getProID()+"ָ��α�ţ����� 4�������ļ�����������:�߼���ַ"+process.getaddress()+" �����ַ"+ActualLoc+" ��������ַ"
				+data+" ���������ַ"+HLoc+"]"+"\n");
		Memory.BuffOutPCB();    //д��PCB
		return true;
	}
	public static void DOSOMETHING() {
		// TODO Auto-generated method stub ����CPU����ָ��ɹ�����
		CPU.loadingprocess.Runing();  //���½�������ʱ��\ʱ��Ƭ\IR\PC
		CPU.SetIR(CPU.loadingprocess.getIR());   //װ��CPU��IR
		int ActualLoc=MMU.visitMMU(CPU.loadingprocess.getaddress(),CPU.loadingprocess.getMemoryLoc()); //��������ַ
		System.out.println("Loc="+ActualLoc);
		if(ActualLoc<0)  //ȱҳ�ж�
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
		Memory.visit(ActualLoc);  //�����ڴ�
		int flag=CPU.loadingprocess.GetIns().get(CPU.loadingprocess.getPC()).getInRunTimes();  //����ָ��ִ��ʱ��
		System.out.println("CPU is doing"+"ProcessID::"+CPU.loadingprocess.getProID()+"Insturctions::"
				+(CPU.loadingprocess.getPC()+1)+"InstructionsType"+CPU.GetIR()+"InRunTime!"+flag);
		GUI.textArea.append(CPU.GetTime()+":[���н���:���̱��:"+CPU.loadingprocess.getProID()+" ָ��α��"+(CPU.loadingprocess.getPC()+1)
				+",ָ�����ͱ��:"+CPU.GetIR()+",ָ������˵��:�߼���ַ"+CPU.loadingprocess.getaddress()+",�����ַ"+ActualLoc+",����ʱ��"
						+flag+"]\n");
		GUI.textField_5.setText("�û�̬");
		GUI.textField_6.setText(String.valueOf(CPU.loadingprocess.getPC()+1)+"/"+String.valueOf(CPU.GetIR()));
		switch(CPU.IR)  //����ָ������ִ�в�ͬ����
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
	public static void doing2() {          //���ָ��2�����е��ж�����
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