import java.util.ArrayList;
import java.util.List;

public class Starvation extends Thread {
	
	private List<Thread> lT;
	
	public synchronized void run(){
		System.out.println("Starvation start");
		lT = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
          Thread t =  new Thread(new Runnable() {
                public void run() {
                    while (!Thread.currentThread().isInterrupted()){
                    	System.out.println("Thread: " + getName());
                    }
                }
            });

          lT.add(t);
          t.start();
		}
		
        while (!isInterrupted());
        for (Thread thread : lT) {
			thread.interrupt();
        }
        System.out.println("Starvation stop");
	}
}
