package binarySearchTree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import persistence.MyPersistenceBinarytree;

public class MyBinarySearchTree<T> {
	private MyBSTNode<T> root;
	private Comparator<T> comparator;
	private MyPersistenceBinarytree<T> bTreePersistence;
	public MyBinarySearchTree(String name,Comparator<T> comparator, IConverterDatas<T> iConverterDatas) throws FileNotFoundException {
		this.bTreePersistence = new MyPersistenceBinarytree<>(name, iConverterDatas);
		this.comparator = comparator;
	}
	public void add(T information) throws IOException,Exception {
		if(this.root == null) {
			root = new MyBSTNode<T>(information);
			bTreePersistence.record(root);
		}else {
			this.root = bTreePersistence.readByIndex(0);
			add(information, this.root);
		}
	}
	
	
	public MyBSTNode<T> add(T information,MyBSTNode<T> father) throws Exception{
		if(comparator.compare(information, father.information) != 0) {//no esta duplicado
			if (comparator.compare(information, father.information) > 0) {//si el nuevo elemento es mayor al padre
				if (father.rightSon == -1) {//si el hijo derecho es null
					father.rightSon = bTreePersistence.record(new MyBSTNode<>(information));//le grabamos al padre la posicion del nuevo hijo derecho.
					bTreePersistence.recordByIndex(father.index, father);//reescribimos al padre con el indice del hijo derecho modificado
				}else {
					add(information,bTreePersistence.readByIndex(father.rightSon));//nos vamos por la derecha.
				}
			}else if (comparator.compare(information, father.information) < 0) {//si el nuevo elemento es menor al padre.
				if (father.leftSon == -1) {//si el hijo izquierdo es null
					father.leftSon =bTreePersistence.record(new MyBSTNode<>(information));//grabamos al hijo izquierdo y le damos el indice donde quedo guardado al padre.
					bTreePersistence.recordByIndex(father.index, father);//reescribimos al padre con el indice del hijo izquierdo modificado.
				}else {
					add(information,bTreePersistence.readByIndex(father.leftSon));//sino nos vamos por el hij izquierdo.
				}
			}
		}else {
			throw new Exception("Elemento Duplicado");
		}
		return father;
	}
	public ArrayList<T> traverseInOrder() throws IOException{
		ArrayList<T> aux = new ArrayList<T>();
		this.root = bTreePersistence.readByIndex(0);
		if(this.root != null) {
			inOrder(this.root,aux);
		}
	    return aux;
	}
	private void inOrder(MyBSTNode<T> node,ArrayList<T> list)throws IOException {
	    if (node != null) {
	    	if(node.leftSon!=-1)inOrder(bTreePersistence.readByIndex(node.leftSon),list);
	        list.add(node.information);
	        if(node.rightSon != -1)inOrder(bTreePersistence.readByIndex(node.rightSon),list);
	    }
	    
	}
	public MyBSTNode<T> read(long index) throws IOException{
		return bTreePersistence.readByIndex(index);
	}
}
