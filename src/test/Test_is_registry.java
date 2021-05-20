package test;

import java.util.Comparator;
import binarySearchTree.IConverterDatas;
import binarySearchTree.Information;
import binarySearchTree.MyBinarySearchTree;
import model.Password;
import persistence.MyMasterPasswordFile;
import utilities.ComplementDatas;
/**
 * Prueba el indexamiento y validacion del registro de un usuario.
 * @author Andres Amezquita , Darwind Vargas, Felipe Moreno
 */
public class Test_is_registry {
	public static void main(String[] args) throws Exception {
		 @SuppressWarnings("resource")
		MyMasterPasswordFile myMasterPasswordFile = new MyMasterPasswordFile("resources/out/test/myMasterFilePasswords.test");
		 ComplementDatas complementDatas = new ComplementDatas();
		 Password[] passwordsArray = new Password[] {
				 new Password(complementDatas.stringSize("andres", 30), complementDatas.stringSize("43242h", 30)),
				 new Password(complementDatas.stringSize("luis", 30), complementDatas.stringSize("224324", 30)),
				 new Password(complementDatas.stringSize("saraaa", 30), complementDatas.stringSize("hdfsd", 30)),
				 new Password(complementDatas.stringSize("ema", 30), complementDatas.stringSize("123abc", 30)),

		 };
		 MyBinarySearchTree<String> myBinarySearchTreePassword = new MyBinarySearchTree<>(
				"resources/out/test/isRegistry.test"
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
		 
		 Information<String> found = myBinarySearchTreePassword.search(passwordsArray[1].getUser());
		 Information<String> notFound = myBinarySearchTreePassword.search(complementDatas.stringSize("joako", 30));

		 System.out.println("case 1: "  + found + " exist? " + (found != null?"ok ":"error"));
		 System.out.println("case 2: "  + notFound + " exist? " + (notFound != null?"ok ":"error"));

	}
	
	
	
	
	
	
	
}
