package b_tree.model;

/**
 * Clase POJO(Plain Old Java Object)
 * 
 * para agrupar la llave y su ubicacion en persistencia.
 * @param <K> Tipo de dato de la clave.
 * @author Andres Felipe Amezquita Gordillo
 */
public class BtreeInformation <K>{
	protected K key;//clave
	protected long address;//direccion en persistencia donde esta esa clave.
	
	public BtreeInformation(K key, long address) {
		this.key = key;
		this.address = address;
	}
	
	public K getKey() {
		return key;
	}
	public long getAddress() {
		return address;
	}
	public void setKey(K key) {
		this.key = key;
	}
	public void setAddress(long address) {
		this.address = address;
	}
}
