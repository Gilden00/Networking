import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
	
	private final static int port = 4444;
	
	public static void main(){
		try {
			ServerSocket sSocket = new ServerSocket(port);
			Socket socket = sSocket.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			
			out.print("Username: ");
			String user = in.readLine();
			
			boolean exit = true;
			while(exit){
				String line = in.readLine();
				if(line.equals("/quit")){
					System.out.println(user + " has disconnected.");
				}else{
					System.out.println(user + ": " + line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
