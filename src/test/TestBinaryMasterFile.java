package test;

import java.io.IOException;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import utilities.ComplementDatas;

public class TestBinaryMasterFile {
	public static void main(String[] args) throws Exception {
		 ComplementDatas complementDatas = new ComplementDatas();

		MyBinarySearchTree<String> myBinarySearchTree = new MyBinarySearchTree<>("resources/out/trees/byName.tree", new Comparator<String>() {
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

		for (int i = 1; i < myBinarySearchTree.getNumberOfNodes(); i++) {
			System.out.println(myBinarySearchTree.read(i));
		}
	}
}
