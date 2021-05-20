package binarySearchTree;

import java.io.Serializable;

/**
 * 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *clase que contiene el nodo de la estructura del arbol binario de busqueda BST
 * @param <T>
 */
public class MyBSTNode<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Information<T> information;
	protected long leftSon;
	protected long rightSon;
	protected long index;
	/**
	 * Crea un nodo hoja.
	 * @param information
	 */
	public MyBSTNode(Information<T> information) {
		this.information = information;
		this.leftSon = -1;
		this.rightSon = -1;
		this.index = -1;
	}
	/**
	 * Crea un nodo con hijos.
	 * @param information info  a añadir.
	 * @param leftSon hijo izquierdo.
	 * @param rightSon hijo derecho.
	 */
	public MyBSTNode(Information<T> information, long leftSon, long rightSon) {
		this.information = information;
		this.leftSon = leftSon;
		this.rightSon = rightSon;
	}
	
	public Information<T> getInformation() {
		return information;
	}
	public long getRightSon() {
		return rightSon;
	}
	public long getLeftSon() {
		return leftSon;
	}
	public long getIndex() {
		return index;
	}
	public void setInformation(Information<T> information) {
		this.information = information;
	}
	public void setRightSon(long rightSon) {
		this.rightSon = rightSon;
	}
	public void setLeftSon(long leftSon) {
		this.leftSon = leftSon;
	}
	public void setIndex(long index) {
		this.index = index;
	}
	@Override
	public String toString() {
		return String.format("index %d key %s leftSon %d rightSon %d", index, information, leftSon, rightSon);
	}
}
