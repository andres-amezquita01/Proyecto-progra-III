package binarySearchTree;


public class MyBSTNode<T> {
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
