package myServer;

import java.io.Serializable;

public class RelationFamilies<Person,Integer> implements Serializable {

	private Person person;
	private int idTypeRelation;
	public Person getPerson() {
		return person;
	}
	
	public RelationFamilies(Person person, int idTypeRelation) {
		this.person = person;
		this.idTypeRelation = idTypeRelation;
	}


	public void setPerson(Person person) {
		this.person = person;
	}
	public int getIdTypeRelation() {
		return idTypeRelation;
	}
	public void setIdTypeRelation(int idTypeRelation) {
		this.idTypeRelation = idTypeRelation;
	}
	
	
	
}
