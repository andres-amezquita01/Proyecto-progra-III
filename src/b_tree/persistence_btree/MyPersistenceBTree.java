package b_tree.persistence_btree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import b_tree.model.CreatorGenericArray;
import b_tree.model.IConverterDatasBtree;
import b_tree.model.BtreeInformation;
import b_tree.model.NodeBtree;
/**
 * Maneja la persistencia de una estructura  Btree
 * @author Andres Felipe Amezquita Gordillo
 *
 */
public class MyPersistenceBTree<K> extends RandomAccessFile{
	private final static String MODE_FILE_READ_AND_WRITE = "rw";
	private final static int LONG_NULL = -1;
	private int keySize;//tamaño de la clave que voy a almacenar.
	private int registrySize; //tamaño completo del registro
	private IConverterDatasBtree<K> iConvert;
	public final int HEADER_SIZE = Long.BYTES + Byte.BYTES + Integer.BYTES + Long.BYTES;
	
	public void recordHeader(long indexRoot, byte order, int keySize) throws IOException {
		this.seek(0);
		this.writeLong(indexRoot);;
		this.writeByte(order);
		this.writeInt(keySize);
		this.writeLong(0);//Numero de nodos que tiene el archivo de indices
	}
	
	public MyPersistenceBTree(String pathFile, IConverterDatasBtree<K> iConvert) throws FileNotFoundException  {
		super(new File(pathFile),MODE_FILE_READ_AND_WRITE);
		this.iConvert = iConvert;
	}
	/**
	 * Calcula el tamaño en byte de cada registro.
	 * @param nodeBTree nodo para saber su tamaño.
	 */
	public void calculateRegistrySize(NodeBtree<K> nodeBTree) {
		this.keySize = iConvert.sizeKey();
		this.registrySize = (keySize * nodeBTree.getInformation().length) + (Long.BYTES* nodeBTree.getInformation().length)
						  +(Long.BYTES * nodeBTree.getChildren().length) +Byte.BYTES
						  + Long.BYTES;//parent
	}
	/**
	 * Graba un nuevo nodo de un arbol BTree en un archivo binario.
	 * @param nodeBTree Objeto que vamos a grabar.
	 * @param isNewNode el nodo a grabar es nuevo, lo agrega de ultimas.(append)
	 * @throws IOException 
	 */
	public long recordNewNode(NodeBtree<K> nodeBTree) throws IOException {
			this.keySize = iConvert.sizeKey();
			this.registrySize = (keySize * nodeBTree.getInformation().length) + (Long.BYTES* nodeBTree.getInformation().length)
							  +(Long.BYTES * nodeBTree.getChildren().length) +Byte.BYTES + Long.BYTES +Byte.BYTES
							  + Long.BYTES;//parent

			long recordIndex = this.length() / registrySize;
			this.seek(this.length());

			//grabamos las claves
			for (int i = 0; i < nodeBTree.getInformation().length; i++) {
				if(nodeBTree.getInformation()[i] != null) {
					this.write(iConvert.keyToByte(nodeBTree.getInformation()[i].getKey()));
				}else {
					this.write(iConvert.keyToByte(null));
				}
			}
			//grabamos los indices que tienen en el archivo maestro las claves.
			for (int i = 0; i < nodeBTree.getInformation().length; i++) {
				if(nodeBTree.getInformation()[i] != null) {
					this.writeLong(nodeBTree.getInformation()[i].getAddress());//direccion o localizacion.
				}else {
					this.writeLong(LONG_NULL);
				}
			}
			//grabamos los hijos de cada clave.
			for (int i = 0; i < nodeBTree.getChildren().length; i++) {
				this.writeLong(nodeBTree.getChildren()[i]);//childs[i].idLocation?
			}
			//Grabamos el numero de hijos que tiene.
			this.writeByte(nodeBTree.getNumberOfChildrenNode());
			this.writeLong(recordIndex);//posicion donde fue guardado.
			this.writeByte(nodeBTree.getNumberOfKeys());
			this.writeLong(nodeBTree.getParent());//padreeeeeeeeeeeeeee
			setNumberOfNodes(getNumberOfNodes()+1);
		return recordIndex;
	}
	/**
	 * Reescribe  un nodo de un arbol BTree en un archivo binario.
	 * @param index donde queremos grabar.
	 * @param nodeBTree Objeto que vamos a grabar.
	 * @throws IOException 
	 */
	public void recordByIndex(long index,NodeBtree<K> nodeBTree) throws IOException {
		//grabarla una unica vez
		this.keySize = iConvert.sizeKey();
		registrySize = (keySize * nodeBTree.getInformation().length) + (Long.BYTES* nodeBTree.getInformation().length)
						  + (Long.BYTES * nodeBTree.getChildren().length) +Byte.BYTES + Long.BYTES+Byte.BYTES
						  + Long.BYTES;//parent

		this.seek((registrySize * index) + HEADER_SIZE);//+ tamaño header
		for (int i = 0; i < nodeBTree.getInformation().length; i++) {
			if(nodeBTree.getInformation()[i] != null) {
				this.write(iConvert.keyToByte(nodeBTree.getInformation()[i].getKey()));
			}else {
				this.write(iConvert.keyToByte(null));
			}
		}
		//grabamos los indices que tienen en el archivo maestro las claves.
		for (int i = 0; i < nodeBTree.getInformation().length; i++) {
			if(nodeBTree.getInformation()[i] != null) {
				this.writeLong(nodeBTree.getInformation()[i].getAddress());//direccion o localizacion.
			}else {
				this.writeLong(LONG_NULL);
			}
		}
		//grabamos los hijos de cada clave.
		for (int i = 0; i < nodeBTree.getChildren().length; i++) {
			this.writeLong(nodeBTree.getChildren()[i]);//childs[i].idLocation?
		}
		this.writeByte(nodeBTree.getNumberOfChildrenNode());//grabamos cuantos hijos tiene.
		this.writeLong(index);//posicion donde esta.
		this.writeByte(nodeBTree.getNumberOfKeys());//grabamos la cantidad de llaves que contiene.
		this.writeLong(nodeBTree.getParent());//padreeeeeeeeeeeeeee

	}
	public NodeBtree<K> readIndex(long index, int order) throws IOException {
		this.keySize = iConvert.sizeKey();
		this.registrySize = (keySize * (order)) + (Long.BYTES*(order))
				  		  + (Long.BYTES * (order +1)) +Byte.BYTES + Long.BYTES+Byte.BYTES
						  + Long.BYTES;//parent

		this.seek((index * this.registrySize) + HEADER_SIZE);
		@SuppressWarnings("unchecked")
		BtreeInformation<K>[] information = new CreatorGenericArray<>(BtreeInformation[].class, order).getGenericArray();
		for (int i = 0; i < order; i++) {
			byte[] keysByte = new byte[iConvert.sizeKey()];//si es string toca de 30 y no multiplicar *2.
			for (int j = 0; j < keysByte.length; j++) {
				keysByte[j] = this.readByte();
			}
			information[i] = new BtreeInformation<K>(iConvert.byteToKey(keysByte), -1);
		}
		
		long indexex =  -1;
		for (int i = 0; i < order; i++) {
			indexex = this.readLong();
			information[i].setAddress(indexex);
		}
		long[] childrenss = new long[order+1];
		for (int i = 0; i < order+1; i++) {
			childrenss[i] = this.readLong();
		}
		byte numberOfChilds = this.readByte();
		long indexPositionNode = this.readLong();
		byte numberOfKeys = this.readByte();
		long parent = this.readLong();//-----------parent
		NodeBtree<K> node = new NodeBtree<>(information);
		node.setNumberOfChildrenNode(numberOfChilds);
		node.setChildren(childrenss);
		node.setIndex(indexPositionNode);
		node.setNumberOfKeys(numberOfKeys);
		node.setParent(parent);
		return node;
	}
	
	public long getIndexRoot() throws IOException {
		this.seek(0);
		return this.readLong();
	}
	public byte getOrder() throws IOException {
		this.seek(0 + Long.BYTES);
		return this.readByte();
	}
	public int getKeySize() throws IOException {
		this.seek(0 + Long.BYTES + Byte.BYTES);
		return this.readInt();
	}
	
	public void setIndexRoot(long indexRoot) throws IOException {
		this.seek(0);
		this.writeLong(indexRoot);
	}
	public void  setOrder(byte order) throws IOException {
		this.seek(0 + Long.BYTES);
		this.writeByte(order);
	}
	public void setKeySize(int sizeKey) throws IOException {
		this.seek(0 + Long.BYTES + Byte.BYTES);
		this.writeInt(sizeKey);;
	}
	public long getNumberOfNodes() throws IOException {
		this.seek(0 + Long.BYTES + Byte.BYTES + Integer.BYTES);
		return this.readLong();
	}
	public void setNumberOfNodes(long numberOfNodes) throws IOException {
		this.seek(0 + Long.BYTES + Byte.BYTES + Integer.BYTES);
		this.writeLong(numberOfNodes);
	}
}







