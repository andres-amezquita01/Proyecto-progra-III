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
/**
 * Cliente de mi aplicacion donde manejo todo lo concerniente al manejo, recepcion y envio de datos 
 * por parte del cliente 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */

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

	
	/**
	 * contructor donde inicializo una variable iterator que me permite un manejo correcto de los eventos de 
	 * mi aplicacion ademas de inicializar mi aplicacion donde recibira una respuesta del servidor empleando sockets
	 */
	public MyClient() {
		iterator = 0;
		 initApp();
	}
	
	/**
	 * creo mis ObjetcInput y Output para la recepcion y envio de objetos en mi aplicacion
	 * @throws IOException  manejo la exepcion por el manejo de flujo de datos de este tipo
	 */
	public void createObjectFlows() throws IOException {
		objectInputStream = new ObjectInputStream(socketClient.getInputStream());
		objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
	}
	
	/**
	 * metodo que me cierra el flujo de datos esto para evitar errores de cualquier tipo en mi aplicacion 
	 * @throws IOException manejo la exepcion por el manejo de flujo de datos de este tipo
	 */
	public void closeObjectFlows() throws IOException {
		objectOutputStream.close();
		objectInputStream.close();
	}
	
	/**
	 * creo las entradas y salidas del flujo de datos para valores generalmente de tipo primitivo
	 * @throws IOException manejo la exepcion por el manejo de flujo de datos de este tipo
	 */
	public void createFlowsInAndOut() throws IOException {
		 dataInputStream = new DataInputStream(socketClient.getInputStream());
		 dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
	}
	
	/**
	 * cierro el flujo de datos para mis valores primitivos 
	 * @throws IOException manejo la exepcion por el manejo de flujo de datos de este tipo
	 */
	public void closeFlowsInAndOut() throws IOException {
		 dataOutputStream.close();
		dataInputStream.close();
	}
	
	
	/**
	 * creo el socket de mi cliente el cual recibe como parametro la ip del servidor y el puerto
	 * @throws UnknownHostException excepcion por el manejo de sockets
	 * @throws IOException Excepcion por el envio de datos por medio de sockets
	 */
	public void createSocket() throws UnknownHostException, IOException {
			socketClient =  new Socket("localhost", PORT);
	}
	
	
	/**
	 * leo la informacion basica de una familia esye metodo lo uso para mostrar las personas disponibles en mi apliacion
	 * @param index posicion de la persona la cual quiero añadir para luego mostrar
	 * @throws IOException execpion por el manejo de datos 
	 */
	public void readBasicInfoPerson(long index) throws IOException {
		mapFamiliesRelations = new HashMap<Long, String>();
		for (int i = 0; i < index; i++) {
			mapFamiliesRelations.put(dataInputStream.readLong(), dataInputStream.readUTF());
		}
	}
	
	
	
	/**
	 * inicializo mi app aca hago todos los procesos de interaccion de informacion con el servidor y donde 
	 * manipulo los archivos de mi persistencia y los muestro y escribo o actualizo si es necesario
	 */
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
	
	/**
	 * inicializo el frame principal de mi aplicacion
	 */
	public void initWindow() {
		this.jfMainWindow = new JFMainWindow(this);
		this.jfMainWindow.setVisible(true);


	}
	
	
	/**
	 * actualizo las relaciones familaires de una persona con base en el item de mi JcomboBox seleccionado
	 * @param relationType tipo de relacion que quiero mostrar
	 */
	public void updateRelatiob(RelationType relationType) {
		jfMainWindow.getjPanel1().getParentesco().setText(relationType.name());
	}
	
	
	/**
	 * Actualizo la informacion relacionada con las conexiones familiares  de la persona actual en mi combo box
	 * @param relation relacion la cual tiene una persona y el tipo de relacion con la persona actual de JComboBox
	 */
	public void updatePerson(RelationFamilies<Person, Integer> relation) {
		jfMainWindow.getjPanel1().updateInfoPerson(relation.getPerson());
		jfMainWindow.getjPanel1().getParentesco().setText(RelationType.values()[relation.getIdTypeRelation()].name());
	}
	
	
	/**
	 * metodo para mostrar el siguiente familiar relacionado con una persona para esto necesito
	 * tener una lista de familiares asociados a esa persona dada
	 * @param list lista de relaciones familiares de una persona
	 */
	public void afterFamily(MySimpleList<RelationFamilies<Person, Integer>> list) {
		if (iterator < list.getSize()) {
			updatePerson(list.getIndex(iterator-1));
		}else {
			iterator = 0;
			updatePerson(list.getIndex(iterator));
		}
	}
	
	
	/**
	 * metodo para mostrar el anterior familiar relacionado con una persona para esto necesito
	 * tener una lista de familiares asociados a esa persona dada
	 * @param list lista de relaciones familiares de una persona
	 */
	public void beforeFamily(MySimpleList<RelationFamilies<Person, Integer>> list) {
		if (iterator>=0) {
			updatePerson(list.getIndex(iterator+1));
		}else {
			iterator =list.getSize();
			updatePerson(list.getIndex(iterator));
		}
	}
	
	//------------------------

	
	
	private int familiCountManagaer;
	/**
	 * metodo sobreescrito de mi manejador de eventos donde los defino y con base en ellos 
	 * manejo los eventos necesarios para responder a las acciones del usuario correctamente
	 */
	
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
			if (familiCountManagaer==0) {
				new ComplementDatas().fillComboBox(mapFamiliesRelations, jfMainWindow.getjPanel1().getComboBox());
			}
			familiCountManagaer++;
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
	
	/**
	 * main donde ejecuto mi cliente
	 * @param args
	 */
	public static void main(String[] args) {
		new MyClient();
	}

}
