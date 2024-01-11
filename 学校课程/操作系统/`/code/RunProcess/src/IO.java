import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
public class IO {
	static String filename = "input/9193010411-jobs-input.txt"; //作业所在地址
	static ArrayList<Job> JobList=LoadJobFile(filename);      //加载作业列表
	public static ArrayList<Job> LoadJobFile(String filename) //加载文件，输出为元素为job数组的动态数组
	{   String pattern="\\s*";  //用正则表达式过滤空行
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
				in=read.split(",");   //数字以逗号分割
				Job job=new Job(StringToInt(in)[0],StringToInt(in)[1],StringToInt(in)[2],StringToInt(in)[3]);
				out.add(job);
			}}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	public static ArrayList<Instructions> LoadInsFile(String filename) //加载文件，输出为元素为instructions数组的动态数组
	{   String pattern="\\s*";	 //用正则表达式过滤空行
		File file=new File(filename);
		String[] in;
		ArrayList<Instructions> out=new ArrayList<Instructions>();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String read=new String();
			while((read=reader.readLine())!= null)
				{if(!Pattern.matches(pattern, read))
			{   Pattern.matches(pattern,read);
				in=read.split(",");   //数字以逗号分割
				Instructions ins=new Instructions(StringToInt(in)[0],StringToInt(in)[1],StringToInt(in)[2],StringToInt(in)[3]);
				out.add(ins);
			}}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	private static int[] StringToInt(String[] strings) {  //将读取的字符串数组转换为int数组
		 int[] ints = new int[strings.length];
		    for(int i=0;i<strings.length;i++){
		        ints[i]=Integer.parseInt(strings[i]);
		    }
		    return ints;
	}
	private static ArrayList<Job> GetJobs(){  //用于返回文件中的所有作业
		return JobList;
	}
	public static void main(String args[]) {
		
	}
}
