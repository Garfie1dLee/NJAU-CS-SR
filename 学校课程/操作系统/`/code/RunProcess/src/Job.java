
public class Job {
	private int JobID;
	private int InsNum;
	private int Priority;
	private int InTime;
	public Job(int jobid,int priority,int intime,int insnum)
	{
		this.JobID=jobid;
		this.Priority=priority;
		this.InsNum=insnum;
		this.InTime=intime;
	}
    public int getid()
    {
    	return this.JobID;
    }
    public int gettime()
    {
    	return this.InTime;
    }
    public int getpriority()
    {
    	return this.Priority;
    }
    public int getinsnum()
    {
    	return this.InsNum;
    }
    public void setid(int id)
    {
    	this.JobID=id;
    }
    public void setpriority(int priority)
    {
    	this.Priority=priority;
    }
    public void settime(int time)
    {
    	this.InTime=time;
    }
    public void setinsnum(int insnum)
    {
    	this.InsNum=insnum;
    }
}

