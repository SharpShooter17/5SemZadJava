import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

class Connected extends Thread {
	private Socket clientSocket;
	private Server server;
	private final int id;
	PrintWriter out;
	BufferedReader in;
	
	public Connected(int id, Socket clientSocket, Server server){
		this.id = id;
		this.clientSocket = clientSocket;
		this.server = server;
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void close(){
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void newService(){
		print("Podaj nazwe us�ugi: ");
		server.addNewService(id, read());
		print("Utworzono now� us�ug�.");
	}
	
	private void deleteService(){
		print("Podaj id us�ugi");
		int idDelete = Integer.parseInt(read());
		if (server.deleteService(idDelete, id)  ){
			print("Pomyslnie usuni�to usluge.");
		} else {
			print("Nie udalo si� usunac uslugi");
		}
	}
	
	private void showAllServices(){
		String str = "List of services:\n";
		for (Service service : server.getServices()) {
			out.println("Service: " + service.getName() + " id: " + service.getIdService());
		}
		print(str);
	}
	
	private void showAvailableServices(){
		String str = "List of available services:\n";
		for (Service service : server.getServices()) {
			str += "Service: " + service.getName() + " id: " + service.getIdService() + "\n";
		}
		print(str);
	}
	
	private void orderService() {
		print("Podaj id us�ugi: ");
		String str = new String(read());
		print("Podaj date rezerwacji:");
		str = read();
		Date dateStart = new Date(str);
		System.out.println(dateStart.getTime());
	}
	
	private String read(){
		try {
			while (!in.ready());
			return  in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void print(String msg){
		out.println(msg);
	}
	
	private void printMenu(){
		String menu = "[0] Close\n"
					+ "[1] New service\n"
					+ "[2] Delete service\n"
					+ "[3] All services\n"
					+ "[4] Available services\n"
					+ "[5] Order service\n"
					+ "[6] Cancel service\n";
		
		print(menu);
	}
	
	public void run(){
		printMenu();
			
		while (true){			
			String line = new String();
			line = read();
			
			if (line.equals("close")){
				close();
				return;
			} else if (line.equals("New service")){
				newService();
			} else if (line.equals("Delete service")){
				deleteService();
			} else if (line.equals("All services")){
				showAllServices();
			} else if (line.equals("Avaliable services")){
				showAvailableServices();
			} else if (line.equals("Order service")){
				orderService();
			} else if (line.equals("Cancel service")){
				
			} else {
				printMenu();
			}
		}
	}

	public long getId() {
		return id;
	}
}
