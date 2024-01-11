 
public class MMU {
	static MMUu[] MMUList=new MMUu[32];
	static {
		for(int i=0;i<32;i++)
			MMUList[i]=new MMUu(-1,-1);
	}
	public static int visitMMU(int L_Address,int Memloc) //返回对应内存地址
	{
		int flag=-1;
		for(int i=0;i<3;i++)
		{ 
			System.out.println("visitinggggggggggggggggggggggggggg"+(Memloc));
			System.out.println("visitinggggggggggggggggggggggggggg"+(Memloc+i));
			if(MMUList[Memloc+i].visitadress()==L_Address)
				flag=Memloc+i;
		}
		return flag;
	}
	public static int update(int L_Address,int Memloc)  //缺页中断更新页面使用fifo
	{
		int i=0;
		int temp=MMUList[Memloc].visitintime();  //找出进入时间最早的
		for(int j=1;j<3;j++)
		{
			if(MMUList[Memloc+j].visitintime()<temp)
				{i=j;temp=MMUList[Memloc+j].visitintime();}
		}
		MMUList[Memloc+i].reset(L_Address,CPU.GetTime());
		return Memloc+i;
	}
	public static void show(int Memloc)
	{
		for(int j=0;j<3;j++)
		{
			System.out.print(MMUList[Memloc+j].visitadress());
		}
		System.out.println();
	}
	public static void main(String[] args)
	{
	}
}
