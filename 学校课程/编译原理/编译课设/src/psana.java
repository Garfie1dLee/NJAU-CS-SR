import java.util.Stack;

public class psana {
	static Stack<Integer> statestack=new Stack<Integer>();
	static Stack<String> signstack=new Stack<String>();
	static String voids[][]={
		{"E'","E"},
		{"E","E or E"},
		{"E","E and E"},
		{"E","not E"},
		{"E","( E )"},
		{"E","id rop id"},
		{"E","true"},
		{"E","false"}};
	static String table[][]= {
			{
				"","or","and","not","(",")","id","rop","true","false","#","E"
			},
			{
				"0","","","S2","S3","","S4","","S5","S6","","1"
			},
			{
				"1","S7","S8","","","","","","","","acc",""
			},
			{
				"2","","","","S3","","S4","","S5","S6","","9"
			},
			{
				"3","","","S2","S3","","S4","","S5","S6","","10"
			},
			{
				"4","","","","","","","S11","","","",""
			},
			{
				"5","r6","r6","","","r6","","","","","r6",""
			},
			{
				"6","r7","r7","","","r7","","","","","r7",""
			},
			{
				"7","","","S2","S3","","S4","","S5","S6","","12"
			},
			{
				"8","","","S2","S3","","S4","","S5","S6","","13"
			},
			{
				"9","S7","S8","","","r3","","","","","r3",""
			},
			{
				"10","S7","S8","","","S14","","","","","",""
			},
			{
				"11","","","","","","S15","","","","",""
			},
			{
				"12","S7","S8","","","r1","","","","","r1",""
			},
			{
				"13","S7","S8","","","r2","","","","","r2",""
			},
			{
				"14","r4","r4","","","r4","","","","","r4",""
			},
			{
				"15","r5","r5","","","r5","","","","","r5",""
			}
	};
	    static void PrintIntStack(Stack<Integer> e)
	    {
	    	for(Integer i:e)
	    	{
	    		System.out.print(i+" ");
	    	}
	    }
	    static void PrintSignStack(Stack<String> e)
	    {
	    	for(String i:e)
	    	{
	    		System.out.print(i);
	    	}
	    }
		static String FindTable(int state,String sign)
		{
			for(int i=1;i<=11;i++)
				if(table[0][i].equals(sign))
					return table[state+1][i];
			return "ERROR";
		}
		static void print()
		{
			System.out.print("|");
		}
		static void print1()
		{
			System.out.print(" ");
		}
		static void PrintTable(int times,int i,String[] input,String leftprint)
		{
			System.out.print(times);
		    print();
			PrintIntStack(statestack);
			print();
			PrintSignStack(signstack);
			print();
			System.out.print(input[i]);
			print();
			for(int j=i;j<input.length;j++)
				System.out.print(input[j]);
			print();
			System.out.print(leftprint);
			
			
		}
		static void  SLR1_Run(String[] input)
		{
			System.out.println("²½Öè|×´Ì¬Õ»|·ûºÅÕ»|ÊäÈë·û|Ê£ÓàÊäÈë·û|²é±í");
			int state=0;
			statestack.push(state);
			signstack.push("#");
			int i=0;
			int times=1;
			String inputsign=input[i];
			while(true)
			{
				String Out=FindTable(state,inputsign);
				String leftprint="Action["+state+","+input[i]+"]="+FindTable(state,input[i]);
				if(Out.contains("r"))
						{
					Stack<Integer> inttemp=new Stack<Integer>();
					int Itemp;
					Stack<String> strtemp=new Stack<String>();
					String Stemp;
					int length=voids[Integer.parseInt(Out.substring(1,Out.length()))][1].split(" ").length;
					for(int j=1;j<=length;j++)
					{
						Itemp=statestack.pop();
						inttemp.push(Itemp);
						Stemp=signstack.pop();
						strtemp.push(Stemp);
					}
					state=statestack.peek();
					for(int j=1;j<=length;j++)
					{
						Itemp=inttemp.pop();
						statestack.push(Itemp);
						Stemp=strtemp.pop();
						signstack.push(Stemp);
					}
					leftprint+=",GOTO["+state+","+"E"+"]="+FindTable(state,"E");
						}
					
				PrintTable(times,i,input,leftprint);

				if(Out.contains("S"))
				{
					state=Integer.parseInt(Out.substring(1,Out.length()));
					statestack.push(state);
					signstack.push(inputsign);
					times++;
					inputsign=input[++i];
					System.out.println();
				}
				else if(Out.contains("r"))
				{
					int num=Integer.parseInt(Out.substring(1,Out.length()));
					String fore=voids[num][0];
					String behi=voids[num][1];
					int length=behi.split(" ").length;
					for(int j=1;j<=length;j++)
					{
						statestack.pop();
						signstack.pop();
					}
					signstack.push(fore);
					state=statestack.peek();
					String out1=FindTable(state,fore);
					state=Integer.parseInt(out1);
					statestack.push(state);
					times++;
					System.out.println();
				}
				else if(Out.contains("acc"))
				{
					break;
				}
				else
				{
					System.out.println("ERROR");
					break;
				}
			}
		}
		public static void main(String args[]) {
			String[] out=LexicalAnalysis.ReadBypsana(IO.KeyWordList);
	    	SLR1_Run(out);
	    }

}
