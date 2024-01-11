import java.util.ArrayList;

public class LexicalAnalysis {
	static int checkSpec(String word)
	{
		Boolean status=word.contains("(");
		Boolean status1=word.contains(")");
		if(status&&status1) return 3;
		if(status) return 1;
		if(status1) return 2;
		return -1;
	}
    static int classify(String word)
    {
    	switch(word) 
    	{
    	case("true"): return 1;
    	case("false"): return 2;
    	case("not"): return 3;
    	case("or"): return 4;
    	case("and"): return 5;
    	case("("): return 6;
    	case(")"): return 7;
    	case(">"): return 8;
    	case("<"): return 9;
    	case("=="): return 10;
    	case("<="): return 11;
    	case(">="): return 12;
    	case("!="): return 13;
    	default: return 14;
    	}
    }
    static void PrintAll(ArrayList<KeyWord> Words)
    {
    	for(KeyWord a:Words)
    	{
    		System.out.println("( "+a.GetName()+", "+a.GetID()+" )");
    	}
    }
    static String[] ReadBypsana(ArrayList<KeyWord> list)
    {
    	ArrayList<KeyWord> newlist=new ArrayList<KeyWord>();
    	for(KeyWord e:list)
    		if(e.GetID()>=8&&e.GetID()<=13)
    			{e.SetName("rop");
    			newlist.add(e);
    			}
    		else if(e.GetID()==14)
    		{
    			e.SetName("id");
    			newlist.add(e);
    		}
    		else newlist.add(e);
    	return array2string(newlist);
    	 
    }
    static String[] array2string(ArrayList<KeyWord> list)
    {
    	String[] out = new String[list.size()+1];
    	for(int i=0;i<list.size();i++)
    		out[i]=list.get(i).GetName();
    	out[list.size()]="#";
    	return out; 
    }
    
    public static void main(String args[]) {
    	
    	PrintAll(IO.KeyWordList);
    	String[] out=ReadBypsana(IO.KeyWordList);
    	for(String e:out)
    		System.out.print(e);

    }
    
}
