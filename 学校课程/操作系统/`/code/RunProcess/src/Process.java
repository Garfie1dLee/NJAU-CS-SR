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
	public static ArrayList<Process> GetProcessRoom(){  //�������еĽ����б�
		return Process.ProcessRoom;
	}
    public void UpdateIR() {                           //���ڽ�����Ҫ����ir������Ҫ��process�л�ô����
    	Instructions temp=insSegments.get(this.getPC());
    	this.setIR(temp.getins());
    }
    public ArrayList<Instructions> GetIns(){           //���ش����
    	return insSegments;
    }
} 
