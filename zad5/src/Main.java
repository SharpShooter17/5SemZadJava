
public class Main {
	public static void main(String args[]){
		if (args.length != 1){
			return;
		}

		Thread t = null;
		if (args[0].equals("1")){
			t = new DeadLock();
		} else if (args[0].equals("2")){
			t = new LiveLock();
		} else if (args[0].equals("3")){
			t =new Starvation();
		}
		
		t.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();		
	}
}
