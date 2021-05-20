package b_tree.model;

import java.io.IOException;
import java.util.Comparator;
import b_tree.persistence_btree.MyPersistenceBTree;


/**
 *Estructura generica de un arbol B  en persistencia a traves
 *de RandomAccesFile
 * @author Andres Felipe Amezquita Gordillo
 * @param <K> Objeto generico de la llave de los nodos del btree.
 */
public class BTree <K>{
	
	private NodeBtree<K> root;//raiz del arbol.
	private Comparator<K> comparator;
	private byte order;
	@SuppressWarnings("unused")
	private IConverterDatasBtree<K> iConvert;
	private MyPersistenceBTree<K> myBTreePersistence;
	private long indexRoot;
	
	//-------CONSTRUCTOR PARA CREAR DE 0 UN BTREE------
	/**
	 * Constructor de un  NUEVO Btree.
	 * @param comparator criterio de ordenamiento del Btree
	 * @param order Grado u orden del Btree
	 * @param fileName ruta donde se desea guardar el BTree
	 * @throws IOException 
	 */
	public BTree(Comparator<K> comparator, byte order, String fileName, IConverterDatasBtree<K> iConvert) throws IOException{
		this.comparator = comparator;
		this.order = order;
		this.root = null;
		this.myBTreePersistence = new MyPersistenceBTree<>(fileName, iConvert);
		this.iConvert = iConvert;
		this.indexRoot = 0;
		if(this.myBTreePersistence.length() ==0) {
			this.myBTreePersistence.recordHeader(indexRoot, order, iConvert.sizeKey());	
		}
	}
	//------cuando el btree ya fue creado-------
	/**
	 * Constructor que se usa cuando ya FUE CREADO previamente el
	 * archivo btree.
	 * @param fileName nombre del archivo
	 * @param iConvert interface que nos enseñe a convertir.
	 * @throws IOException exepcion.
	 */
	public BTree(String fileName,IConverterDatasBtree<K> iConvert,Comparator<K> comparator) throws IOException {
		this.myBTreePersistence = new MyPersistenceBTree<>(fileName, iConvert);
		this.comparator = comparator;
		this.iConvert = iConvert;
		if(this.myBTreePersistence.length() != 0) {
			this.indexRoot = this.myBTreePersistence.getIndexRoot();
			this.order = this.myBTreePersistence.getOrder();
			this.root = this.myBTreePersistence.readIndex(indexRoot, order);
		}
		
	}

	/**
	 * Valida si la estructura esta vacia.
	 * @return
	 * @throws IOException 
	 */
	public boolean isEmpty() throws IOException {
		return this.myBTreePersistence.length() == 0;
	}
	
	
	@SuppressWarnings("unchecked")
	private NodeBtree<K> createNode() {
		@SuppressWarnings("rawtypes")
		CreatorGenericArray<BtreeInformation> infoArray = new CreatorGenericArray<>(BtreeInformation[].class, order);
		return new NodeBtree<K>(infoArray.getGenericArray());
	}
	
