import java.util.ArrayList;
import java.util.Stack;

public class Process extends PCB{
	private static ArrayList<Process> ProcessRoom=new  ArrayList<Process>();
	private ArrayList<Instructions> insSegments;
	public Process(int ProID, int Priority, int InTimes, int InstrucNum, ArrayList<Instructions> Segments,int Memloc) {
		super(ProID, Priority, InTimes, InstrucNum,Memloc);
		this.insSegments=Segments;
		ProcessRoom.add(this);
	}
	public static ArrayList<Process> GetProcessRoom(){  //返回所有的进程列表
		return Process.ProcessRoom;
	}
    public void UpdateIR() {                           //对于进程需要更新ir，其需要在process中获得代码段
    	Instructions temp=insSegments.get(this.getPC());
    	this.setIR(temp.getins());
    }
    public ArrayList<Instructions> GetIns(){           //返回代码段
    	return insSegments;
    }
} 
