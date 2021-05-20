package test;

import java.util.Comparator;
import binarySearchTree.IConverterDatas;
import binarySearchTree.MyBinarySearchTree;
import model.MySimpleList;
import persistence.MyMasterPersonFile;
import randomDatas.CreatorRandomDatas;
import utilities.ComplementDatas;
/**
 * Crea un archivo de personas de prueba
 * @author Andres Amezquita, Darwin Vargas, Felipe Moreno
 *
 */
public class TestDeleteBST_1 {
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
		
		System.out.println("\n ------------Antes de eliminar-----------");
		MySimpleList<Long> mySimpleList = myBinarySearchTree.traverseInOrder();
		for (int i = 0; i < mySimpleList.getSize(); i++) {
			System.out.println(i + " "+myMasterPersonFileTest.read(mySimpleList.getIndex(i)));
		}
		
		String deleted_1 = complementDatas.stringSize("Karen Adriana5", 30);
//		String deleted_2 = complementDatas.stringSize("Claudia Nicole1", 30);
//		String deleted_3 = complementDatas.stringSize("Karen Angelica0", 30);
//
		System.out.println("se elimino a "+ deleted_1+"? " + myBinarySearchTree.delete(deleted_1));
//		System.out.println("se elimino a " + deleted_2  +"? " + myBinarySearchTree.delete(deleted_2));
//		System.out.println("se elimino a " +deleted_3 + "? " + myBinarySearchTree.delete(deleted_3));
//		
		System.out.println("\n ------------indexamiento -----------");
		for (int i = 0; i < myBinarySearchTree.getNumberOfNodes(); i++) {
			System.out.println(myBinarySearchTree.read(i));
		}
		System.out.println("\n ------------despues de eliminar-----------");
		//---------despues de eliminar--------------
		MySimpleList<Long> mySimpleListDeleted = myBinarySearchTree.traverseInOrder();
		for (int i = 0; i < mySimpleListDeleted.getSize(); i++) {
			System.out.println(i + " " +myMasterPersonFileTest.read(mySimpleListDeleted.getIndex(i)));
		}
		

	}
}














