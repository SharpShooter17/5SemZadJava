import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class User extends Thread {
	private final Server server;
	private final Socket userSocket;

	private PrintWriter output;
	private BufferedReader input;
	
	private List<Service> services;
	
	private static int userCounter;
	private final int userId;
	
	public User(Server server, Socket userSocket){
		this.server = server;
		this.userSocket = userSocket;
		this.userId = userCounter++;
		
		services = new ArrayList<Service>();
		
		try {
			output = new PrintWriter(userSocket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Service> getServices(){
		return services;
	}
	
	public String getReservations(){
		String result = new String();
		result = "Your's reservations:\n";
		for (Service service : services) {
			result += service.getTimeReservations();
		}
		return result;
	}
	
	public List<List<Service>> getListOfServices(){
		return server.getListOfServices();
	}
	
	public Service getService(int id) throws ExceptionNoServiceFound{
		return server.getService(id);
	}
	
	public String showMyServices(){
		String result = new String();
		
		for (Service service : services) {
			result += service.getName() + " ID: " + service.getIdService() + "\n";
		}
		
		return result;
	}
	
	public String getAllServices(){
		return server.getAllServices();
	}
	
	public void newService(String name){
		services.add(new Service(name));
	}
	
	public void cancelReservation(int idReservation) throws ExceptionNoReservation{
		server.cancelReservation(idReservation, this.userId);
	}
	
	public boolean deleteService(int id){
		for (Service service : services) {
			if ( service.getIdService() == id ){
				return services.remove(service);
			}
		}
		return false;
	}
	
	private String read(){
		String result = new String();
		
		try {
			while(!input.ready()) {
				TimeUnit.MILLISECONDS.sleep(100);
			}
			
			result += input.readLine();
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		
		return result.trim();
	}
	
	private void print(String msg){
		output.println(msg);
	}
	
	public int getUserId(){
		return userId;
	}
	
	public void run(){
		boolean bDone = false;
		
		ProtocolContext protocol = new ProtocolContext(this);
		
		while(!bDone) {
			String msg = read();
			System.out.println("Klient: " + msg + " | Id: " + getId());
			
			msg = protocol.getProtocolState().processInput(msg);
			
			if (protocol.getProtocolState() == null){
				bDone = true;
			} else {
				System.out.println("Server: " + msg);
				print(msg);
			}
		}
		
		try {
			output.close();
			input.close();
			userSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.removeUser(this);		
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
