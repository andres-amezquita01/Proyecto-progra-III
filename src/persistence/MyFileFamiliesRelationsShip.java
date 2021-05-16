package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import model.Gender;
import model.GraphFamily;
import model.RelationType;
import utilities.ComplementDatas;
/**
 * 
 * @author Darwin Vargas
 *clase quen me escribe las relaciones familiares representadas en un grafo 
 */
public class MyFileFamiliesRelationsShip extends RandomAccessFile {
	
	private final static String MODE_FILE_READ_AND_WRITE = "rw";
	private ComplementDatas converter;
	private final static long RECORD_SIZE = 17;// tamaño de cada registro que es el que debemos saber
	
	
	public MyFileFamiliesRelationsShip(String fileName) throws FileNotFoundException {
		super(new File(fileName), MODE_FILE_READ_AND_WRITE);
		converter = new ComplementDatas();
	}
	
	
	public long add(GraphFamily family) throws IOException {
		long recordIndex = this.length() / RECORD_SIZE;
		this.seek(this.length());
		this.writeLong(family.getIdPersonOne());
		this.writeByte(family.getRelationType().ordinal());
		this.writeLong(family.getIdPersonTwo());
		return recordIndex;
	}
	
	public GraphFamily read(long index) throws IOException {
		this.seek(index * RECORD_SIZE);
		GraphFamily family = new GraphFamily();
		family.setIdPersonOne(this.readLong());
		family.setRelationType(RelationType.values()[this.readByte()]);
		family.setIdPersonTwo(this.readLong());
		return family;
	}
	
	public static void main(String[] args) throws IOException {
		
//		System.out.println(new MyFileFamiliesRelationsShip("xd.d").add(new GraphFamily(21, RelationType.ESPOSO, 13)));
		
		System.out.println(new MyFileFamiliesRelationsShip("resources/out/graphRelationsFamilies/relations.graph").read(1).toString());
	
	}
	
}
