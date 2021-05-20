package b_tree.model;

public interface IConverterDatasBtree <K>{
	public  byte[] keyToByte(K key);
	public K byteToKey(byte[] byteArray);
	public int sizeKey();
}
