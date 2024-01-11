
public class Disk {
	private boolean[] sector=new boolean[64];
	{
		for(int i=0;i<64;i++)
		{
			sector[i]=false;
		}
	}
	public boolean r(int loc)
	{
		return sector[loc];
	}
	public void w(int loc)
	{
		sector[loc]=true;
	}
	public void f(int loc)
	{
		sector[loc]=false;
	}
}