	/**
	 * Lee un nodo en la ubicacion indicada
	 * es un metodo para probar en el test ya que esta
	 * es una funcion de la clase MyPersistenceBtree
	 * @param index
	 * @return
	 * @throws IOException
	 */
	public NodeBtree<K> read(long index) throws IOException {
		return myBTreePersistence.readIndex(index, order);
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	public void close() throws IOException {
		this.myBTreePersistence.close();
	}
	
	/**
	 * Añade una informacion(clave, direccion en masterFile) a el btree.
	 * @param information informacion a añadir key/value
	 * @throws IOException
	 */
	public void add(BtreeInformation<K> information) throws IOException {
		if(myBTreePersistence.length() == myBTreePersistence.HEADER_SIZE) {
			this.root = createNode();
			this.root.addKey(information, comparator);
			this.indexRoot = myBTreePersistence.recordNewNode( this.root);
			this.myBTreePersistence.setIndexRoot(indexRoot);
		}else{
			this.indexRoot = this.myBTreePersistence.getIndexRoot();
			this.root = myBTreePersistence.readIndex(indexRoot, order);
			NodeBtree<K> node = root;
			while(node != null) {
				if(node.numberOfChildrenNode == 0) {
					node.addKey(information, comparator);
					this.myBTreePersistence.recordByIndex(node.index, node);//grabamos
					if(node.numberOfKeys + 1 <= order) {
						break;
					}
					split(node);//________________________
					this.myBTreePersistence.recordByIndex(node.index, node);//grabamos
					break;
				}
				K lesser = node.getInformation()[0].key;
				if(comparator.compare(information.key, lesser) <= 0) {
					node = this.myBTreePersistence.readIndex(node.getChildren()[0], order);
//					this.bTreePersistence.recordByIndex(node.index, node);//grabamos
					// se graba ?????
					continue;
				}
				int numberofKeys = node.numberOfKeys;
				int last = numberofKeys -1;
				K greater = node.getInformation()[last].key;
				if(comparator.compare(information.key, greater) > 0) {
					node = this.myBTreePersistence.readIndex(node.getChildren()[numberofKeys], order);
//					this.bTreePersistence.recordByIndex(node.index, node);//grabamos
					//se graba?????
					continue;
				}
				for (int i = 1; i < node.numberOfKeys; i++) {
					K prev = node.getInformation()[i-1].key;
					K next = node.getInformation()[i].key;
					if (comparator.compare(information.key, prev) > 0 && comparator.compare(information.key, next) <= 0) {
						node = this.myBTreePersistence.readIndex(node.getChildren()[i], order);
						break;
					}
				}
				this.myBTreePersistence.recordByIndex(node.index, node);//grabamos
			}
		}
	}
	/**
	 * Realiza el proceso de split del arbol, divide y reestructura el btree.
	 * @param nodeToSplit nodo a dividir.
	 * @throws IOException 
	 */
	private void split(NodeBtree<K> nodeToSplit) throws IOException {
		NodeBtree<K> node = nodeToSplit;
		int numberOfKeys = node.numberOfKeys;
		int medianIndex = numberOfKeys/2;
		BtreeInformation<K> medianVale = node.getInformation()[medianIndex];
		//-----HIJO IZQUIERDO-------
		NodeBtree<K> left = createNode();
		for (int i = 0; i < medianIndex; i++) {
			left.addKey(node.getInformation()[i], comparator);//revisar bien
		}
		if(node.numberOfChildrenNode > 0) {
			for (int i = 0; i < medianIndex; i++) {
				left.addChildren(node.getChildren()[i]);
			}
		}
		//-----HIJO DERECHO-------
		NodeBtree<K> right = createNode();
		for (int i = medianIndex +1; i < numberOfKeys; i++) {
			right.addKey(node.getInformation()[i], comparator);//revisar bien
		}
		if(node.numberOfChildrenNode > 0 ) {
			for (int i = medianIndex +1; i <node.numberOfChildrenNode; i++) {
				right.addChildren(node.getChildren()[i]);
			}
		}
		if(node.parent == -1) {
			NodeBtree<K> newRoot = createNode();
			newRoot.addKey(medianVale, comparator);
			node.parent = this.myBTreePersistence.recordNewNode(newRoot);
//			this.root = newRoot;
			this.root = this.myBTreePersistence.readIndex(node.parent, order);
			this.indexRoot = root.index;
			this.myBTreePersistence.setIndexRoot(indexRoot);
				System.out.println("nueva raiz " + myBTreePersistence.getIndexRoot());
			node = root;
			node.addChildren(this.myBTreePersistence.recordNewNode(left));
			node.addChildren(this.myBTreePersistence.recordNewNode(right));
			this.myBTreePersistence.recordByIndex(root.index, root);
			this.myBTreePersistence.recordByIndex(node.index, node);//estaba commit
		}else {
			NodeBtree<K> parent = this.myBTreePersistence.readIndex(node.parent, order);
			parent.addKey(medianVale, comparator);
			parent.removeChild(node.index);
			parent.addChildren(this.myBTreePersistence.recordNewNode(left));
			parent.addChildren(this.myBTreePersistence.recordNewNode(right));
			this.myBTreePersistence.recordByIndex(parent.index, parent);
				if(parent.numberOfChildrenNode > order) {
					split(parent);
				}
		}
	}
	

//---------version medio funcional del proceso split--------------
//	private void split(NodeBtree<K> nodeToSplit) throws IOException {
//		NodeBtree<K> node = nodeToSplit;
//		int numberOfKeys = node.numberOfKeys;
//		int medianIndex = numberOfKeys/2;
//		Information<K> medianVale = node.getInformation()[medianIndex];
//		long indexLef= 0;
//		long indexRight = 0;
//		NodeBtree<K> left = createNode();
//		left.parent = -1;
//		for (int i = 0; i < medianIndex; i++) {
//			left.addKey(node.getInformation()[i], comparator);//revisar bien
//		}
//		
//		if(node.numberOfChildrenNode > 0) {
//			for (int i = 0; i < medianIndex; i++) {
//				NodeBtree<K> c = this.bTreePersistence.readIndex(node.getChildren()[i], order);
//				left.addChildren(this.bTreePersistence.recordNewNode(c));
//			}
//		}
//		
//		NodeBtree<K> right = createNode();
//		right.parent = -1;
//		for (int i = medianIndex +1; i < numberOfKeys; i++) {
//			right.addKey(node.getInformation()[i], comparator);//revisar bien
//		}
//		if(node.numberOfChildrenNode > 0 ) {
//			for (int i = medianIndex +1; i <node.numberOfChildrenNode; i++) {
//				NodeBtree<K> c = this.bTreePersistence.readIndex(node.getChildren()[i], order);
//				right.addChildren(this.bTreePersistence.recordNewNode(c));
//			}
//		}
//		
//		if(node.parent == -1) {
//			NodeBtree<K> newRoot = createNode();
//			newRoot.parent = -1;
//			newRoot.addKey(medianVale, comparator);
//			node.parent = this.bTreePersistence.recordNewNode(newRoot);
//			this.root = newRoot;
//			node = root;
//			indexLef = this.bTreePersistence.recordNewNode(left);
//			node.addChildren(this.bTreePersistence.recordNewNode(left));
//			node.addChildren(this.bTreePersistence.recordNewNode(right));
//		}else {
//			NodeBtree<K> parent = this.bTreePersistence.readIndex(node.parent, order);
//			parent.addKey(medianVale, comparator);
//			parent.removeChild(node.index);
//			parent.addChildren(this.bTreePersistence.recordNewNode(left));
//			parent.addChildren(this.bTreePersistence.recordNewNode(right));
//			if(parent.numberOfChildrenNode > order)split(parent);
//		}
//	
//	}
	/**
	 * Obtiene del header del archivo binario el indice de la raiz.
	 * @return
	 * @throws IOException
	 */
	public long getIndexRoot() throws IOException {
		return this.myBTreePersistence.getIndexRoot();
	}
	
	
	/**
	 * Buscar la llave indicada dentro del Btree
	 * @param key llave a buscar
	 * @return
	 * @throws IOException
	 */
	public NodeBtree<K> search(K key) throws IOException{
		return searchAux(this.myBTreePersistence.readIndex(this.myBTreePersistence.getIndexRoot(), order), key);
//		return searchAux(this.bTreePersistence.readIndex(1, order), information);
	}
	/**
	 * Metodo auxiliar de el metodo search(K key)
	 * realiza la logica necesaria para ubicar la llave indicada
	 * @param rootNode
	 * @param key
	 * @return
	 * @throws IOException
	 */
	private NodeBtree<K> searchAux(NodeBtree<K> rootNode, K key ) throws IOException{
		int i = 0;
		if(rootNode == null) {
			return null;
		}
		for ( i = 0; i < rootNode.numberOfKeys; i++) {
			if(comparator.compare(key, rootNode.information[i].key) < 0) {
				break;
			}
			if(comparator.compare(key, rootNode.information[i].key) == 0) {
				return rootNode;
			}
		}
		if(rootNode.numberOfChildrenNode == 0) {
			return null;
		}else {
			return searchAux(this.myBTreePersistence.readIndex(rootNode.getChildren()[i], order), key);
		}
	}
	/**
	 * Validar orden con el que se grabó
	 * almacenar tamaño de la clave
	 * @throws IOException 
	 */
	
	/**
	 * Obtiene el numero de nodos que tiene el archivo de indices de 
	 * el btree.
	 * @return
	 * @throws IOException
	 */
	public long getNumberOfNodes() throws IOException {
		return this.myBTreePersistence.getNumberOfNodes();
	}
	/**
	 * Remueve del arbol una determinada entrada.
	 * @param key clave a remover
	 * @param index posicion de la clave en el archivo maestro.
	 */
	public void remove(K key, long index) {
		
	}
	
	/**
	 * Va a buscar una clave en el arbol.
	 * @param key LLave a buscar su ubicacion
	 * @return Las posiciones donde se encontro la clave en el archivo maestro porque puede estar repetida la clave.
	 */
	public long[] searchIndexes(K key) {
		return new long[] {};
	}
}




















