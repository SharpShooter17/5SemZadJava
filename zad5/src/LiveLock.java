
class Class1 {
	boolean bBoolean = false;
	
	public void action(Class2 c2){
		while (!c2.bBoolean && !Thread.currentThread().isInterrupted()){
			System.out.println("Wait for c2..");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		System.out.println("C1 end");
	}
	
}

class Class2 {
	boolean bBoolean = false;
	
	public void action(Class1 c1){
		while (!c1.bBoolean && !Thread.currentThread().isInterrupted()){
			System.out.println("Wait for c1..");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		System.out.println("C2 end");
	}
}

public class LiveLock extends Thread {
	public void run(){
		System.out.println("LiveLock start");
		Class1 c1 = new Class1();
		Class2 c2 = new Class2();
		
		Thread t1 = new Thread(new Runnable() {
			
					@Override
					public void run() {
						c1.action(c2);				
					}
				});
			
		Thread t2 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						c2.action(c1);				
					}
				});
		
		t1.start();
		t2.start();
		
		while (!isInterrupted());
		
		t1.interrupt();
		t2.interrupt();
		
		System.out.println("LiveLock stop");
	}
}
