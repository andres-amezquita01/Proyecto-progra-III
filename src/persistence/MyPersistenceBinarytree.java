package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBSTNode;

public class MyPersistenceBinarytree<T> extends RandomAccessFile{
//	private final static int LONG_NULL = -1;
	private int keySize;//tamaño de la clave que voy a almacenar.
	private int registrySize;
	private IConverterDatas<T> iConverterDatas;
	public static final int SIZE_REGISTRY = Long.BYTES + Long.BYTES + Long.BYTES + Long.BYTES;
	public static final int SIZE_HEADER =  Long.BYTES + Long.BYTES + Long.BYTES;
	public static final int ROOT_INDEX_HEADER = 0;
	public static final int NUMBER_NODES_INDEX_HEADER = 1;
	public static final int SIZE_KEY_INDEX_HEADER = 2;

	public void writeHeader(long indexRoot, long sizeKey) throws IOException {
		this.seek(0);
		this.writeLong(indexRoot);
		this.writeLong(0);
		this.writeLong(sizeKey);

	}
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
		this.registrySize = keySize + SIZE_REGISTRY;
		long recordIndex = this.length() / registrySize;
		this.seek(this.length());
		this.write(iConverterDatas.keyToByte(nodeBST.getInformation().geKey()));
		this.writeLong(nodeBST.getInformation().getIndexInMasterFile());
		this.writeLong(nodeBST.getLeftSon());//hijo izquierdo
		this.writeLong(nodeBST.getRightSon());
		this.writeLong(recordIndex);
		setNumberOfNodes(getNumberOfNodes()+1);
		return recordIndex;
	}
	
	public void recordByIndex(long index, MyBSTNode<T> node) throws IOException {
		this.keySize = iConverterDatas.sizeKey();
		this.registrySize = keySize + SIZE_REGISTRY;
		this.seek((registrySize*index) + SIZE_HEADER);
		this.write(iConverterDatas.keyToByte(node.getInformation().geKey()));
		this.writeLong(node.getInformation().getIndexInMasterFile());
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
		this.registrySize = keySize + SIZE_REGISTRY;
		this.seek((index*this.registrySize) + SIZE_HEADER);
		byte[] keysByte = new byte[iConverterDatas.sizeKey()];
		for (int i = 0; i < keysByte.length; i++) {
			keysByte[i] = this.readByte();
		} 
		T key = iConverterDatas.byteToKey(keysByte);
		long indexMasterFile = this.readLong();
		long leftSon = this.readLong();
		long rightSon = this.readLong();
		long position = this.readLong();
		MyBSTNode<T> myBSTNode = new MyBSTNode<T>(new  Information<T>(key, indexMasterFile));
		myBSTNode.setLeftSon(leftSon);
		myBSTNode.setRightSon(rightSon);
		myBSTNode.setIndex(position);
		return myBSTNode;
	}
	
//	public void writeHeader(long indexRoot, long sizeKey) throws IOException {
//		this.seek(0);
//		this.writeLong(indexRoot);
//		this.writeLong(0);
//		this.writeLong(sizeKey);
//
//	}
	
	public void setIndexRoot(long indexRoot) throws IOException {
		this.seek(0);
		this.writeLong(indexRoot);
	}
	
	public long getIndexRoot() throws IOException {
		this.seek(0);
		return this.readLong();
	}
	
	public void setNumberOfNodes(long numberOfNodes) throws IOException {
		this.seek(0 + Long.BYTES);
		this.writeLong(numberOfNodes);
	}
	public long getNumberOfNodes() throws IOException {
		this.seek(0 + Long.BYTES);
		return this.readLong();
	}
	public long getKeySize() throws IOException {
		this.seek(0 + Long.SIZE + Long.BYTES);
		return this.readLong();
	}
	
}
