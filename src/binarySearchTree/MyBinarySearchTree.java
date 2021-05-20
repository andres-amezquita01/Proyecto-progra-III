package binarySearchTree;

import java.io.IOException;
import java.io.Serializable;
import java.util.Comparator;

import model.MySimpleList;
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
	
	/**
	 * Elimina un elemento del arbol desreferenciandolo en persistencia.
	 * @param information informacion a eliminar
	 * @return true si la elimino, false si no.
	 * @throws IOException
	 */
	public boolean delete(T information) throws IOException {
		if(this.myPersistenceBinaryTree.length() == MyPersistenceBinarytree.SIZE_HEADER) {
			return false;
		}else {
			this.root = myPersistenceBinaryTree.readByIndex(this.myPersistenceBinaryTree.getIndexRoot());
			MyBSTNode<T> aux = root;
			MyBSTNode<T> father = root;
			boolean isLeft = true;
			
			while(comparator.compare(information, aux.information.key) != 0) {
				father = aux;
				if(comparator.compare(information, aux.information.key) < 0) {
					isLeft = true;
					//validar que no sea nulo
					if(aux.leftSon != -1) {
						aux = this.myPersistenceBinaryTree.readByIndex(aux.leftSon);
						this.myPersistenceBinaryTree.recordByIndex(aux.index, aux);
					}else {
						aux = null;
					}
				}else {
					isLeft = false;
					//validar que no sea nulo
					if(aux.rightSon != -1) {
						aux = this.myPersistenceBinaryTree.readByIndex(aux.rightSon);
						this.myPersistenceBinaryTree.recordByIndex(aux.index, aux);
					}else {
						aux = null;
					}
					
				}
				if(aux == null) {
					return false;
				}
			}//fin while
			
			if(aux.leftSon == -1 && aux.rightSon == -1) {//es hoja
				if(aux.leftSon == root.index) {//mirar bien si toca leer el left para comparar
					root = null;
				}else if(isLeft) {
					father.leftSon = -1;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}else {
					father.rightSon = -1;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}
			}else if(aux.rightSon == -1) {
				if(aux.index == root.index) {
//					root.index = aux.leftSon;//tocaria cambiar el indice
					root = this.myPersistenceBinaryTree.readByIndex(aux.leftSon);
//					this.myPersistenceBinaryTree.recordByIndex(root.index, root);
//					myPersistenceBinaryTree.setIndexRoot(root.index);
				}else if(isLeft) {
					father.leftSon = aux.leftSon;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}else {
					father.rightSon = aux.leftSon;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}
			}else if(aux.leftSon == -1) {
				if(aux.index == root.index) {
//					root.index = aux.rightSon;//leer y cambiar
					root = this.myPersistenceBinaryTree.readByIndex(aux.rightSon);
//					this.myPersistenceBinaryTree.recordByIndex(root.index, root);
//					myPersistenceBinaryTree.setIndexRoot(root.index);
				}else if(isLeft) {
					father.leftSon = aux.rightSon;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}else {
					father.rightSon = aux.rightSon;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}
			}else {
				MyBSTNode<T> nodeReplace = getNodeReplace(aux);
				if(aux.index == root.index) {
//					root.index = nodeReplace.index;//leer y cambiar
					root = this.myPersistenceBinaryTree.readByIndex(nodeReplace.index);
//					this.myPersistenceBinaryTree.recordByIndex(root.index, root);
//					myPersistenceBinaryTree.setIndexRoot(root.index);
				}else if(isLeft) {
					father.leftSon = nodeReplace.index;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
					
				}else {
					father.rightSon = nodeReplace.index;
					this.myPersistenceBinaryTree.recordByIndex(father.index, father);
				}
				nodeReplace.leftSon = aux.leftSon;
				this.myPersistenceBinaryTree.recordByIndex(nodeReplace.index, nodeReplace);

			}
			return true;
		}
	}
	private MyBSTNode<T> getNodeReplace(MyBSTNode<T> nodeReplace) throws IOException{
		MyBSTNode<T> replaceFather = nodeReplace;
		MyBSTNode<T> replace = nodeReplace;
		MyBSTNode<T> aux = this.myPersistenceBinaryTree.readByIndex(nodeReplace.index);
		while(aux != null) {
			replaceFather = replace;
			replace = aux;
			if(aux.leftSon != -1) {
				aux = this.myPersistenceBinaryTree.readByIndex(aux.leftSon);
			}else {
				aux = null;
			}
		}
		if(replace.index != nodeReplace.rightSon) {
			replaceFather.leftSon = replace.rightSon;
			replace.rightSon = nodeReplace.rightSon;
			this.myPersistenceBinaryTree.recordByIndex(replaceFather.index, replaceFather);
			this.myPersistenceBinaryTree.recordByIndex(replace.index, replace);

			//grabar---
		}
		return replace;
	}
	/**
	 * Recorre el arbol inOrden 
	 * @return indices de referencia de los registros
	 * @throws IOException
	 */
	public MySimpleList<Long> traverseInOrder() throws IOException{
		MySimpleList<Long> aux = new MySimpleList<Long>();
		
		if(this.myPersistenceBinaryTree.length() != MyPersistenceBinarytree.SIZE_HEADER) {
			this.root = myPersistenceBinaryTree.readByIndex(myPersistenceBinaryTree.getIndexRoot());
			inOrder(this.root,aux);
		}
	    return aux;
	}
	/**
	 * Metodo para recorrer inOrder el arbol
	 * @param node nodo padre
	 * @param list lista para guardar las referencias.
	 * @throws IOException
	 */
	private void inOrder(MyBSTNode<T> node,MySimpleList<Long> list)throws IOException {
	    if (node != null) {
	    	if(node.leftSon!=-1)inOrder(myPersistenceBinaryTree.readByIndex(node.leftSon),list);
	        	list.add(node.information.indexInMasterFile);
	        if(node.rightSon != -1)inOrder(myPersistenceBinaryTree.readByIndex(node.rightSon),list);
	    }
	    
	}
	/**
	 * Metodo para recorrer inOrder el arbol
	 * @throws IOException
	 */
	public MySimpleList<T> traverseInOrderKeys() throws IOException{
		MySimpleList<T> aux = new MySimpleList<T>();
		
		if(this.myPersistenceBinaryTree.length() != MyPersistenceBinarytree.SIZE_HEADER) {
			this.root = myPersistenceBinaryTree.readByIndex(myPersistenceBinaryTree.getIndexRoot());
			inOrderKeys(this.root,aux);
		}
	    return aux;
	}
	/**
	 * Metodo para recorrer inOrder el arbol
	 * @param node nodo padre
	 * @param list lista para guardar las llaves.
	 * @throws IOException
	 */
	private void inOrderKeys(MyBSTNode<T> node,MySimpleList<T> list)throws IOException {
//	    System.out.println(node);
		if (node != null) {
	    	if(node.leftSon!=-1)inOrderKeys(myPersistenceBinaryTree.readByIndex(node.leftSon),list);
	        	list.add(node.information.key);
	        if(node.rightSon != -1)inOrderKeys(myPersistenceBinaryTree.readByIndex(node.rightSon),list);
	    }
	    
	}
	
	
	
}
