import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock extends Thread {
	private Object o1;
	private Object o2;
	private final  Lock lock1 = new ReentrantLock();
	private final  Lock lock2 = new ReentrantLock();

	@SuppressWarnings("deprecation")
	public void run(){
		System.out.println("DeadLock start");
		
		Thread t1 = new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					f1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		} );
		
		Thread t2 = new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					f2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		} );
		
		t1.start();
		t2.start();
		
		while (!isInterrupted());
		
		t1.interrupt();
		t2.interrupt();
		
		System.out.println("DeadLock stop");
	}
	
	public void f1() throws InterruptedException {
		lock1.lockInterruptibly();
		try {
			sleep(100);
			lock2.lockInterruptibly();
			System.out.println("f1!");
			
		} finally {
			System.out.println("Bye f1!");
		}
	}
	
	public void f2() throws InterruptedException {
		lock2.lockInterruptibly();
		try {
			sleep(100);
			lock1.lockInterruptibly();
			System.out.println("f2!");
			
		} finally {
			System.out.println("Bye f2!");
		}
	}
}
