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

import javax.swing.JFrame;

import model.GraphFamily;
import model.MySimpleList;
import model.Password;
import model.Person;
import model.RelationType;
import myClient.UI.ConstantsUI;
import myClient.UI.FamilyRelations;
import myClient.UI.JFMainWindow;
import myServer.RelationFamilies;
import utilities.ComplementDatas;


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
	
	private int current;
	private int iterator;

	public MyClient() {
		iterator = 0;
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
	
	
	
	@SuppressWarnings("unchecked")
	public void initApp() {
			try {
				createSocket();
				objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
				dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
				dataInputStream = new DataInputStream(socketClient.getInputStream());
				initWindow();
				readBasicInfoPerson(dataInputStream.readLong());
				objectInputStream = new ObjectInputStream(socketClient.getInputStream());
				do {
						dataOutputStream.writeInt(flatAddPerson);
						
						switch (flatAddPerson) {
						case 1:
							dataOutputStream.writeInt(flatAddPerson);
							objectOutputStream.writeObject((Person) jfMainWindow.getPersonCreated());
							familyRelations = new FamilyRelations(mapFamiliesRelations, this);
							flatAddPerson =0;
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
							objectOutputStream.writeObject(password);
							if(dataInputStream.readBoolean()) {
								jfMainWindow.setPane();
							}else {
								jfMainWindow.showExceptionUserNotRegistry();
							}
							flatAddPerson =0;
							break;
						case 4:
							dataOutputStream.writeInt(flatAddPerson);
							Password passwordCreated = jfMainWindow.getUserCreated();
							objectOutputStream.writeObject(passwordCreated);
							if(dataInputStream.readBoolean()) {
								jfMainWindow.setPane();
							}else {
								jfMainWindow.showExceptionUserDuplicate();
							}
							flatAddPerson =0;
							break;
						case 5:
							dataOutputStream.writeInt(flatAddPerson);
							Password passwordRecovered = jfMainWindow.getUserCreated();
							objectOutputStream.writeObject(passwordRecovered);
							jfMainWindow.showPasswordRecovered(dataInputStream.readUTF());
							flatAddPerson =0;
							break;
						case 6:
							dataOutputStream.writeInt(flatAddPerson);
							dataOutputStream.writeInt(jfMainWindow.getjPanel1().getComboBox().getSelectedIndex());
							MySimpleList<RelationFamilies<Person, Integer>> families = new MySimpleList<RelationFamilies<Person, Integer>>();
							int sizeListFamilies = dataInputStream.readInt();
							if (sizeListFamilies>0) {
								for (int i = 0; i < sizeListFamilies; i++) {
									families.add((RelationFamilies<Person, Integer>) objectInputStream.readObject());
//									updateRelatiob(RelationType.values()[dataInputStream.readInt()]);
									updatePerson(families.getIndex(i));
								}
							}
							if (current == 1) {
								afterFamily(families);
							}else if(current==2) {
								beforeFamily(families);
							}
							current = 0;
							flatAddPerson = 0;
							break;
						default:
							break;
						}
				}while(true);
				
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("No hay un servidor disponible");
			}
	}
	
	public void initWindow() {
		this.jfMainWindow = new JFMainWindow(this);
		this.jfMainWindow.setVisible(true);


	}
	//---------parte darwin---------------
	public void updateRelatiob(RelationType relationType) {
		jfMainWindow.getjPanel1().getParentesco().setText(relationType.name());
	}
	public void updatePerson(RelationFamilies<Person, Integer> relation) {
		jfMainWindow.getjPanel1().updateInfoPerson(relation.getPerson());
		jfMainWindow.getjPanel1().getParentesco().setText(RelationType.values()[relation.getIdTypeRelation()].name());
	}
	
	public void afterFamily(MySimpleList<RelationFamilies<Person, Integer>> list) {
		if (iterator < list.getSize()) {
			updatePerson(list.getIndex(iterator-1));
		}else {
			iterator = 0;
			updatePerson(list.getIndex(iterator));
		}
	}
	
	public void beforeFamily(MySimpleList<RelationFamilies<Person, Integer>> list) {
		if (iterator>=0) {
			updatePerson(list.getIndex(iterator+1));
		}else {
			iterator =list.getSize();
			updatePerson(list.getIndex(iterator));
		}
	}
	
	//------------------------

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == jfMainWindow.getjPanel1().getComboBox()) {
			iterator = 0;
			flatAddPerson =6;
		}else {
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
		case C_MENU_SHOW_SEARCH_RELATION_FAMILY_PANEL:
			new ComplementDatas().fillComboBox(mapFamiliesRelations, jfMainWindow.getjPanel1().getComboBox());
			jfMainWindow.showPanelSearchFamilyRelation();
			break;
		case C_LOGIN_BUTTON_ENTRY:
			flatAddPerson = 3;
			break;
		case C_LOGIN_BUTTON_REGISTRY:
			System.out.println("¿registrarse?");
			Password passwordCreated = jfMainWindow.getUserCreated();
			
			if(passwordCreated.getUser().equals(ConstantsUI.REGISTRY_USER) !=true  && passwordCreated.getPassword().equals(ConstantsUI.PASSWORD) != true) {
				flatAddPerson = 4;	
			}else {
				flatAddPerson = 0;
				jfMainWindow.showExceptionUserDuplicate();
			}
			break;
		case C_LOGIN_BUTTON_RECOVER_PASSWORD:
			flatAddPerson = 5;
			break;
		case C_CANCEL_CREATE_PERSON:
			System.out.println("cancelar persona");
			break;
		case AFTER:
			iterator++;
			current = 1;
			flatAddPerson  = 6;
			break;
		case BEFORE:
			iterator--;
			current = 2;
			flatAddPerson  = 6;
			break;
			}	
		}
	}
	public static void main(String[] args) {
		new MyClient();
	}

}
