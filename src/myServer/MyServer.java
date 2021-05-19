package myServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import model.GraphFamily;
import model.MySimpleList;
import model.Password;
import model.Person;
import model.RelationType;
import myClient.UI.ConstantsUI;
import persistence.MyFileFamiliesRelationsShip;
import persistence.MyMasterPasswordFile;
import persistence.MyMasterPersonFile;
import utilities.ComplementDatas;

public class MyServer {
	private final static int PORT = 1111;
	private ServerSocket serverSocket;
	
	private FileHandler fileHandler;
	private Logger logger;
	private MyBinarySearchTree<String> myBinarySearchTree;
	private MyBinarySearchTree<Long> myBinarySearchTreeId;
	private MyMasterPersonFile myMasterPersonFile;
	private ComplementDatas complementDatas;
	private MyFileFamiliesRelationsShip familiesRelationsShip;
	private MyBinarySearchTree<String> myBinarySearchTreePassword;
	private MyMasterPasswordFile myMasterPasswordFile;
	
	
	public MyServer( ) throws IOException  {
		createFileLogger();
		createSockets();
		try {
			initTreeAndMasterFile();
			
			familiesRelationsShip = new MyFileFamiliesRelationsShip("resources/out/graphRelationsFamilies/relations.graph");
		} catch (FileNotFoundException e) {
			writeInLog(e.getMessage());
		}
		initApp();
	}
	
	public void initTreeAndMasterFile() throws IOException {
		this.complementDatas = new ComplementDatas();
		writeInLog("cargando binaryTree");
		this.myBinarySearchTree = new MyBinarySearchTree<>("resources/out/trees/byName.tree", new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}, new IConverterDatas<String>() {

			@Override
			public byte[] keyToByte(String key) {
				if(key != null) {
					key = complementDatas.stringSize(key, 30);
					return key.getBytes();
				}else {
					key = complementDatas.stringSize(" ", 30);
					return key.getBytes();
				}
			}
			@Override
			public String byteToKey(byte[] byteArray) {
				return new String(byteArray);
			}
			@Override
			public int sizeKey() {
				return 30;
			}
		});
		this.myBinarySearchTreePassword = new MyBinarySearchTree<>(
				"resources/out/passwords/myBST.passwords"
				, new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						return o1.compareTo(o2);
					}
				},new IConverterDatas<String>() {
					@Override
					public byte[] keyToByte(String key) {
						if(key != null) {
							key = complementDatas.stringSize(key, 30);
							return key.getBytes();
						}else {
							key = complementDatas.stringSize(" ", 30);
							return key.getBytes();
						}
					}
					@Override
					public String byteToKey(byte[] byteArray) {
						return new String(byteArray);
					}
					@Override
					public int sizeKey() {
						return 30;
					}
				});
		myBinarySearchTreeId = new MyBinarySearchTree<>(
				"resources/out/trees/tree.integer", 
				new Comparator<Long>() {
					@Override
					public int compare(Long o1, Long o2) {
						return o1.compareTo(o2);
					}
				}, new IConverterDatas<Long>() {
					@Override
					public byte[] keyToByte(Long key) {
						if(key != null) {
						    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
						    buffer.putLong(key);
						    return buffer.array();
						}else {
						    ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
						    buffer.putInt(-1);
						    return buffer.array();
						}				
					}

					@Override
					public Long byteToKey(byte[] byteArray) {
						 ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
						 buffer.put(byteArray);
						 buffer.flip();//need flip 
						 return buffer.getLong();
					}

					@Override
					public int sizeKey() {
						return Long.BYTES;
					}
				});
		this.myMasterPasswordFile = new MyMasterPasswordFile("resources/out/passwords/myMasterFile.passwords");
		writeInLog("cargando archivo maestro...");
		this.myMasterPersonFile = new MyMasterPersonFile("resources/out/masterFile/myMasterFile.Person");
