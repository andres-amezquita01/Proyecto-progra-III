package binarySearchTree;

import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;
import persistence.MyPersistenceBinarytree;
/**
 * 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *Clase que me permite manejar mi arbol binario de busqueda el cual al manejar una lista de indices
 *es mucho mas optimo que trabajar sobre el archivo maestro
 * @param <T>
 */
public class MyBinarySearchTree<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private MyBSTNode<T> root;
	private Comparator<T> comparator;
	private MyPersistenceBinarytree<T> myPersistenceBinaryTree;
	/**
	 * Constructor para un nuevo BST
	 * @param name
	 * @param comparator
	 * @param iConverterDatas
	 * @throws IOException 
	 */
	public MyBinarySearchTree(String name,Comparator<T> comparator, IConverterDatas<T> iConverterDatas) throws IOException {
		this.myPersistenceBinaryTree = new MyPersistenceBinarytree<>(name, iConverterDatas);
		this.comparator = comparator;
		if(this.myPersistenceBinaryTree.length() == 0) {
			this.myPersistenceBinaryTree.writeHeader(0, iConverterDatas.sizeKey());
		}
	}
	/**
	 * Añade una llave y valor al arbol binario
	 * @param information informacion a agregar
	 * @throws IOException excepion de archivos
	 * @throws Exception exeption la cual escribe los posibles errores en mi logger
	 */
	public void add(Information<T> information) throws IOException,Exception {
		if(this.myPersistenceBinaryTree.length() == MyPersistenceBinarytree.SIZE_HEADER) {
			this.root = new MyBSTNode<T>(information);
			long indexRoot = myPersistenceBinaryTree.record(root);
			this.myPersistenceBinaryTree.setIndexRoot(indexRoot);
		}else {
			this.root = myPersistenceBinaryTree.readByIndex(this.myPersistenceBinaryTree.getIndexRoot());
			add(information, this.root);
		}
	}
	
	/**
	 * Añade recursivamente una informacion al arbol binario
	 * @param information informacion a agregar
	 * @param father nodo padre de referencia donde se agregara la informacion anterior
	 * @return me devuleve un nodo con la informacion agregada
	 * @throws Exception manejo la exepcion posible para escribirla en mi logger
	 */
	public MyBSTNode<T> add(Information<T> information,MyBSTNode<T> father) throws Exception{
		if(comparator.compare(information.key, father.information.key) != 0) {//no esta duplicado
			if (comparator.compare(information.key, father.information.key) > 0) {//si el nuevo elemento es mayor al padre
				if (father.rightSon == -1) {//si el hijo derecho es null
					father.rightSon = myPersistenceBinaryTree.record(new MyBSTNode<>(information));//le grabamos al padre la posicion del nuevo hijo derecho.
					myPersistenceBinaryTree.recordByIndex(father.index, father);//reescribimos al padre con el indice del hijo derecho modificado
				}else {
					add(information,myPersistenceBinaryTree.readByIndex(father.rightSon));//nos vamos por la derecha.
				}
			}else if (comparator.compare(information.key, father.information.key) < 0) {//si el nuevo elemento es menor al padre.
				if (father.leftSon == -1) {//si el hijo izquierdo es null
					father.leftSon =myPersistenceBinaryTree.record(new MyBSTNode<>(information));//grabamos al hijo izquierdo y le damos el indice donde quedo guardado al padre.
					myPersistenceBinaryTree.recordByIndex(father.index, father);//reescribimos al padre con el indice del hijo izquierdo modificado.
				}else {
					add(information,myPersistenceBinaryTree.readByIndex(father.leftSon));//sino nos vamos por el hij izquierdo.
				}
			}
		}else {
			throw new Exception("Elemento Duplicado");
		}
		return father;
	}
	
	
	/**
	 * metodo fachada de busqueda basado en el metodo siguiente
	 * @param key llave la cual quiero buscar en mi BSF
	 * @return devuelvo la informacion concerniente a esa llave 
	 * @throws IOException manejo la exepcion correspondiente al manejo de archivos
	 */
	public Information<T> search(T key) throws IOException {
		if(myPersistenceBinaryTree.length() != MyPersistenceBinarytree.SIZE_HEADER) {
		Information<T> found = search(key, this.myPersistenceBinaryTree.readByIndex(this.myPersistenceBinaryTree.getIndexRoot()));
		if(found != null) {
			return found;
		}else {
			return null;
		}
		}else {
			return null;
		}
		
	}
	
	/**
	 * metodo de busqueda implementado para mi BSF donde aplico la recursividad propuesta en sesiones anteriroes
	 * para ahora implementarlo con manipulacion de archivos de acceso aleatorio 
	 * @param key llave que quiero buscar 
	 * @param father nodo padre desde donde quiero empezar a buscar
	 * @return devuelvo una informacion si encuentro la llave buscada
	 * @throws IOException manejo la exepcion del manejo de archivos
	 */
	private Information<T> search(T key,MyBSTNode<T> father) throws IOException{
		Information<T> information = father.information;
		if(father.index != -1) {
			if(comparator.compare(father.information.key,key) == 0) {
				information = father.information;
			}
			if(comparator.compare(key, father.information.key) > 0 ) {
				if(father.rightSon!=-1) {
					information = search(key, this.myPersistenceBinaryTree.readByIndex(father.rightSon));
				}else {
					return null;
				}
			}
			if(comparator.compare(key, father.information.key)  < 0 ) {
				if(father.leftSon != -1) {
					information = search(key, this.myPersistenceBinaryTree.readByIndex(father.leftSon));
				}else {
					return null;
				}
			}
			return information;
		}else {
			return null;
		}
	}

	
	/**
	 * metodo que me lee un nodo de un archivo basandose en su respectivo indice
	 * @param index indice del cual se quiere obtener el nodo
	 * @return devuelve el nodo en caso de que lo encuentre
	 * @throws IOException excepcion del manejo de archivos
	 */
	public MyBSTNode<T> read(long index) throws IOException{
		return myPersistenceBinaryTree.readByIndex(index);
	}
	public long getNumberOfNodes() throws IOException {
		return myPersistenceBinaryTree.getNumberOfNodes();
	}
	

	
	
}
