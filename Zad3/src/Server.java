import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Connected extends Thread {
	private Socket clientSocket;
	public Connected(Socket clientSocket){
		this.clientSocket = clientSocket;
	}
	
	public void run(){
		
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while (true){
				String line = new String();
				line = in.readLine();
				
				if (line.equals("close")){
					clientSocket.close();
					return;
				}
				
				out.println("Server: " + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class Server {
	
	public void run(){
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(4321);
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new Connected(clientSocket).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new Server().run();
		return;
	}
}
