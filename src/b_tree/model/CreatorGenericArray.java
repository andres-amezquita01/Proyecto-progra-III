package b_tree.model;


import java.lang.reflect.Array;

/**
 * Crea un arreglo generico con la longitud indicada.
 * @author Andres Felipe Amezquita Gordillo
 * @param <K>
 */
public class CreatorGenericArray<K> {  
    private K[] genericArray;  
    public CreatorGenericArray(Class<K[]> clazz, int length) {  
        genericArray = clazz.cast(Array.newInstance(clazz.getComponentType(), length));  
    }  
    
    /**
     * Retorna un arreglo generico de la clase especificada
     * @return
     */
    public K[] getGenericArray() {
		return genericArray;
	}



	public void setGenericArray(K[] a) {
		this.genericArray = a;
	}

}