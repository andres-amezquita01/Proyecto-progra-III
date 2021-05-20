package test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
/**
 * 
 * @author Andres Amezquita ,Felipe Moreno,  Darwin Vargas
 * Prueba unitaria del metodo search, el cual busca  en un archivo indexado
 * en un arbol binario un elemento.
 */
public class Test_search_2 {
	public static void main(String[] args) throws IOException, Exception {
		MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>(
				"resources/out/test/search_test.2", 
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1.compareTo(o2);
					}
				}, new IConverterDatas<Integer>() {
					@Override
					public byte[] keyToByte(Integer key) {
						if(key != null) {
						    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
						    buffer.putInt(key);
						    return buffer.array();
						}else {
						    ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
						    buffer.putInt(-1);
						    return buffer.array();
						}				
					}

					@Override
					public Integer byteToKey(byte[] byteArray) {
						 ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
						 buffer.put(byteArray);
						 buffer.flip();//need flip 
						 return buffer.getInt();
					}

					@Override
					public int sizeKey() {
						return Integer.BYTES;
					}
				});
		myBinarySearchTree.add(new Information<Integer>(23223, 11));
		myBinarySearchTree.add(new Information<Integer>(18999, 12));
		myBinarySearchTree.add(new Information<Integer>(13555, 13));
		myBinarySearchTree.add(new Information<Integer>(14555, 14));
		myBinarySearchTree.add(new Information<Integer>(11176, 15));
		myBinarySearchTree.add(new Information<Integer>(32000, 16));
		myBinarySearchTree.add(new Information<Integer>(25000, 17));
		myBinarySearchTree.add(new Information<Integer>(24423, 18));
		myBinarySearchTree.add(new Information<Integer>(27023, 19));

		Information<Integer> found = myBinarySearchTree.search(13555);
		Information<Integer> notFound = myBinarySearchTree.search(10);
		
		System.out.println("case 1: " + (found != null?"ok ":"error"));
		System.out.println("case 2: " + (notFound != null?"ok ":"error"));
	}
}
