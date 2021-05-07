package test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.MyBinarySearchTree;

public class TestAddInteger {
	public static void main(String[] args) throws FileNotFoundException {
		MyBinarySearchTree<Integer> myBst = new MyBinarySearchTree<>("resources/out/test/test.integer",
				new Comparator<Integer>() {
					@Override
					public int compare(Integer o1, Integer o2) {
						return o1 - o2;
					}
				}, new IConverterDatas<Integer>() {

					@Override
					public byte[] keyToByte(Integer key) {
						   ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
						    buffer.putInt(key);
						    return buffer.array();
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
		try {
			myBst.add(1704);
			myBst.add(234);
			myBst.add(76556);
			myBst.add(123);
			myBst.add(300);
			myBst.add(80000);
			for (int i = 0; i < 6; i++) {
				System.out.println(myBst.read(i));
			}
//			System.out.println(myBst.read(0));
//			System.out.println(myBst.read(1));
//			System.out.println(myBst.read(2));
			ArrayList<Integer> list = myBst.traverseInOrder();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
//			System.out.println(myBst.read(3));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
