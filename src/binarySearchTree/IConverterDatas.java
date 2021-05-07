package binarySearchTree;
/**
 * Interface que comunica el usuario
 * con la persistencia, para obtener datos.
 * @author Andres Felipe Amezquita Gordillo
 * @param <T> objeto generico.
 */
public interface IConverterDatas<T>{
	public byte[] keyToByte(T key);
	public T byteToKey(byte[] byteArray);
	public int sizeKey();
}
