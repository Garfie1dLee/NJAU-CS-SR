import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Memory {
	public static boolean[] Memory=new boolean[32]; //����32���ڴ�鷽����ʾ
	public static LinkedList<MemoryDataS> MemoryList=new LinkedList<MemoryDataS>();//����32���ڴ�������
	public static boolean Buffer=false;  //������ default=false
	static{ //��ʼ���ڴ�
	for(int i=0;i<32;i++)
		Memory[i]=false;
	MemoryDataS FreeArea1= new MemoryDataS(24,8,false);  //8-31Ϊ�����ڴ�
	MemoryList.offer(FreeArea1);
	}
	public static void printstate()
	{
		if(!Buffer)
			{System.out.println("�������޽���");
			GUI.textArea.append(CPU.GetTime()+":[�������޽���"+"]\n");
			GUI.textField_11.setText("�޽���");
			}
		else
		{
			GUI.textField_11.setText("ռ����");
		}
	}
	
	public static boolean getBuffState()  //BUFF!
	{
		return Buffer;
	}
	public static void P()  //ռ�û�����
	{
		Buffer=true;
		GUI.textArea.append(CPU.GetTime()+":[P����"+"]"+"\n");
	}
	public static void V()  //�뿪������
	{
		Buffer=false;
		GUI.textArea.append(CPU.GetTime()+":[V����"+"]"+"\n");
	}
	public static void WriteBuff()   //д��buff��ռ��
	{
		for(int i=0;i<8;i++)
			Memory[i]=true;
		GUI.textArea.append(CPU.GetTime()+":[�����뻺����"+"]"+"\n");
	}
	public static void ReadBuff()   //��ȡbuff��ռ��
	{
	}
	public static int RangeBuffIn()   //д��buff �������д��
	{
		int data=(int)(Math.random()*7);
		for(int i=data;i<8;i++)
			Memory[i]=true;
		GUI.textArea.append(CPU.GetTime()+":[�����뻺����"+"]"+"\n");
		return data;
	}
	public static int BuffOutPCB()     //buff���
	{
		for(int i=0;i<8;i++)
			Memory[i]=false;
		GUI.textArea.append(CPU.GetTime()+":[������������"+"]"+"\n");
		///// for future usage
		return 1;
	}
	public static int BuffOutDisk()     //buff��� �漴����Ӳ�̵�ַ(bug
	{
		for(int i=0;i<8;i++)
			Memory[i]=false;
		GUI.textArea.append(CPU.GetTime()+":[������������"+"]"+"\n");
		return (int)(Math.random()*20480)+1;
	}
	public static void Sort()  //���������ڴ������������
	{
		Collections.sort(MemoryList);
		
	}
	public static  void JoinFreeMemory() {  //�ϲ������ڴ�
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
	public static void show()  //չʾ�ڴ�
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
	public static int search(int StartLoc) //Ѱ���ض���ʼ���ڴ�
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
	public static int GetMemory()  //�����ڴ�
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
	public static void FreeMemory(int StartLoc)  //�ͷ��ڴ�
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
