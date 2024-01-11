import java.util.Random;

public class Harddisk {
	private static Disk[][] Hard=new Disk[10][32];  //抽象10*32个disk
	
	static  //初始化
	{
		for(int i=0;i<10;i++)
			for(int j=0;j<32;j++)
					Hard[i][j]=new Disk();
	}
	public static int write(int len)  //写入硬盘
	{
		boolean doubleflag=true;
		while(doubleflag)
		{
		boolean flag=true;
		Random r=new Random();  //random location
		int i=r.nextInt(10);
		int j=r.nextInt(32);
		int k=r.nextInt(40);
		for(int o=0;o<len;o++)
			if(Hard[i][j].r(k+o)==false)
			continue;
		else
			{flag=false;break;}  //no match location 
		if(flag)
		{
				for(int o=0;o<len;o++)
				Hard[i][j].w(o+k);   //write
				doubleflag=false;
				int loc=i*32*64+j*64+k;
				System.out.print("write disk i="+i+" j="+j+" k="+k);
				System.out.println("loc="+loc);
				return loc; //return start loc
		}
		}
		return -1;
	}
		
	public static void superwrite(int loc,int len)  //写入硬盘 use loc
	{
		
		int i=loc/(32*64);
		int j=loc%(32*64)/64;
		int k=loc%(32*64)%64;
		for(int o=0;o<len;o++)
		{
			Hard[i][j].w(o+k);
		}
		System.out.println("write disk location  "+loc+"   len = "+len);
	}
	public static boolean read(int loc,int len)  //读取硬盘
	{
		boolean flag=true;  //trans
		int i=loc/(32*64);
		int j=loc%(32*64)/64;
		int k=loc%(32*64)%64;
		for(int o=0;o<len;o++)
		{
			if(Hard[i][j].r(o+k)==false)
				{flag=false;
				break;
				}
		}
		System.out.println("read disk location  "+loc+"   len = "+len);
		return flag;
		
	}
	public static void free(int loc,int len)  //释放硬盘 ，可不用
	{
		int i=loc/(32*64);
		int j=loc%(32*64)/64;
		int k=loc%(32*64)%64;
		for(int o=0;o<len;o++)
		{
			Hard[i][j].f(o+k);
		}
		
	}
	public static void main(String args[]) {
		System.out.println(Harddisk.read(Harddisk.write(1), 1));
		System.out.println(Harddisk.read(Harddisk.write(1), 1));
		System.out.println(Harddisk.read(Harddisk.write(1), 1));
		System.out.println(Harddisk.read(Harddisk.write(1), 1));
		
	}
	
}


