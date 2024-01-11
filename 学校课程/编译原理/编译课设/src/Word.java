
public class Word {
	
		private String number;
		private String processID;
		public Word(String number,String processID)
		{
			this.number=number;
			this.processID=processID;
		}
		public String GetNumber()
		{
			return number;
		}
		public String GetProcesID()
		{
			if(processID=="")
			return  this.number;
			else
			return this.processID;
		}
		public void SetID(String processID)
		{
			this.processID=processID;
		}
        
	}