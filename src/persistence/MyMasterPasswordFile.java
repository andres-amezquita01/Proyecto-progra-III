package persistence;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import model.Password;
import utilities.ComplementDatas;

public class MyMasterPasswordFile extends RandomAccessFile{
	private final static String MODE_FILE_READ_AND_WRITE = "rw";
	private ComplementDatas complementDatas;
	private static final int SIZE_USER = 30;
	private static final int SIZE_PASSWORD = 30;
	private static final int SIZE_REGISTRY = SIZE_USER + SIZE_PASSWORD;

	/**
	 * Constructor de un objeto PersonFile que a su vez crea un archivo RandomAccesFile.
	 * @param fileName Nombre del archivo binario a crear.
	 * @throws FileNotFoundException archivo no encontrado.
	 */
	public MyMasterPasswordFile(String fileName) throws FileNotFoundException {
		super(new File(fileName), MODE_FILE_READ_AND_WRITE);
		complementDatas = new ComplementDatas();
	}
	/**
	 * Añade al final del archivo a esa persona y retorna el indice donde lo agrego.
	 * @throws IOException 
	 * 
	 */
	public long add(Password password) throws IOException {
		this.seek(this.length());
		this.writeUTF(complementDatas.stringSize(password.getUser(), SIZE_USER));
		this.writeUTF(complementDatas.stringSize(password.getPassword(), SIZE_PASSWORD));
		return this.length()/SIZE_REGISTRY;
	}
	/**
	 * debemos leer en el mismo orden que añadimos
	 * @param index indice del registro en el archivo donde se va a leer.
	 * @return Objeto leido.
	 * @throws IOException 
	 */
	public Password read(long index) throws IOException {
		this.seek(index * SIZE_REGISTRY);
		Password password = new Password();
		password.setUser(this.readUTF());
		password.setPassword(this.readUTF());
		return password;
	}
	
	
	public long numberPasswordInFile() throws IOException {
		return this.length()/SIZE_REGISTRY;
	}
	
//	public static void main(String[] args) throws IOException {
//		System.out.println(new MyMasterPersonFile("resources/out/masterFile/myMasterFile.Person").read(2).getFirstName());
//	}
	
}
