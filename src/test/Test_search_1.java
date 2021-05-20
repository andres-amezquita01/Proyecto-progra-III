package test;

import java.util.Comparator;
import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import utilities.ComplementDatas;

/**
 * 
 * @author Andres Amezquita ,Felipe Moreno,  Darwin Vargas
 * Prueba unitaria del metodo search, el cual busca  en un archivo indexado
 * en un arbol binario un elemento.
 */
public class Test_search_1 {
	public static void main(String[] args) throws Exception {
		ComplementDatas complementDatas = new ComplementDatas();
		MyBinarySearchTree<String> myBinarySearchTree = new MyBinarySearchTree<>("resources/out/test/search_test.1", new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		}, new IConverterDatas<String>() {

			@Override
			public byte[] keyToByte(String key) {
				if(key != null) {
					key = complementDatas.stringSize(key, 30);
					return key.getBytes();
				}else {
					key = complementDatas.stringSize(" ", 30);
					return key.getBytes();
				}
			}
			@Override
			public String byteToKey(byte[] byteArray) {
				return new String(byteArray);
			}
			@Override
			public int sizeKey() {
				return 30;
			}
		});
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("andres", 30), 11));
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("luis", 30), 12));
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("oscar", 30), 13));
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("sara", 30), 14));
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("santiago", 30), 15));
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("jose", 30), 16));
		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("pepe", 30), 17));
		String keyIndexed = complementDatas.stringSize("andres", 30);
		String keyNotIndexed = complementDatas.stringSize("juan", 30);
		Information<String> found = myBinarySearchTree.search(keyIndexed);
		Information<String> notFound = myBinarySearchTree.search(keyNotIndexed);
		
		System.out.println("case 1: " + (found != null?"ok ":"error"));
		System.out.println("case 1: " + (notFound != null?"ok ":"error"));
	}
}