//		System.out.println("dddd: " + myBinarySearchTree.read(0).getIndex());
		writeInLog("completado.");
		
	}
	
	public MyBinarySearchTree<String> getMyBinarySearchTree() {
		return myBinarySearchTree;
	}

	/**
	 * Inicializa el servidor.
	 */
	private void initApp() {
		while(true) {
				try {
					
					writeInLog("Esperando un cliente...");
					Socket socketClient = serverSocket.accept();
					writeInLog("se ha conectado " + socketClient.getInetAddress().getHostName());
					writeInLog("desde: " + socketClient.getInetAddress().getHostAddress());
					createThread(socketClient);
				} catch (IOException e) {
					System.out.println("error aceptando cliente.");
					writeInLog(e.getMessage());
				}
		}
		
	}
	
	private void sendDataBasicPersons(DataOutputStream dataOutputStream) throws IOException {
		for (int i = 0; i < myMasterPersonFile.numberPersonsInFile(); i++) {
			Person person = myMasterPersonFile.read(i);
			String name= (person.getFirstName() +" " + person.getLastName()).replace("_", ""); 
			dataOutputStream.writeLong(person.getId());
			dataOutputStream.writeUTF(name);
		}
	}
	
	private void createThread(Socket socketClient) {
			new Thread(new Runnable() {
				@Override
				public void run() {	
					try {
//						DataOutputStream dataOut = new DataOutputStream(socketClient.getOutputStream());
//						DataInputStream objectIn = new DataInputStream(socketClient.getInputStream());
						System.out.println("server start");
						ObjectInputStream objectInputStream  = new ObjectInputStream(socketClient.getInputStream());
						DataInputStream dataInputStream = new DataInputStream(socketClient.getInputStream());
						DataOutputStream dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
						dataOutputStream.writeLong(myMasterPersonFile.numberPersonsInFile());
						sendDataBasicPersons(dataOutputStream);
//						DataOutputStream dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
						String message = "1)Agregar persona\n2)login";
						ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketClient.getOutputStream());

							while(!message.equals("salir") ) {
								int flat = dataInputStream.readInt();
									switch(flat) {
										case 1:
											addPersonToMasterAndTreeFile((Person)objectInputStream.readObject());
											break;
										case 2: 
											addRelationFamlily((GraphFamily) objectInputStream.readObject());
											break;
										case 3://boton iniciar
											if(isRegistry((Password) objectInputStream.readObject())) {
												//hacemos proceso de registro
												dataOutputStream.writeBoolean(true);
											}else {
												dataOutputStream.writeBoolean(false);
											}
											break;
										case 4:
											if(registryNewUser((Password) objectInputStream.readObject())) {
												//hacemos proceso de registro
												dataOutputStream.writeBoolean(true);
											}else {
												dataOutputStream.writeBoolean(false);
											}
											break;
										case 5:
											dataOutputStream.writeUTF(recoveredPassWord((Password) objectInputStream.readObject()));
											break;
										case 6:
											MySimpleList<RelationFamilies<Person,Integer>> list = relationsFamilies((long) dataInputStream.readInt());
											dataOutputStream.writeInt(list.getSize());
											if (list.getSize()>0) {
												for (int i = 0; i < list.getSize(); i++) {
//													System.out.println("value relationn is: " + list.getIndex(i).geKey());
													objectOutputStream.writeObject((RelationFamilies<Person, Integer>) list.getIndex(i));
//													dataOutputStream.writeInt(list.getIndex(i).geKey());
												}
											}
											
										break;
									default:
											break;
									}
							}
							System.out.println("ultimooo");
//							String messageOut = "Vuelve pronto...";
//							dataOutputStream.writeUTF(messageOut);
//							objectInputStream.close();
//							dataOutputStream.close();
//					} catch (IOException | ClassNotFoundException e) {
					} catch (IOException  e) {
//						e.printStackTrace();
						writeInLog(e.getMessage());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}).start();;
	}
	//---------version ultima----------------
	public MySimpleList<RelationFamilies<Person,Integer>> relationsFamilies(Long idPerson) {
		MySimpleList<RelationFamilies<Person,Integer>> list = new MySimpleList<>();
		try {
			for (int i = 0; i < familiesRelationsShip.numberRelationsInFile(); i++) {
				if (familiesRelationsShip.read(i).getIdPersonOne() == myBinarySearchTreeId.read(idPerson)
						.getInformation().geKey()) {
					list.add(new RelationFamilies<Person,Integer>(searchPerson(familiesRelationsShip.read(i).getIdPersonTwo()),
							familiesRelationsShip.read(i).getRelationType().ordinal()
							));
				}
			}
			
			
		} catch (IOException e) {
			System.out.println("dpdpdp´´dp");
			e.printStackTrace();
		}
		return list;
	}
	
	public Person searchPerson(long idPerson) throws IOException {
		return myMasterPersonFile.read(myBinarySearchTreeId.search(idPerson).getIndexInMasterFile());
	}
	//-----------fin version ultima---------------
//	public Map<Long,RelationType> relationsFamilies(Long idPerson) throws IOException{
//		Map<Long, RelationType> aux = new HashMap<>();
//		for (int i = 0; i < familiesRelationsShip.numberRelationsInFile(); i++) {
//			if (familiesRelationsShip.read(i).getIdPersonOne()==idPerson) {
//				aux.put(familiesRelationsShip.read(i).getIdPersonTwo(),
//						familiesRelationsShip.read(i).getRelationType());
//			}
//		}
//		return aux;
//	}
	
	//--------------AREA DE REGISTRO-----------------------------
	
	/**
	 * Valida si un usuario existe en la base de datos.
	 * @param password
	 */
	public boolean isRegistry(Password password)  {
		try {
			String user = complementDatas.stringSize(password.getUser(), 30);
			String passwordUser = complementDatas.stringSize(password.getPassword(), 30);
			Information<String> informationFound = myBinarySearchTreePassword.search(user) ;
			if(informationFound != null) {
				Password passwordMasterFile = this.myMasterPasswordFile.readIndex(informationFound.getIndexInMasterFile());
				if(passwordMasterFile.getUser().equals(user) == true && passwordMasterFile.getPassword().equals(passwordUser)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String recoveredPassWord(Password password) {
		try {
			String user = complementDatas.stringSize(password.getUser(), 30);
			Information<String> informationFound = myBinarySearchTreePassword.search(user) ;
			if(informationFound != null) {
				return this.myMasterPasswordFile.readIndex(informationFound.getIndexInMasterFile()).getPassword().replace("_", "");
			}else {
				return ConstantsUI.EXCEPTION_USER_NOT_REGISTRY;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ConstantsUI.EXCEPTION_USER_NOT_REGISTRY;
	}
	/**
	 * Registra un nuevo usuario a la aplicacion.
	 * @param password usuario y contraseña  del cliente
	 * @return true si se pudo agregar, false si ya existia.
	 */
	
	private boolean registryNewUser(Password password) {
		try {
			String user = complementDatas.stringSize(password.getUser(), 30);
			Information<String> informationFound = myBinarySearchTreePassword.search(user) ;
			if(informationFound == null) {
				myBinarySearchTreePassword.add(new Information<String>(password.getUser(), this.myMasterPasswordFile.add(password)));
					return true;
			}else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Aun NO SE USA
	 * @param password
	 */
	public void loggin(Password password) {
		try {
			//aca se deberia inicializar el arbol que coincida con el usuario
			String user = complementDatas.stringSize(password.getUser(), 30);
//			String passwordUser = complementDatas.stringSize(password.getPassword(), 30);
//			Information<String> information = myBinarySearchTreePassword.search(user) ;
			myBinarySearchTreePassword.add(new Information<String>(password.getUser(), this.myMasterPasswordFile.add(password)));
			System.out.println(password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//---------------------FIN AREA DE REGISTRO------------------------------------
	
	public Person readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
		return (Person) objectInputStream.readObject();
	}
	
	
	public void addRelationFamlily(GraphFamily family) {
		try {
			familiesRelationsShip.add(family);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addPersonToMasterAndTreeFile(Person person) {
		try {
			System.out.println(person);
			long indexMasterFile = this.myMasterPersonFile.add(person);
			this.myBinarySearchTree.add(new Information<String>(person.getFirstName(),indexMasterFile));
			this.myBinarySearchTreeId.add(new Information<Long>(person.getId(), indexMasterFile));
			System.out.println(person);
		} catch (IOException e) {
			writeInLog(e.getMessage());
		} catch (Exception e) {
			writeInLog(e.getMessage());
		}
	}
	
	/**
	 * Crea el archivo para guardar e Log.
	 */
	private void createFileLogger() {
		try {
			 fileHandler  =  new FileHandler("resources/out/loggers/logger.log", true);
			 logger = Logger.getLogger(this.getClass().getName());
			 logger.addHandler(fileHandler);
			 SimpleFormatter formatter = new SimpleFormatter();
			 fileHandler.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("archivo no encontrado");
		}
	}
	
	private void createSockets() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.out.println("Error en creacion se socket, puerto ocupado");
		}
	}
	
	/**
	 * Escribe un objeto a traves del flujo de salida de datos.
	 * @param object objeto que se va a enviar a traves del flujo de salida.
	 * @throws IOException
	 */
	
	public void writeInLog(String message) {
		logger.info(message);
	}
	
	public static void main(String[] args) throws IOException {
		
		new MyServer();
		
	}
	
	
}
