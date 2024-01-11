import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class IO {
	static String filename = "input/9193010411-jobs-input.txt"; //��ҵ���ڵ�ַ
	static ArrayList<Job> JobList=LoadJobFile(filename);      //������ҵ�б�
	public static ArrayList<Job> LoadJobFile(String filename) //�����ļ������ΪԪ��Ϊjob����Ķ�̬����
	{   String pattern="\\s*";  //��������ʽ���˿���
		File file=new File(filename);
		String[] in;
		ArrayList<Job> out=new ArrayList<Job>();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String read=new String();
			reader.readLine();
			while((read=reader.readLine())!= null)
			{if(!Pattern.matches(pattern, read))
			{   Pattern.matches(pattern,read);
				in=read.split(",");   //�����Զ��ŷָ�
				Job job=new Job(StringToInt(in)[0],StringToInt(in)[1],StringToInt(in)[2],StringToInt(in)[3]);
				out.add(job);
			}}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	public static ArrayList<Instructions> LoadInsFile(String filename) //�����ļ������ΪԪ��Ϊinstructions����Ķ�̬����
	{   String pattern="\\s*";	 //��������ʽ���˿���
		File file=new File(filename);
		String[] in;
		ArrayList<Instructions> out=new ArrayList<Instructions>();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String read=new String();
			while((read=reader.readLine())!= null)
				{if(!Pattern.matches(pattern, read))
			{   Pattern.matches(pattern,read);
				in=read.split(",");   //�����Զ��ŷָ�
				Instructions ins=new Instructions(StringToInt(in)[0],StringToInt(in)[1],StringToInt(in)[2],StringToInt(in)[3]);
				out.add(ins);
			}}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	private static int[] StringToInt(String[] strings) {  //����ȡ���ַ�������ת��Ϊint����
		 int[] ints = new int[strings.length];
		    for(int i=0;i<strings.length;i++){
		        ints[i]=Integer.parseInt(strings[i]);
		    }
		    return ints;
	}
	private static ArrayList<Job> GetJobs(){  //���ڷ����ļ��е�������ҵ
		return JobList;
	}
	public static void main(String args[]) {
		
	}
}
