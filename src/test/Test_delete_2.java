package test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import model.MySimpleList;

public class Test_delete_2 {
	public static void main(String[] args) throws IOException, Exception {
		MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>(
				"resources/out/test/delete.2", 
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
		myBinarySearchTree.add(new Information<Integer>(50, 11));
		myBinarySearchTree.add(new Information<Integer>(20, 12));
		myBinarySearchTree.add(new Information<Integer>(30, 13));
		myBinarySearchTree.add(new Information<Integer>(40, 14));
		myBinarySearchTree.add(new Information<Integer>(70, 15));
		myBinarySearchTree.add(new Information<Integer>(60, 16));
		myBinarySearchTree.add(new Information<Integer>(80, 17));
		for (int i = 0; i < myBinarySearchTree.getNumberOfNodes(); i++) {
			System.out.println(myBinarySearchTree.read(i));
		}
		MySimpleList<Integer> mySimpleListKeys = myBinarySearchTree.traverseInOrderKeys();
		
		for (int i = 0; i < mySimpleListKeys.getSize(); i++) {
			System.out.println(mySimpleListKeys.getIndex(i));
		}
		
		myBinarySearchTree.delete(30);
//		
		System.out.println("--------eliminados------------ ");
		MySimpleList<Integer> mySimpleListKeysDeleted = myBinarySearchTree.traverseInOrderKeys();
		for (int i = 0; i < mySimpleListKeysDeleted.getSize(); i++) {
			System.out.println(mySimpleListKeysDeleted.getIndex(i));
		}
	}
}













