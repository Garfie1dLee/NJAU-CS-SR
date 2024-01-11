import java.io.IOException;
import java.util.Scanner;

public class GO {

	 public static void main(String args[]) throws IOException {
		 System.out.println("输入1词法分析2语义分析3语法分析");
		 Scanner input=new Scanner(System.in);
		 int a=input.nextInt();
		 if(a==1)
			 {LexicalAnalysis.PrintAll(IO.KeyWordList);
	    	String[] out=LexicalAnalysis.ReadBypsana(IO.KeyWordList);
	    	for(String e:out)
	    		System.out.print(e);}
		 else if(a==2)
		 {
			 String[] out=LexicalAnalysis.ReadBypsana(IO.KeyWordList);
		    	psana.SLR1_Run(out);
		 }
		 else if(a==3)
		 {
			 String[] out=new String[IO.KeyWordList.size()+1];
				for(int i=0;i<IO.KeyWordList.size();i++)
					out[i]=IO.KeyWordList.get(i).GetName();
				out[IO.KeyWordList.size()]="#";
				gramaana.grama(out);
		 }
		 while(true);
	 }
}
