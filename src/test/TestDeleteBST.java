package test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import model.MySimpleList;
import model.Person;
import persistence.MyMasterPersonFile;
import randomDatas.CreatorRandomDatas;
import utilities.ComplementDatas;

public class TestDeleteBST {
	public static void main(String[] args) throws Exception {
		ComplementDatas complementDatas = new ComplementDatas();
		CreatorRandomDatas creatorRandomDatas = new CreatorRandomDatas();
		MyMasterPersonFile myMasterPersonFileTest = new MyMasterPersonFile("resources/out/test/archivoMaestro.test");
		MyBinarySearchTree<String> myBinarySearchTree = new MyBinarySearchTree<>("resources/out/test/tree.delete", new Comparator<String>() {
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
//		ArrayList<Person> listPersonRandom = creatorRandomDatas.generateDatas(100);
//		for (int i = 0; i < listPersonRandom.size(); i++) {
//			Person person = listPersonRandom.get(i);
//			myBinarySearchTree.add(new Information<String>(complementDatas.stringSize(person.getFirstName()+i, 30), myMasterPersonFileTest.add(person)));
//		}

		for (int i = 1; i < myBinarySearchTree.getNumberOfNodes(); i++) {
			System.out.println(myBinarySearchTree.read(i));
		}
//		MySimpleList<Person> myPersonList = new MySimpleList<>();
//		MySimpleList<Long> indexPersonMasterFile = myBinarySearchTree.traverseInOrder();
//		for (int i = 0; i <indexPersonMasterFile.getSize(); i++) {
//			myPersonList.add(myMasterPersonFileTest.read(indexPersonMasterFile.getIndex(i)));
//		}
//		
//		for (int i = 0; i < myPersonList.getSize(); i++) {
//			System.out.println(myPersonList.getIndex(i));
//		}
	}
}














