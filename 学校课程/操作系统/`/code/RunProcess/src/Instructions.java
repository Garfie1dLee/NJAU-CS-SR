
public class Instructions {  
	private int InstrucID;   
	private int InstruState;   //指令类作为一个结构体，用到的只有指令种类，只需要返回指令种类
	private int L_Address;   //逻辑地址
	private int InRunTimes;   //指令运行时间
	public Instructions(int id, int state,int address,int time) {    
		this.InstrucID=id;
		this.InstruState=state;
		this.L_Address=address;
		this.InRunTimes=time;
	}
    public int getins() {
	   return this.InstruState;
    }
    public int getAddress()
    {
    	return this.L_Address;
    }
    public int getInRunTimes()
    {
    	return this.InRunTimes;
    }
}

//指令类。