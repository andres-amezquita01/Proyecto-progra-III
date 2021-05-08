package myServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import binarySearchTree.IConverterDatas;
import binarySearchTree.MyBinarySearchTree;
import model.Person;
import persistence.MyMasterPersonFile;
import utilities.ComplementDatas;

public class MyServer {
	private final static int PORT = 1111;
	private ServerSocket serverSocket;
	
	private FileHandler fileHandler;
	private Logger logger;
	private MyBinarySearchTree<String> myBinarySearchTree;
	private MyMasterPersonFile myMasterPersonFile;
	private ComplementDatas complementDatas;
	private final static String ADD_PERSON = "Añadir persona";

	public MyServer( )  {
		createFileLogger();
		createSockets();
		try {
			initTreeAndMasterFile();
		} catch (FileNotFoundException e) {
			writeInLog(e.getMessage());
		}
		initApp();
	}
	
	public void initTreeAndMasterFile() throws FileNotFoundException {
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
		writeInLog("cargando archivo maestro...");
		this.myMasterPersonFile = new MyMasterPersonFile("resources/out/masterFile/myMasterFile.Person");
		writeInLog("completado.");
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
	
	private void createThread(Socket socketClient) {
			new Thread(new Runnable() {
				@Override
				public void run() {	
					try {
//						DataOutputStream dataOut = new DataOutputStream(socketClient.getOutputStream());
//						DataInputStream objectIn = new DataInputStream(socketClient.getInputStream());
						System.out.println("dpodoopdpododpo");
						ObjectInputStream objectInputStream  = new ObjectInputStream(socketClient.getInputStream());
						DataInputStream dataInputStream = new DataInputStream(socketClient.getInputStream());
//						DataOutputStream dataOutputStream = new DataOutputStream(socketClient.getOutputStream());
						String message = "1)Agregar persona\n2)login";
							while(!message.equals("salir") ) {
<<<<<<< HEAD
								objectOutputStream.writeUTF(message);
								message = objectInputStream.readUTF();
									switch(message) {
										case ADD_PERSON:
											addPersonToMasterAndTreeFile((Person)objectInputStream.readObject());
=======
//								dataOutputStream.writeUTF(message);
//								message = dataInputStream.readUTF();
								int flat = dataInputStream.readInt();
									switch(flat) {
										case 1:
											System.out.println("rrrrrrr");
											addPersonToMasterAndTreeFile((Person)objectInputStream.readObject());
//											Person person = (Person) objectInputStream.readObject();
//											System.out.println(person.getFirstName());
>>>>>>> version1Darwin
											break;
									default:
											break;
									}
							}
							System.out.println("ultimooo");
							String messageOut = "Vuelve pronto...";
//							dataOutputStream.writeUTF(messageOut);
//							objectInputStream.close();
//							dataOutputStream.close();
//					} catch (IOException | ClassNotFoundException e) {
					} catch (IOException  e) {

						writeInLog(e.getMessage());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}).start();;
	}
	
	public Person readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
		return (Person) objectInputStream.readObject();
	}
	
	public void addPersonToMasterAndTreeFile(Person person) {
		try {
			this.myMasterPersonFile.add(person);
			this.myBinarySearchTree.add(person.getFirstName());
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
	
	public static void main(String[] args) {
		new MyServer();
	}
	
	
}
