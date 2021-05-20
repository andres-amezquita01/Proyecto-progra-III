package test;

import java.util.ArrayList;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import model.Person;
import persistence.MyMasterPersonFile;
import randomDatas.CreatorRandomDatas;
import utilities.ComplementDatas;

public class TestIndexing_2 {
	public static void main(String[] args) throws Exception {
		 ComplementDatas complementDatas = new ComplementDatas();

		MyBinarySearchTree<String> myBinarySearchTree = new MyBinarySearchTree<>("resources/out/test/indexing.2", new Comparator<String>() {
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
		ArrayList<Person> listPerson = new CreatorRandomDatas().generateDatas(7);
		listPerson.get(0).setFirstName(complementDatas.stringSize("kely", 30));
		listPerson.get(1).setFirstName(complementDatas.stringSize("cami", 30));
		listPerson.get(2).setFirstName(complementDatas.stringSize("ana", 30));
		listPerson.get(3).setFirstName(complementDatas.stringSize("diego", 30));
		listPerson.get(4).setFirstName(complementDatas.stringSize("sara", 30));
		listPerson.get(5).setFirstName(complementDatas.stringSize("luis", 30));
		listPerson.get(6).setFirstName(complementDatas.stringSize("viviana", 30));

//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("kely", 30), 0));
//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("cami", 30), 1));
//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("ana", 30), 2));
//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("diego", 30), 3));
//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("sara", 30), 4));
//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("luis", 30), 5));
//		myBinarySearchTree.add(new Information<String>(complementDatas.stringSize("viviana", 30), 6));
//		MyMasterPersonFile myMasterPersonFile = new MyMasterPersonFile("resources/out/test/master.7");
//		for (int i = 0; i < listPerson.size(); i++) {
//			myMasterPersonFile.add(listPerson.get(i));
//		}
//		for (int i = 0; i < myMasterPersonFile.numberPersonsInFile(); i++) {
//			System.out.println("index " + i + " "+ myMasterPersonFile.read(i));
//		}
		for (int i = 0; i < myBinarySearchTree.getNumberOfNodes(); i++) {
			System.out.println("index " + i + " "+ myBinarySearchTree.read(i));
		}
	}
}
