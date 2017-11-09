import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class Server {
	List<Connected> connected;
	List<Service> services;
	private int clientCounter = 0;
	private int servicesCounter = 0;
	
	public Server(){
		connected = new ArrayList<Connected>();
		services = new ArrayList<Service>();
	}
	
	public List<Service> getServices() {
		return services;
	}
	
	public List<Service> getOwnerServices(int id){
		List<Service> result = new ArrayList<Service>();
		
		for (Service service : services) {
			if (service.getIdOwner() == id){
				result.add(service);
			}
		}
		
		return result;
	}
	
	public void addNewService(int id, String name){
		services.add(new Service(id, servicesCounter++, name));
	}
	
	public boolean deleteService(int id, int idOwner){
		for (Service service : services) {
			if (service.getIdService() == id){
				if (service.getIdOwner() == idOwner){
					return services.remove(service);
				}else {
					return false;
				}
				
			}
		}
		return false;
	}
	
	public void run(){
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(4321);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				Connected client = new Connected(clientCounter++, clientSocket, this);
				connected.add(client);
				client.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}