package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import binarySearchTree.IConverterDatas;
import binarySearchTree.MyBSTNode;

public class MyPersistenceBinarytree<T> extends RandomAccessFile{
//	private final static int LONG_NULL = -1;
	private int keySize;//tamaño de la clave que voy a almacenar.
	private int registrySize;
	private IConverterDatas<T> iConverterDatas;
	
	public MyPersistenceBinarytree(String name, IConverterDatas<T> iConverterDatas) throws FileNotFoundException  {
		super(name, "rw");
		this.iConverterDatas = iConverterDatas;
	}
	
	/**
	 * Agrega un nuevo nodo en la ultima posicion del archivo.
	 * @param nodeBST nodo a grabar.
	 * @return posicion donde se grabo.
	 * @throws IOException
	 */
	public long record(MyBSTNode<T> nodeBST) throws IOException {
		this.keySize = iConverterDatas.sizeKey();
		this.registrySize = keySize + Long.BYTES + Long.BYTES + Long.BYTES;
		long recordIndex = this.length() / registrySize;
		this.seek(this.length());
		this.write(iConverterDatas.keyToByte(nodeBST.getInformation()));
		this.writeLong(nodeBST.getLeftSon());//hijo izquierdo
		this.writeLong(nodeBST.getRightSon());
		this.writeLong(recordIndex);
		return recordIndex;
	}
	
	public void recordByIndex(long index, MyBSTNode<T> node) throws IOException {
		this.keySize = iConverterDatas.sizeKey();
		this.registrySize = keySize + Long.BYTES + Long.BYTES + Long.BYTES;
		this.seek(registrySize*index);
		this.write(iConverterDatas.keyToByte(node.getInformation()));
		this.writeLong(node.getLeftSon());//hijo izquierdo
		this.writeLong(node.getRightSon());//hijo derecho.
		this.writeLong(index);//posicion donde quedo guardado.
	}
	
	/**
	 * Lee un nodo en el indice indicado.
	 * @param index indice a leer.
	 * @return nodo leido en el index.
	 * @throws IOException
	 */
	public MyBSTNode<T> readByIndex(long index) throws IOException{
		this.keySize = iConverterDatas.sizeKey();
		this.registrySize = keySize + Long.BYTES + Long.BYTES + Long.BYTES;
		this.seek(index*this.registrySize);
		byte[] keysByte = new byte[iConverterDatas.sizeKey()];
		for (int i = 0; i < keysByte.length; i++) {
			keysByte[i] = this.readByte();
		} 
		T key = iConverterDatas.byteToKey(keysByte);
		long leftSon = this.readLong();
		long rightSon = this.readLong();
		long position = this.readLong();
		MyBSTNode<T> myBSTNode = new MyBSTNode<T>(key);
		myBSTNode.setLeftSon(leftSon);
		myBSTNode.setRightSon(rightSon);
		myBSTNode.setIndex(position);
		return myBSTNode;
	}
	
	
	
	
	
	
	
	
	
}
