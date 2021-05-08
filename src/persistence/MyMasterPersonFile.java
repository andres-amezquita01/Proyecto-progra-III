package persistence;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import model.Gender;
import model.Person;
import utilities.ComplementDatas;
/**
 * Encargado de la persistencia de una persona.
 * @author Andres Felipe Amezquita Gordillo
 *
 */
public class MyMasterPersonFile extends RandomAccessFile{
	private final static String MODE_FILE_READ_AND_WRITE = "rw";
	private final static long RECORD_SIZE = 2155;// tamaño de cada registro que es el que debemos saber
	
	private final static int LENGTH_PASSPORT = 30;
	private final static int LENGTH_NAME = 30;
	private final static int LENGTH_LAST_NAME = 30;
	private final static int LENGTH_PROFILE = 2000;

	private ComplementDatas converter;
	/**
	 * Constructor de un objeto PersonFile que a su vez crea un archivo RandomAccesFile.
	 * @param fileName Nombre del archivo binario a crear.
	 * @throws FileNotFoundException archivo no encontrado.
	 */
	public MyMasterPersonFile(String fileName) throws FileNotFoundException {
		super(new File(fileName), MODE_FILE_READ_AND_WRITE);
		converter = new ComplementDatas();
	}
	/**
	 * Añade al final del archivo a esa persona y retorna el indice donde lo agrego.
	 * @throws IOException 
	 * 
	 */
	public long add(Person person) throws IOException {
		long recordIndex = this.length() / RECORD_SIZE;
		this.seek(this.length());
		this.writeLong(person.getId());//8 byte
		this.writeDouble(person.getValue());//8byte dice que escribe el double como el long
		this.writeByte(person.getGender().ordinal());//1 byte //int = 0 para hobre,1 para mujer etc depende del orden que hayamos creado
		this.writeUTF(person.getBirthDay().format(DateTimeFormatter.ISO_DATE));//10*2 =20 debido a que cada caracter UTF ocupa 2  byte //guarda la fecha como string de acuerdo al formato.
		this.writeUTF(converter.stringSize(person.getFirstName(), LENGTH_NAME));//metodo string que permita maximo 30 caracteres 
		this.writeUTF(converter.stringSize(person.getLastName(), LENGTH_LAST_NAME));//30 metodo string => bytes
		this.writeChars(converter.stringSize(person.getPassport(), LENGTH_PASSPORT));//80 metodo string => bytes
		this.writeUTF(converter.stringSize(person.getProfile(), LENGTH_PROFILE));//2000 metodo string => bytes
	  //this.write(person.getPhoto());//1200*1200 metodo image => byte[ ]
//		addImage("resources/img/Desert.jpg");
		return recordIndex;
	}
	/**
	 * debemos leer en el mismo orden que añadimos
	 * @param index indice del registro en el archivo donde se va a leer.
	 * @return Objeto leido.
	 * @throws IOException 
	 */
	public Person read(long index) throws IOException {
		this.seek(index * RECORD_SIZE);
		Person person = new Person();
		person.setId(this.readLong());
		person.setValue(this.readDouble());
		person.setGender(Gender.values()[this.readByte()]);//volver el gender un arreglo y pasarle la posicion que estaba guardada, 0 hombre,1 mujer etc
		person.setBirthDay(LocalDate.parse(this.readUTF(), DateTimeFormatter.ISO_DATE));
		person.setFirstName(this.readUTF());
		person.setLastName(this.readUTF());
		person.setPassport(new String(readChar(LENGTH_PASSPORT)));//leer la secuencia de caracteres (un arreglo de bytes)
		person.setProfile(this.readUTF());
//		this.close();
//		person.setPhoto(readImage(400, 400)); //byte[] => image
		
		return person;
	}
	
	
	/**
	 * Lee un conjunto de byte y lo transforma a una imagen
	 * @param height Altura que desea que tenga la imagehn.
	 * @param width ancho que desea que tenga la imagen.
	 * @return Imagen
	 * @throws IOException
	 */
	public Image readImage(int height, int width) throws IOException {
		int sizeFile = this.readInt();
		byte[] bytesFile = new byte[sizeFile];
		for (int i = 0; i < sizeFile; i++) {
			bytesFile[i] = this.readByte();
		}
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytesFile);
		BufferedImage image = ImageIO.read(inputStream);
		return new ImageIcon(image.getScaledInstance(height, width, Image.SCALE_SMOOTH)).getImage();

	}
	
	/**
	 * Graba una imagen en el archivo binario
	 * @param path ruta de la imagen a grabar ¿como grabo el objeto como tal?
	 * @throws IOException
	 */
	public void  addImage(String path) throws IOException {
		File file = new File(path);
		int sizeFile = (int) file.length();
		this.writeInt(sizeFile);
		@SuppressWarnings("resource")
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));//lee el archivo
		byte[] bytesFile = new byte[sizeFile];
		bufferedInputStream.read(bytesFile);
		for (int i = 0; i < sizeFile; i++) {
			this.writeByte(bytesFile[i]);
		}
		System.out.println("archivo añadido");
	}
	/**
	 * Lee una secuencia de caracteres
	 * @param maximun cantidad  de caracteres que contiene la secuencia a leer.
	 * @return arreglo de byte de los caracteres leidos.
	 * @throws IOException
	 */
	public byte[] readChar(int maximun) throws IOException {
		maximun = maximun * 2;//un char ocupa 2 byte
		byte[] bytesArray = new byte[maximun];
		for (int i = 0; i < maximun; i++) {
			bytesArray[i] =  this.readByte();
		}
		return bytesArray;
	}
	public void writeStringAsChar(String characters, int length) throws IOException {
		String a = converter.stringSize(characters, length);
		this.writeChars(a);//80 metodo string => bytes
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(new MyMasterPersonFile("resources/out/masterFile/myMasterFile.Person").read(0).getFirstName());
	}
	
}











