package b_tree.model;

import java.util.Comparator;


/**
 * Estructura generica de un nodo de la estructura BTree
 * @author Andres Felipe Amezquita Gordillo
 * @param <K> objeto generico.
 */
public  class NodeBtree<K> {

    protected BtreeInformation<K>[] information;// tamaño order -1
    protected long[] children;//tamaño order  => direccion en el archivo de persistencia de cada uno de los hijos.
    protected byte numberOfChildrenNode;
    protected long index;//posicion donde quedara guardado el nodo en el archivo de indices.+
    protected byte numberOfKeys;
    protected long parent;
    /**
     * Constructor de un nodo.
     * @param key Llave del nodo.
     * @param idLocation Indice del registro en el archivo maestro.
     * @param order orden del nodo.
     * @param iCreatorArrayBTreeNode Interfaz que crea un arreglo de tipo NodeBtree.
     * 
     * @author Andres Felipe Amezquita Gordillo
     */
    public NodeBtree(BtreeInformation<K>[] information) {
    	this.information = information;
    	this.children = new long[information.length + 1];
    	this.numberOfChildrenNode = 0;
    	this.numberOfKeys = 0;
    	this.index = -1;
    	this.parent = -1;
	}
    
    /**
     * Añade un nodo al arreglo de hijos.
     * @param node Nodo de tipo NodeBtree a añadir al arreglo de hijos.
     * @author Andres Felipe Amezquita Gordillo
     */
//    public void addNode(NodeBtree<K> node) {
//    	this.children[numberOfChildrenNode] = node;
//    	this.numberOfChildrenNode ++;
//    	
//    }
    /**
     * Aca no deberia ir un parametro de tipo nodo debido a que el nodo que se quiere añadir
     * se creo previamente en alguna posicion en persistencia, por lo tanto unicamente le pasamos
     * la referencia es decir la direccion donde se creo.
     * @param adreesNode
     */
    public void addChildren(long adressNode) {
    	if(numberOfChildrenNode +1 <= this.children.length) {
        	this.children[numberOfChildrenNode] = adressNode;
        	this.numberOfChildrenNode ++;
    	}
//    	this.children[numberOfChildrenNode] = adressNode;
//    	this.numberOfChildrenNode ++;
    	
    }
    /**
     * Añade una llave valor al arreglo
     * @param information
     * @param comparator
     */
    public void addKey(BtreeInformation<K> information, Comparator<K> comparator) {
    	if(numberOfKeys + 1 <= this.information.length) {
    		this.information[numberOfKeys] = information;
        	this.numberOfKeys++;
        	sort(comparator);
    	}
//    	this.information[numberOfKeys] = information;
//    	this.numberOfKeys++;
//    	sort(comparator);
    }
    /**
     * Ordena el arreglo de llaves.
     * @param comparator criterio de ordenamiento.
     */
    public void sort(Comparator<K> comparator) {
    	boolean listener = true;
    	    int size = numberOfKeys;
    	    while (listener) {
    	        listener = false;
    	        size--;
    	        for (int i = 0; i < size; i++) {
    	        	if(comparator.compare(information[i].key,information[i+1].key) >0  ) {
    	        		BtreeInformation<K> temp = information[i];
    	        		information[i] = information[i + 1];
    	        		information[i + 1] = temp;
     	                listener = true;
    	        	}
    	        }
    	 }
    }
    public boolean removeChild(long  child) {
        boolean found = false;
        if (numberOfChildrenNode == 0)
            return found;
        for (int i = 0; i < numberOfChildrenNode; i++) {
            if (children[i] == child) {
                found = true;
            } else if (found) {
                // mueva el resto de las teclas hacia abajo
                children[i - 1] = children[i];
            }
        }
        if (found) {
        	numberOfChildrenNode--;
            children[numberOfChildrenNode] = -1;
        }
        return found;
    }
    public BtreeInformation<K>[] getInformation() {
		return information;
	}
    
    public long[] getChildren() {
		return children;
	}
    
    public byte getNumberOfChildrenNode() {
		return numberOfChildrenNode;
	}
    public long etIndex() {
		return index;
	}
    public byte getNumberOfKeys() {
		return numberOfKeys;
	}
    public void setChildren(long[] children) {
		this.children = children;
	}
    public void setInformation(BtreeInformation<K>[] information) {
		this.information = information;
	}
    public void setNumberOfChildrenNode(byte numberOfChildrenNode) {
		this.numberOfChildrenNode = numberOfChildrenNode;
	}
    public void setIndex(long index) {
		this.index = index;
	}
    public void setNumberOfKeys(byte numberOfKeys) {
		this.numberOfKeys = numberOfKeys;
	}
    @Override
    public String toString() {
    	String keys = "";
    	for (int i = 0; i < this.information.length; i++) {
			if(this.information[i] != null) {
				keys = keys + " |  " + information[i].key;
			}else {
				keys = keys + " --- ";
			}
		}
    	String address = "";
    	for (int i = 0; i < this.information.length; i++) {
			if(this.information[i] != null) {
				address = address + " |  " + information[i].address;
			}else {
				address = address + " --- ";
			}
		}
    	String childs = "";
    	for (int i = 0; i < this.children.length; i++) {
				childs = childs + "  | " + children[i];
		}
    	return String.format(" adressNodeHeap %s Keys %s masterFileAddressKeys %s children %s numberOfKeys %s numberOfChilds %s parent %s",
    			 Long.toString(index) + "|",keys + "|", address + "|" , childs + "|" , "|"+Byte.toString(numberOfKeys) + "|", Byte.toString(numberOfChildrenNode) + "|",Long.toString(parent));
    }
    
    public long getParent() {
		return parent;
	}
    public void setParent(long parent) {
		this.parent = parent;
	}
}















