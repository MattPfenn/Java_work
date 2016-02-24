package ch.collector.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class Station4 {

	private static DataOutputStream out = null;
	private static DataInputStream in = null;
	private static Socket s = null;

	public static void login() throws IOException {

		s = new Socket(InetAddress.getByName("localhost"), 8000);
		out = new DataOutputStream(s.getOutputStream());

		System.out.println("login");
		out.writeUTF("test_login2 LOGIN Station4 1234 ");
		out.flush();

        if(s.isInputShutdown()==false) System.out.println("Input opened");
        if(s.isOutputShutdown()==false) System.out.println("Output opened");
        if(s.isClosed()==false) System.out.println("socket opened");
	}

	public static void sendData() throws IOException, InterruptedException {
 
		in = new DataInputStream(s.getInputStream());
		Scanner sc = new Scanner(in);
        sc.useDelimiter("[ \n,\r]+");

		System.out.println("Waiting server DATAQUERY...");

		while (true) {
			String s1 = sc.next();
			if (s1.equals("DATAQUERY")) {
				System.out.println("Sending data to server");
				out.writeUTF(" DATA "+(System.currentTimeMillis()) +" 903 HU 3 "+(new Random().nextInt(100)) +" 0 100 TE 7 "+(new Random().nextInt(30))+" 0 30.2 ");
				out.flush();
                break;
			}
			System.out.println("...");
		}
        System.out.println("ok Matthieu");
        out.writeUTF(" DATAEND ");
        out.flush();
	}

	private static void sendStatus() throws UnknownHostException, IOException {

		Scanner sc = new Scanner(in);
        sc.useDelimiter("[ \n,\r]+");

		System.out.println("Waiting server STATUSQUERY...");

		while (true) {
			String s1 = sc.next();
			if (s1.equals("STATUSQUERY")) {
				System.out.println("Sending data to server");
				out.writeUTF(" STATUS Operator SwissPorn");
                out.flush();
				out.writeUTF(" STATUS Flux PussyExterminator2000");
                out.flush();
                break;
			}
			System.out.println("...");
		}
		out.writeUTF(" STATUSEND ");
                
        out.writeUTF(" LOGOUT ");         
        System.out.println("END");
	}

	public static void main(String[] args) throws InterruptedException {
		try {
			login();
			sendData();
			sendStatus();

			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
