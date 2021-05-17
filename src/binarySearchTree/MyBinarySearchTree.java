package binarySearchTree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import persistence.MyPersistenceBinarytree;

public class MyBinarySearchTree<T> {
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
	 * @param information
	 * @throws IOException
	 * @throws Exception
	 */
	public void add(Information<T> information) throws IOException,Exception {
		if(this.myPersistenceBinaryTree.length() == myPersistenceBinaryTree.SIZE_HEADER) {
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
	 * @param information
	 * @param father
	 * @return
	 * @throws Exception
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
	public Information<T> search(T key) throws IOException {
		Information<T> found = search(key, this.myPersistenceBinaryTree.readByIndex(this.myPersistenceBinaryTree.getIndexRoot()));
		if(found != null) {
			return found;
		}else {
			return null;
		}
		
	}
	private Information<T> search(T key,MyBSTNode<T> father) throws IOException{
		MyBSTNode<T> aux = father;
		Information<T> information = father.information;
		if(father.index != -1) {
			if(comparator.compare(father.information.key,key) == 0) {
				information = father.information;
			}
			if(comparator.compare(key, father.information.key) > 0 ) {
				information = search(key, this.myPersistenceBinaryTree.readByIndex(father.rightSon));
			}
			if(comparator.compare(key, father.information.key)  < 0 ) {
				information = search(key, this.myPersistenceBinaryTree.readByIndex(father.leftSon));
			}
			return information;
		}else {
			return null;
		}
	}
	public ArrayList<T> traverseInOrder() throws IOException{
		ArrayList<T> aux = new ArrayList<T>();
		this.root = myPersistenceBinaryTree.readByIndex(0);
		if(this.root != null) {
			inOrder(this.root,aux);
		}
	    return aux;
	}
	private void inOrder(MyBSTNode<T> node,ArrayList<T> list)throws IOException {
	    if (node != null) {
	    	if(node.leftSon!=-1)inOrder(myPersistenceBinaryTree.readByIndex(node.leftSon),list);
	        list.add(node.information.key);
	        if(node.rightSon != -1)inOrder(myPersistenceBinaryTree.readByIndex(node.rightSon),list);
	    }
	    
	}
	public MyBSTNode<T> read(long index) throws IOException{
		return myPersistenceBinaryTree.readByIndex(index);
	}
	public long getNumberOfNodes() throws IOException {
		return myPersistenceBinaryTree.getNumberOfNodes();
	}
}
