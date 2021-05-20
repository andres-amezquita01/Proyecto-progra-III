package binarySearchTree;

import java.io.Serializable;
/**
 * clase informacion que me permite tener un tipo de dato y su ubiacion como indice en el archivo maestro
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 * @param <T>
 */
public class Information<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected T key;
	protected long indexInMasterFile;
	
	
	/**
	 * contructor de mi clase Informacion donde recibo los parametros descritos
	 * @param key llave de la clase que sera de algun valor T
	 * @param indexMasterFile indice en el archivo maestro donde se encuentra el valor T
	 */
	public Information(T key, long indexMasterFile) {
		this.key = key;
		this.indexInMasterFile = indexMasterFile;
	}

	public T geKey() {
		return key;
	}

	public void setKey(T information) {
		this.key = information;
	}

	public long getIndexInMasterFile() {
		return indexInMasterFile;
	}

	public void setIndexInMasterFile(long indexInMasterFile) {
		this.indexInMasterFile = indexInMasterFile;
	}
	@Override
	public String toString() {
		return String.format("  %s indexMasterFile %d", key,indexInMasterFile);
	}
}
