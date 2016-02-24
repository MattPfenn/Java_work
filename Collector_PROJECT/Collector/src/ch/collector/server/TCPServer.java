package ch.collector.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date; 

public class TCPServer {

	private int port;
	private static int clientNumber = 0; 
	
	
	public TCPServer(int port){
		this.port = port;
	}

	public static void main(String[] args) {
		
		new Thread( () -> {
			
			System.out.println("Server started at "+ new Date());
			
			try {
				ServerSocket serverSocket = new ServerSocket(8000);
				
				while (true) {
					
					if(clientNumber<4){
						
						Socket socket = serverSocket.accept();
						clientNumber++;
						
						System.out.println("Starting thread for client "+ clientNumber +" at " + new Date()); 
						
						TCPServer server = new TCPServer(8000); 
						
						new Thread(server.new HandleAClient(socket)).start();
						
					}else{
						System.out.println("A client tried tried to connect but there's already 4 clients connected");
					}
				} 
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}).start(); 
	}
	
	class HandleAClient implements Runnable {
		
		private DataInputStream in = null;
		private DataOutputStream out = null;
		private Scanner sc = null;

		private final static String PASSWORD = "1234";
		
		private Socket s;
		private String station; //Le nom de la station qui se connecte
		
		public HandleAClient(Socket socket) {
			this.s = socket;
		}
		
		public void login() throws IOException {
			
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			sc = new Scanner(in);
			sc.useDelimiter("[ \r\n\0,]+"); 
			
			System.out.println("wait for login ...");
			String password = null;

			while (true) {
				String s1 = sc.next();
				if (s1.equals("LOGIN")) {
					station = sc.next();
					password = sc.next();
					break;
				} else {
					System.out.println("...");
				}
			}
			
			if (password.equals(PASSWORD)) {
				System.out.println("Station logged in");
			} else {
				System.out.println("Wrong password : access denied");
				sc.close();
				s.shutdownInput();
				s.close();
			}	
		}

		private void getData() throws IOException, FileNotFoundException {

			System.out.println("Asking for Data...");
			out.writeUTF(" DATAQUERY ");
			out.flush();
			
			System.out.println("Start to gathering data...");
			
			File f = new File("database\\"+station+"\\data.txt");
			if(f.exists()){}
			else{
				f.createNewFile();
			}
			
			FileWriter fw = new FileWriter(f,true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			sc.useDelimiter("[ \r\n]+");
			
			while((sc.next().equals("DATA")==false)){}
			bw.write("\r\n");
		 	bw.flush();
			while (true) {
				String s1 = sc.next(); 
				if(s1.equals("DATA"))
				{
					bw.write("\r\n");
				 	bw.flush();
				}
				else if(s1.equals("DATAEND")){
					break;
				}
				else{
					bw.write(s1+" ");
					bw.flush(); 
				}
			}
			fw.close(); 
			bw.close();
		}
			
		private void getStatus() throws IOException {

			System.out.println("Asking for Status...");
			out.writeUTF(" STATUSQUERY ");
			out.flush();
			System.out.println("Start to gathering status...");

			File f = new File("database\\"+station+"\\status.txt");
			if(f.exists()){}
			else{
				f.createNewFile();
			}
			
			FileWriter fw = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(fw);
			
			while((sc.next().equals("STATUS")==false)){}
			bw.write(new Date().toString());
			bw.write("\r\n");
			bw.flush(); 
			while (true) {
				String s1 = sc.next(); 
				if(s1.equals("STATUS"))
				{
					bw.write("\r\n");
				 	bw.flush();
				}
				else if(s1.equals("STATUSEND")){
					break;
				}
				else{
					bw.write(s1+" ");
					bw.flush(); 
				}
			}
			fw.close(); 
			bw.close();
		}
		
		private void logout() throws IOException {
			System.out.println("Waiting for logout..."); 
			while(sc.next().equals("LOGOUT")==false){}
			
			s.shutdownInput();
			s.shutdownOutput();
			s.close();
			
			System.out.println("Client "+clientNumber+" logout success");
			clientNumber--;
		}

		private void store() throws IOException {

			System.out
					.println("The data will be stored in the following directory :/database/"
							+ station + "/data.txt");

			File folder = new File("database/" + station);
			if (folder.exists() && folder.isDirectory()) {
				System.out.println("The storing directory already exist.");
			} else {
				System.out
						.println("This is the first time this station is identified. "
								+ "A new directory has been created");
				folder.mkdir();
				
				File f = new File("config");
				FileWriter fw = new FileWriter(f, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(station+"\r\n");
				bw.close(); 
				fw.close(); 
			}
		}
		
		@Override
		public void run() {
			try {
				login();
				store(); 
				getData(); 
				getStatus(); 
				logout(); 
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
