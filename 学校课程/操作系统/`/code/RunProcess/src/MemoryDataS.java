
public class MemoryDataS implements Comparable<MemoryDataS> {
	private int DSize;
	private int StartLoc;
	private boolean OccupySta;
	public int compareTo(MemoryDataS arg0) {  //便于使用collection的sort函数
		// TODO Auto-generated method stub
		if(this.StartLoc>arg0.StartLoc)
		return 1;
		else if(this.StartLoc<arg0.StartLoc)
		return -1;
		else 
		return 0;
	}
	public MemoryDataS(int DSize,int StartLoc,boolean OccupySta) {    
		// TODO Auto-generated constructor stub
		this.DSize=DSize;
		this.StartLoc=StartLoc;
		this.OccupySta=OccupySta;
	}
	public void Update(int DSize,boolean OccupySta) {    
		// TODO Auto-generated constructor stub
		this.DSize=DSize;
		this.OccupySta=OccupySta;
	}
	public void Free()
	{
		this.OccupySta=false;
	}
	public int getDSize() {
		   return this.DSize;
	    }
	public int getStartLoc() {
		   return this.StartLoc;
	    }
	public boolean getOccupySta() {
		   return this.OccupySta;
	    }

}
