
public class Instructions {  
	private int InstrucID;   
	private int InstruState;   //ָ������Ϊһ���ṹ�壬�õ���ֻ��ָ�����ֻ࣬��Ҫ����ָ������
	private int L_Address;   //�߼���ַ
	private int InRunTimes;   //ָ������ʱ��
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

//ָ���ࡣ