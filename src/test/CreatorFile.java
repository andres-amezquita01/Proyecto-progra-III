package test;

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

public class CreatorFile {
	public static void main(String[] args) throws Exception {
		ComplementDatas complementDatas = new ComplementDatas();
		CreatorRandomDatas creatorRandomDatas = new CreatorRandomDatas();
		@SuppressWarnings("resource")
		MyMasterPersonFile myMasterPersonFileTest = new MyMasterPersonFile("resources/out/test/delete.master");
		MyBinarySearchTree<String> myBinarySearchTree = new MyBinarySearchTree<>("resources/out/test/delete.1", new Comparator<String>() {
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
		long time = System.currentTimeMillis();
		System.out.println("inicio...");
		ArrayList<Person> listPersonRandom = creatorRandomDatas.generateDatas(10);
		for (int i = 0; i < listPersonRandom.size(); i++) {
			Person person = listPersonRandom.get(i);
			myBinarySearchTree.add(new Information<String>(complementDatas.stringSize(person.getFirstName()+i, 30),
					myMasterPersonFileTest.add(person)));
		}
		System.out.println("fin... tiempo gastado:\n " + (System.currentTimeMillis()-time));

	}
}
