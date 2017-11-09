import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	public void run(){
		Scanner sc = new Scanner(System.in);
		try {
			Socket socket = new Socket("Bartek", 4321);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while (true){
				String line = new String();
				line = sc.nextLine();
				out.println(line);
				
				if (line.equals("close")){
					socket.close();
					return;
				}
				
				line = in.readLine();
				System.out.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}
	
	public static void main(String args[]){
		new Client().run();
	}
}
