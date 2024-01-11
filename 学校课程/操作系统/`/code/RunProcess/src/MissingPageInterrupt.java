
public class MissingPageInterrupt extends Thread{
	static boolean SUPERFLAG=false;
	public void run() {
		
			while(true) if(TimeThread.Sign){
				 
					   GUI.Lock.lock();                    
					   try {
						   GUI.Condition.signalAll();   
					    }	
					   finally {	
					   }
					   if(SUPERFLAG)
					   {
					   CPU.GOTOCORE(true);
			           System.out.println("NO PAGE!!");
			           Harddisk.read(CPU.loadingprocess.getHarddiskLoc(), CPU.loadingprocess.getaddress());
			           System.out.println("visitharddisk="+(CPU.loadingprocess.getHarddiskLoc()+CPU.loadingprocess.getaddress()));
			           int ActualLoc=MMU.update(CPU.loadingprocess.getaddress(),CPU.loadingprocess.getMemoryLoc());
			           System.out.println("MemoryLoc="+ActualLoc);
			           CPU.GOTOUSER(true);
			           Memory.visit(ActualLoc);  //∑√Œ ƒ⁄¥Ê
			           SUPERFLAG=false;
					   }
						  try {GUI.Condition.await();} catch (InterruptedException e) {e.printStackTrace();}
						  GUI.Lock.unlock();
			}
	 }
			
		
}

