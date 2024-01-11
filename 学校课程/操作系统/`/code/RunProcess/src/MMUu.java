
public class MMUu {
	private int ladress;
	private int intime;
	public MMUu(int l,int i)
	{
		this.ladress=l;
		this.intime=i;
	}
	public int visitadress()
	{
		return this.ladress;
	}
	public int visitintime()
	{
		return this.intime;
	}
	public void reset(int l,int i)
	{
		this.ladress=l;
		this.intime=i;
	}
}
