import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class IO {
	static String filename = "input/input.txt"; //作业所在地址
	static ArrayList<KeyWord> KeyWordList=LoadFile(filename);      //加载作业列表
	public static ArrayList<KeyWord> LoadFile(String filename) //加载文件，输出为元素为KeyWord数组的动态数组
	{   String pattern="\\s*";	 //用正则表达式过滤空行
		File file=new File(filename);
		String[] in;
		ArrayList<KeyWord> out=new ArrayList<KeyWord>();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			String read=new String();
			while((read=reader.readLine())!= null)
			{if(!Pattern.matches(pattern, read))
			{ 
				in=read.split(" ");   //以空格分割
				for(String temp:in)
				{
					if(LexicalAnalysis.checkSpec(temp)==-1)
					{
						String keyword=temp;
						int id=LexicalAnalysis.classify(temp);
						KeyWord a=new KeyWord(keyword,id);
						out.add(a);
					}
					else if(LexicalAnalysis.checkSpec(temp)==1) 
					{
						int count=getCount(temp,"(");
						if(count==1) {
						String keyword1="(";
						int id1=6;
						KeyWord a=new KeyWord(keyword1,id1);
						out.add(a);
						String keyword2=temp.replace("(", "");
						int id2=LexicalAnalysis.classify(keyword2);
						KeyWord b=new KeyWord(keyword2,id2);
						out.add(b);}
						else {
							for(int i=1;i<=count;i++)
							{
								String keyword1="(";
								int id1=6;
								KeyWord a=new KeyWord(keyword1,id1);
								out.add(a);
							}
							String keyword2=temp.replace("(", "");
							int id2=LexicalAnalysis.classify(keyword2);
							KeyWord b=new KeyWord(keyword2,id2);
							out.add(b);
						}
						
					}
					else if(LexicalAnalysis.checkSpec(temp)==2) 
					{
						int count=getCount(temp,")");
						if(count==1)
						{
						String keyword1=")";
						int id1=7;
						KeyWord a=new KeyWord(keyword1,id1);
						String keyword2=temp.replace(")", "");
						int id2=LexicalAnalysis.classify(keyword2);
						KeyWord b=new KeyWord(keyword2,id2);
						out.add(b);
						out.add(a);
						}
						else {
							String keyword2=temp.replace(")", "");
							int id2=LexicalAnalysis.classify(keyword2);
							KeyWord b=new KeyWord(keyword2,id2);
							out.add(b);
							for(int i=1;i<=count;i++)
							{
								String keyword1=")";
								int id1=7;
								KeyWord a=new KeyWord(keyword1,id1);
								out.add(a);
							}
							
						}
					}
					else if(LexicalAnalysis.checkSpec(temp)==3) 
					{
						int count1=getCount(temp,"(");
						int count2=getCount(temp,")");
						if(count1==1&&count2==1) {
						String keyword1="(";
						int id1=6;
						KeyWord a=new KeyWord(keyword1,id1);
						out.add(a);
						String temp1=temp.replace("(", "");
						String keyword2=temp1.replace(")", ""); 
						int id2=LexicalAnalysis.classify(keyword2);
						KeyWord b=new KeyWord(keyword2,id2);
						out.add(b);
						String keyword3=")";
						int id3=7;
						KeyWord c=new KeyWord(keyword3,id3);
						out.add(c);
						}
						else
						{
							for(int i=1;i<=count1;i++)
							{
								String keyword1="(";
								int id1=6;
								KeyWord a=new KeyWord(keyword1,id1);
								out.add(a);
							}
							String temp1=temp.replace("(", "");
							String keyword2=temp1.replace(")", ""); 
							int id2=LexicalAnalysis.classify(keyword2);
							KeyWord b=new KeyWord(keyword2,id2);
							out.add(b);
							for(int i=1;i<=count2;i++)
							{
								String keyword3=")";
								int id3=7;
								KeyWord c=new KeyWord(keyword3,id3);
								out.add(c);
							}
						}
					}
					
					
				}
				
			}}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	private static ArrayList<KeyWord> GetJobs(){  //用于返回文件中的所有作业
		return KeyWordList;
	}
	public static int getCount(String str,String key){  
		if(str == null || key == null || "".equals(str.trim()) || "".equals(key.trim())){  
			return 0;  }  
		int count = 0;  
		int index = 0;  
		while((index=str.indexOf(key,index))!=-1){  
			index = index+key.length();  
			count++;
			}  
		return count;
		}  

}
