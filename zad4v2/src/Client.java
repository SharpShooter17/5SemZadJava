import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Client {
	Socket socket;
	PrintWriter output;
	BufferedReader input;
	Scanner scanner;
	
	public Client(){
		try {
			socket = new Socket(Server.hostName, Server.port);
			output = new PrintWriter(socket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		scanner = new Scanner(System.in);
	}
	
	private String read(){

		try {
			while(!input.ready()) {
				TimeUnit.MILLISECONDS.sleep(100);
			}
			CharBuffer buffer = CharBuffer.allocate(512);
			while (input.ready()) {
				//result += input.readLine();
				input.read(buffer);
			}
			buffer.flip();
			return buffer.toString();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void print(String msg){
		output.println(msg);
	}
	
	public void run() {
		print("Menu");
		boolean bDone = false;
		while(!bDone){
			System.out.println("Server: " + read());
			System.out.print("Klient: ");
			
			String request = new String(scanner.nextLine()).trim();
			print(request);
			
			if (request.toLowerCase().equals("close") || request.equals("10")){
				bDone = true;
			}
		}
		
		try {
			input.close();
			output.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		scanner.close();
	}
	
	public static void main(String args[]){
		new Client().run();
	}
}
