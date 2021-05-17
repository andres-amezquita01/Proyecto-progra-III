package myClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import model.GraphFamily;
import model.Password;
import model.Person;
import model.RelationType;
import myClient.UI.FamilyRelations;
import myClient.UI.JFMainWindow;


public class MyClient implements ActionListener{
	private Socket socketClient;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private JFMainWindow jfMainWindow;
	private int flatAddPerson;
	private final static int PORT = 1111;
	private Map<Long, String> mapFamiliesRelations;
	private FamilyRelations familyRelations;
	public MyClient() {
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
	
	public void readBasicInfoPerson(long index) throws IOException {
		mapFamiliesRelations = new HashMap<Long, String>();
		for (int i = 0; i < index; i++) {
			mapFamiliesRelations.put(dataInputStream.readLong(), dataInputStream.readUTF());
		}
	}
	
	
	
	public void initApp() {
			try {
				createSocket();
//				createFlowsInAndOut();
				objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
//				createFlowsInAndOut();
				dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
				dataInputStream = new DataInputStream(socketClient.getInputStream());
				initWindow();
//				console = new Scanner(System.in);
				readBasicInfoPerson(dataInputStream.readLong());
				
//				for (Entry<Long, String> entry : mapFamiliesRelations.entrySet()) {
//					String values = (entry.getKey() + "/" + entry.getValue()).replaceAll("_", "");
//				    System.out.println(values);
//				}
				
				int o = 0;
				do {
						dataOutputStream.writeInt(flatAddPerson);
						
						switch (flatAddPerson) {
						case 1:
							dataOutputStream.writeInt(flatAddPerson);
							objectOutputStream.writeObject((Person) jfMainWindow.getPersonCreated());
							familyRelations = new FamilyRelations(mapFamiliesRelations, this);
							flatAddPerson =0;
//							new FamilyRelations(null);
							break;
						case 2:
							dataOutputStream.writeInt(flatAddPerson);
							objectOutputStream.writeObject(new GraphFamily(jfMainWindow.getPersonCreated().getId()
							, RelationType.values()[familyRelations.getComboBoxOne().getSelectedIndex()],
							(long) mapFamiliesRelations.keySet().toArray()[familyRelations.getComboBoxTwo().getSelectedIndex()]));
							familyRelations.dispatchEvent(new WindowEvent(familyRelations, WindowEvent.WINDOW_CLOSING));
							flatAddPerson =0;
							break;
						case 3:
							dataOutputStream.writeInt(flatAddPerson);
							Password password = jfMainWindow.getUserCreated();
							System.out.println(password);
							objectOutputStream.writeObject(password);
							flatAddPerson =0;

							break;
						default:
							break;
						}
						
//						comunication =
//								objectOutputStream.writeObject((Person) jfMainWindow.getPersonCreated());
//								dataOutputStream.writeUTF(comunication);
					o++;
//				System.out.println(o);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				}while(true);
				
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
			flatAddPerson = 1;
			break;
		case ADD_RELATION_FAMILY:
			flatAddPerson =2;
			break;
		case C_MENU_SHOW_CREATE_PERSON_PANEL:
			jfMainWindow.showPanelPerson();
			break;
		case PANEL_TWO:
			jfMainWindow.showPanel2();
			break;
		case C_LOGIN_ENTRY:
			System.out.println("entro");
			flatAddPerson = 3;
			jfMainWindow.setPane();
			break;
		case C_LOGIN_RECOVER_PASSWORD:
			System.out.println("recuperar contraseña");
			break;
		case C_LOGIN_REGISTRY:
			System.out.println("¿registrarse?");
			break;
		}		
	}
}
