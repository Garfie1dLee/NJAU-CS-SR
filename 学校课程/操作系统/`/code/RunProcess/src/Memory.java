import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Memory {
	public static boolean[] Memory=new boolean[32]; //代表32个内存块方便显示
	public static LinkedList<MemoryDataS> MemoryList=new LinkedList<MemoryDataS>();//抽象32个内存块的数列
	public static boolean Buffer=false;  //缓存区 default=false
	static{ //初始化内存
	for(int i=0;i<32;i++)
		Memory[i]=false;
	MemoryDataS FreeArea1= new MemoryDataS(24,8,false);  //8-31为可用内存
	MemoryList.offer(FreeArea1);
	}
	public static void printstate()
	{
		if(!Buffer)
			{System.out.println("缓冲区无进程");
			GUI.textArea.append(CPU.GetTime()+":[缓冲区无进程"+"]\n");
			GUI.textField_11.setText("无进程");
			}
		else
		{
			GUI.textField_11.setText("占用中");
		}
	}
	
	public static boolean getBuffState()  //BUFF!
	{
		return Buffer;
	}
	public static void P()  //占用缓存区
	{
		Buffer=true;
		GUI.textArea.append(CPU.GetTime()+":[P操作"+"]"+"\n");
	}
	public static void V()  //离开缓存区
	{
		Buffer=false;
		GUI.textArea.append(CPU.GetTime()+":[V操作"+"]"+"\n");
	}
	public static void WriteBuff()   //写入buff，占用
	{
		for(int i=0;i<8;i++)
			Memory[i]=true;
		GUI.textArea.append(CPU.GetTime()+":[拷贝入缓冲区"+"]"+"\n");
	}
	public static void ReadBuff()   //读取buff，占用
	{
	}
	public static int RangeBuffIn()   //写入buff 随机长度写入
	{
		int data=(int)(Math.random()*7);
		for(int i=data;i<8;i++)
			Memory[i]=true;
		GUI.textArea.append(CPU.GetTime()+":[拷贝入缓冲区"+"]"+"\n");
		return data;
	}
	public static int BuffOutPCB()     //buff输出
	{
		for(int i=0;i<8;i++)
			Memory[i]=false;
		GUI.textArea.append(CPU.GetTime()+":[拷贝出缓冲区"+"]"+"\n");
		///// for future usage
		return 1;
	}
	public static int BuffOutDisk()     //buff输出 随即返回硬盘地址(bug
	{
		for(int i=0;i<8;i++)
			Memory[i]=false;
		GUI.textArea.append(CPU.GetTime()+":[拷贝出缓冲区"+"]"+"\n");
		return (int)(Math.random()*20480)+1;
	}
	public static void Sort()  //对新增的内存区域进行排序
	{
		Collections.sort(MemoryList);
		
	}
	public static  void JoinFreeMemory() {  //合并空闲内存
		for(int i=0;i<MemoryList.size();i++)
		{   int number=0;
			if(MemoryList.get(i).getOccupySta()==false)
			{
				number++;
				for(int j=i+1;j<MemoryList.size();j++)
				{
					if(MemoryList.get(j).getOccupySta()==false)
						number++;
					else
						break;
				}
				if(number>1)
				{
					MemoryDataS first=MemoryList.get(i);
					MemoryDataS last=MemoryList.get(i+number-1);
					int size=last.getStartLoc()-first.getStartLoc()+last.getDSize();
					first.Update(size, false);
					for(int k=1;k<=number-1;k++)
						MemoryList.remove(i+1);
				}
			}	
		}
	}
	public static void show()  //展示内存
	{
		for(MemoryDataS e:MemoryList)
		{
			System.out.println(MemoryList.indexOf(e)+" "+e.getDSize()+" "
					+e.getStartLoc()+" "+e.getOccupySta());
		}
		System.out.println();
	}
	public static void show2()
	{
		for(boolean e:Memory)
		{
			System.out.print(e);
		}
	}
	public static int search(int StartLoc) //寻找特定开始的内存
	{
		for(MemoryDataS e:MemoryList)
		{
			if (e.getStartLoc()==StartLoc)
				{return MemoryList.indexOf(e);}
		}
		return -1;
	}
	public static int checkM()
	{
		boolean flag=false;
		for(MemoryDataS e:MemoryList)
		{
			if(e.getOccupySta() == false && e.getDSize()>=3)
				{flag=true;
				break;}
		}
		if(flag==true)
			return 1;
		else return -1;
	}
	public static int GetMemory()  //存入内存
	{
		int NeedSize=3;
		boolean flag=false;
		int number=-1;
		for(MemoryDataS e:MemoryList)
		{
			if(e.getOccupySta() == false && e.getDSize()>=NeedSize)
				{flag=true;number=MemoryList.indexOf(e);
				break;}
		}
		if(flag==true)
		{
				
			MemoryDataS old_one=MemoryList.get(number);
			int size=old_one.getDSize()-NeedSize;
			if(size==0)
			{
				old_one.Update(NeedSize, true);
				Sort();
				int first=old_one.getStartLoc();
				for(int j=0;j<3;j++)
				{
					Memory[first+j]=true;
				}
				return old_one.getStartLoc();
			}
			else
			{
			old_one.Update(NeedSize, true);
			int Startloc=old_one.getStartLoc()+NeedSize;
			MemoryDataS new_one=new MemoryDataS(size,Startloc,false);
			int first=old_one.getStartLoc();
			for(int j=0;j<3;j++)
			{
				Memory[first+j]=true;
			}
			MemoryList.offer(new_one);
			Sort();
			return old_one.getStartLoc();
			}
		}
		else
		{
			System.out.println("NO FREE MEMORY");
			return -1;
		}

		
	}
	public static void FreeMemory(int StartLoc)  //释放内存
	{
		int id=search(StartLoc);
		if(id!=-1)
		{
			MemoryList.get(id).Free();
			int first=MemoryList.get(id).getStartLoc();
			for(int j=0;j<3;j++)
			{
				Memory[first+j]=false;
			}
			Sort();
			JoinFreeMemory();
		}
		else
			System.out.println("Free Memory Error");
	}
	public static boolean visit(int loc)
	{
		if(Memory[loc]==true)
			return true;
		else
			return false;
	}
	public static int free1memory() {
		int count=0;
		for(int i=8;i<32;i++)
			if(Memory[i]==false)
				count++;
		return count;
	}
	public static void main(String args[]) {
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		GetMemory();
		FreeMemory(30);
		show2();

	}

}
