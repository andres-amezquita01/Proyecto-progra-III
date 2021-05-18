package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.Comparator;

import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import model.Password;
import persistence.MyMasterPasswordFile;
import persistence.MyMasterPersonFile;
import utilities.ComplementDatas;

public class TestRegistry {
	public static void main(String[] args) throws Exception {
//		File file = new File("resources/out/passwords");
//		System.out.println(file.list()[0]);
//		System.out.println(file.list()[1]);
//		System.out.println(file.list()[0]);
//		System.out.println(file.list()[0]);
		
		 MyMasterPasswordFile myMasterPasswordFile = new MyMasterPasswordFile("resources/out/passwords/myMasterFile.passwords");
		 ComplementDatas complementDatas = new ComplementDatas();
		 Password[] passwordsArray = new Password[] {
				 new Password(complementDatas.stringSize("andres", 30), complementDatas.stringSize("43242h", 30)),
				 new Password(complementDatas.stringSize("luis", 30), complementDatas.stringSize("224324", 30)),
				 new Password(complementDatas.stringSize("saraaa", 30), complementDatas.stringSize("hdfsd", 30)),
				 new Password(complementDatas.stringSize("ema", 30), complementDatas.stringSize("123abc", 30)),

		 };
//		 System.out.println(myMasterPasswordFile.add(passwordsArray[0]));
//		 System.out.println(myMasterPasswordFile.add(passwordsArray[1]));
//		 System.out.println(myMasterPasswordFile.add(passwordsArray[2]));
//
//		 System.out.println(myMasterPasswordFile.readIndex(0));
//		 System.out.println(myMasterPasswordFile.readIndex(1));
//		 System.out.println(myMasterPasswordFile.readIndex(2));
//		 MyMasterPersonFile myMasterPersonFile = new MyMasterPersonFile("resources/out/masterFile/myMasterFile.Person");
//		 for (int i = 0; i < passwordsArray.length; i++) {
//			 System.out.println(myMasterPersonFile.read(i));
//		}
			 MyBinarySearchTree<String> myBinarySearchTreePassword = new MyBinarySearchTree<>(
					"resources/out/passwords/myBST.passwords"
					, new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
							return o1.compareTo(o2);
						}
					},new IConverterDatas<String>() {
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
			 long indexA =  myMasterPasswordFile.add(passwordsArray[0]);
			 long indexB =  myMasterPasswordFile.add(passwordsArray[1]);
			 long indexC =  myMasterPasswordFile.add(passwordsArray[2]);
			 long indexD =  myMasterPasswordFile.add(passwordsArray[3]);
			 System.out.println(indexA);
			 System.out.println(indexB);
			 System.out.println(indexC);
			 System.out.println(indexD);

			 myBinarySearchTreePassword.add(new Information<String>(passwordsArray[0].getUser(),indexA));
			 myBinarySearchTreePassword.add(new Information<String>(passwordsArray[1].getUser(), indexB));
			 myBinarySearchTreePassword.add(new Information<String>(passwordsArray[2].getUser(), indexC));
			 myBinarySearchTreePassword.add(new Information<String>(passwordsArray[3].getUser(), indexD));

	}
	
	
	
	
	
	
	
}
