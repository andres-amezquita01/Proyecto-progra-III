package myClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;

import model.Person;
import myClient.UI.JFMainWindow;


public class MyClient implements ActionListener{
	private Scanner console;
	private Socket socketClient;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private JFMainWindow jfMainWindow;
	private final static String ADD_PERSON = "Añadir persona";
	private final static int PORT = 12322;
	public MyClient() {
		 console = new Scanner(System.in);
		 initApp();
	}
	public void createObjectFlows() throws IOException {
		objectInputStream = new ObjectInputStream(socketClient.getInputStream());
		objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
	}
	public void closeObjectFlows() throws IOException {
		objectOutputStream.close();
		objectInputStream.close();
	}
	public void createFlowsInAndOut() throws IOException {
		 dataInputStream = new DataInputStream(socketClient.getInputStream());
		 dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
	}
	public void closeFlowsInAndOut() throws IOException {
		 dataOutputStream.close();
		dataInputStream.close();
	}
	public void createSocket() throws UnknownHostException, IOException {
			socketClient =  new Socket("localhost", PORT);
	}
	
	public void initApp() {
			try {
				System.out.println("eyyyyyy");
				createSocket();
//				createFlowsInAndOut();
				System.out.println("222222222");
				createObjectFlows();
				System.out.println("33333");
				initWindow();
				do {
						String comunication = dataInputStream.readUTF(); //leemos un objeto
						System.out.println(comunication + "hhoooho");
//						comunication = console.next();
						dataOutputStream.writeUTF(comunication);
							switch (comunication) {
							case ADD_PERSON:
								objectOutputStream.writeObject((Person) jfMainWindow.getPersonCreated());
								break;
	
							default:
								break;
							}
				} while (true);
				
				
			} catch (IOException e) {
				System.out.println("No hay un servidor disponible");
			}
	}
	
	public void initWindow() {
		this.jfMainWindow = new JFMainWindow(this);
		this.jfMainWindow.setVisible(true);


	}
	
	
	public static void main(String[] args) {
		new MyClient();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		switch (Commands.valueOf(event.getActionCommand())) {
		case C_CREATE_PERSON:
			System.out.println(this.jfMainWindow.getPersonCreated());
			break;
		}		
	}
}




















