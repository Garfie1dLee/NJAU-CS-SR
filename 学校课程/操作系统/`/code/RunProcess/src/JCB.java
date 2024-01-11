import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class JCB implements Comparable<JCB>{
	private int JobID;  
	private int InsNum;
	private int Priority;
	private int InTime;
	private int TurnTime;  //��תʱ��
	private int HarddiskLoc;  //Ӳ�������ַ
	private int MemoryLoc;    //�ڴ������ַ
	private static LinkedList<JCB> QueueList=new LinkedList<JCB>();  //job�ȴ�����
	private static LinkedList<JCB> JCBList=new LinkedList<JCB>();  //���е�job
	public JCB(int jobid,int priority,int intime,int insnum)
	{
		this.JobID=jobid;
		this.Priority=priority;
		this.InTime=intime;
		this.InsNum=insnum;
		this.CreateJob();  //ֱ�Ӵ���job
		System.out.println("CreateNewJob");
	}
	public void CreateJob()   //Create
	{
		this.HarddiskLoc=Harddisk.write(24);  //Ĭ��24��
		QueueList.offer(this);
		JCBList.offer(this);
		Collections.sort(QueueList);  //��Ҫ�������ȼ�����
	}
	public void JobToProcess(int loc)  //JCB to Process
	{
		this.MemoryLoc=loc;
		ArrayList<Instructions> InsSegments;
		if(this.JobID>5)
		{
			InsSegments=new ArrayList<Instructions>();
			for(int i=1;i<=InsNum;i++)
			{
				Instructions a=new Instructions(i,(int)(Math.random()*5),(int)(Math.random()*20),(int)(Math.random()*3)+1);
				InsSegments.add(a);
			}
		}
		else {
		String filename="input/"+Integer.toString(this.JobID)+".txt";
		InsSegments=IO.LoadInsFile(filename);
		}
		Process a=new Process(this.JobID,this.Priority,this.InTime,this.InsNum,InsSegments,this.MemoryLoc);
		a.setHarddiskLoc(this.HarddiskLoc);
		
		//����Mloc��Hloc
	}
	public void CancelJob() {  //����
		this.TurnTime=CPU.GetTime()-this.InTime+1;
		JCBList.remove(this);
	}
	@Override
	public int compareTo(JCB arg0) {  //����ʹ��collection��sort����
		// TODO Auto-generated method stub
		if(this.Priority>arg0.Priority)
		return 1;
		else if(this.Priority<arg0.Priority)
		return -1;
		else 
		return 0;
	}
	public static boolean Ifempty()  //return first  Queuelist
	{
		JCB jcb=QueueList.peek();
		if(jcb==null)
			return true;
		else 
			return false;
	}
	public static JCB poll() {
		return QueueList.poll();
	}
   public static void main(String args[]) {
	}

}
