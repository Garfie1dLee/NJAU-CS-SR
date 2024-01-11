import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gramaana {
	static boolean switchword(String input)
	{
		switch(input)
		{
		case "not":
		case "or":
		case "and":
		case "(":
		case ")":
		case "#":
		case ">":
		case "<":
		case ">=":
		case "<=":
		case "==":
		case "!=":
			return true;
		default: return false;
		}
	}
	static String Precede(String left,String right)
	{
		if(left.equals("or"))
			switch(right)
			{
			case "or":
			case ")":
			case "#": return ">";
			default: return "<";
			}
		else if(left.equals("and"))
		{
			switch(right) {
			case "or":
			case "and":
			case ")":
			case "#":return ">";
			default : return "<";
			}
		}
		else if(left.equals("not"))
		{
			switch(right) {
			case "or":
			case "and":
			case ")":
			case "#":return ">";
			default: return "<";
		}}
		else if(left.equals("("))
		{
			switch(right) {
			case ")": return "=";
			default : return "<";
			}
		}
		else if(left.equals(")"))
		{
			switch(right)
			{
			case ">":
			case ">=":
			case "<":
			case "<=":
			case "!=":
			case "==": return "<";
			default: return ">";
			}
		}
		else if (left.equals("==")||left.equals("!=")||left.equals(">=")||left.equals("<=")||left.equals(">")||left.equals("<"))
		{
			switch(right)
			{
			default : return ">";
			}
		}
		else if (left.equals("#"))
		{
			switch(right)
			{
			case "#": return "=";
			default : return "<";
			}
		}
		return "ERROR";
	}
	 public static boolean judgeContainsStr(String str) {  
	        String regex=".*[a-zA-Z]+.*";  
	        Matcher m=Pattern.compile(regex).matcher(str);  
	        return m.matches();  
	    }  
	static boolean Operate2(String left,String sign,String right)
	{
		if(sign.equals("or"))
		{
			if(left.equals("true"))
				return true;
			else {
				if(right.equals("true"))
					return true;
				else return false;
			}
			}
		
		else if(sign.equals("and"))
		{
			if(left.equals("false"))
				return false;
			else
			{
				if(right.equals("true"))
					return true;
				else return false;
					
			}
		}
		else
		{
			if(judgeContainsStr(left)||judgeContainsStr(right)) return false;
			int l=Integer.parseInt(left); 
			int r=Integer.parseInt(right);
			switch(sign)
			{
			case ">": return (l>r);
			case "<": return (l<r);
			case ">=": return (l>=r);
			case "<=": return (l<=r);
			case "!=": return (l!=r);
			case "==": return (l==r);
			default: 
			}
		}
		return false;
	}
	static boolean Operate1(String sign,String word)
	{
		if(word.equals("true"))
			{
			return false;} 
		else { 
			return true;}
	}
	static void grama(String[] input)
	{
		Stack<String> Operate=new Stack<String> ();
		Stack<Word> numbers=new Stack<Word> ();
		Operate.push("#");
		int i=0;
		int count=0;
		while(input[i]!="#"||Operate.peek()!="#")
		{
			if(switchword(input[i]))
			{
				switch(Precede(Operate.peek(),input[i]))
				{
				case "<":Operate.push(input[i++]);break;
					
				case "=":{Operate.pop();i++;break;}
				
				case ">":if(Operate.peek().equals("not"))
				{
					String sign=Operate.pop();
					Word word=numbers.pop();
					Word word1=new Word(String.valueOf(Operate1(sign,word.GetNumber())),"T"+String.valueOf(count++));
					System.out.println("(not"+","+word.GetProcesID()+", ,"+word1.GetProcesID()+")");
					numbers.push(word1);
				}
				else {
					Word right=numbers.pop();
					Word left=numbers.pop();
					String sign=Operate.pop();
					Word word=new Word(String.valueOf(Operate2(left.GetNumber(),sign,right.GetNumber())),"T"+String.valueOf(count++));
					System.out.println("("+sign+","+left.GetProcesID()+","+right.GetProcesID()+","+word.GetProcesID()+")");
					numbers.push(word);
				}
				}
			}
			else
			{
				Word word=new Word(input[i++],"");
				numbers.push(word);
			}
			
		}
		System.out.println("最终结果为"+numbers.peek().GetNumber());
	}
	public static void main(String args[]) {
		String[] out=new String[IO.KeyWordList.size()+1];
		for(int i=0;i<IO.KeyWordList.size();i++)
			out[i]=IO.KeyWordList.get(i).GetName();
		out[IO.KeyWordList.size()]="#";
		grama(out);
		
		}
		
	}


