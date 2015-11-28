import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class StartClient {
	
	private static final String hostname = "127.0.0.1";
	private static final int port = 4444;
	
	public static void main(String[]args){
		try {
			Socket socket = new Socket(hostname, port);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			Scanner scan = new Scanner(System.in);
			
			while(!in.ready()){
				out.println("/Ready");
			}
			
			System.out.print(in.readLine());
			String line = scan.nextLine();
			out.println(line);
			
			boolean exit = true;
			while(exit){
				if(in.ready()){
					System.out.println(in.readLine());
				}
				if(scan.hasNextLine()){
					out.println(scan.nextLine());
				}
			}
			scan.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
