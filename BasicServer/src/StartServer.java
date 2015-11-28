import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
	
	private final static int port = 4444;
	
	public static void main(String[]args){
		try {
			System.out.println("Starting Server...");
			ServerSocket sSocket = new ServerSocket(port);
			System.out.println("Server Started. Listening for client.");
			Socket socket = sSocket.accept();
			System.out.println("Client found.");
			
			System.out.println("Preparing.");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Ready. Waiting for confirmation.");
			
			while(!in.ready()){}
			
			System.out.println("Confirmed.");
			
			out.println("Username: ");
			String user = "<noname>";
			while(user.equals("<noname>")){
				String line = in.readLine();
				if(!line.equals("/Ready")){
					user = line;
				}
			}
			
			System.out.println(user + " has connected.");
			
			boolean exit = true;
			while(exit){
				String line = in.readLine();
				if(line.equals("/quit")){
					System.out.println(user + " has disconnected.");
				}else{
					System.out.println(user + ": " + line);
				}
			}
			
			socket.close();
			sSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
