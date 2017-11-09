import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;


public class Server {
	public static final int port = 62000;
	public static final String hostName = "Bartek";
	
	private List<User> users;
	
	Server(){
		users = new ArrayList<User>();
	}
	
	public void run() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean listening = true;
		
		while (listening){
			try {
				User user = new User(this, serverSocket.accept());
				System.out.println("New user: " + user.getId());
				users.add(user);
				user.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getAllServices(){
		String result = new String();
		for (User user : users) {
			result += user.showMyServices();			
		}
		return result;
	}
	
	public List<List<Service>> getListOfServices(){
		List<List<Service>> result = new ArrayList<List<Service>>();
		
		for (User user : users) {
			result.add( user.getServices() );
		}
		
		return result;
	}
	
	public void cancelReservation(int idReservation, int idUser) throws ExceptionNoReservation{
		for (List<Service> list : getListOfServices()) {
			for (Service service : list) {
				if (service.hasReservation(idReservation)){
					service.removeReservation(idReservation, idUser);
				}
			}
		}
	}
	
	public void removeUser(User user){
		users.remove(user);
	}
	
	public Service getService(int id) throws ExceptionNoServiceFound{
		for (User user : users) {
			for (Service service : user.getServices()) {
				if ( service.getIdService() == id ){
					return service;
				}
			}
		}
		throw new ExceptionNoServiceFound();
	}
	
	public static void main(String args[]){
		new Server().run();
	}
}
