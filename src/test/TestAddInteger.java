package test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;

public class TestAddInteger {
	public static void main(String[] args) throws IOException, Exception {
		MyBinarySearchTree<Integer> myBinarySearchTree = new MyBinarySearchTree<>(
				"resources/out/test/tree.integer", 
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
		for (int i = 0; i < myBinarySearchTree.getNumberOfNodes(); i++) {
			System.out.println(myBinarySearchTree.read(i));
		}
		System.out.println(myBinarySearchTree.search(11176));
	}
}








