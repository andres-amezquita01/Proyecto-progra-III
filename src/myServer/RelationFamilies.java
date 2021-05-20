package myServer;

import java.io.Serializable;
/**
 * relaciones familiares que tiene una personas
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 * @param <Person> Persona 
 * @param <Integer> tipo de relacion 
 */
public class RelationFamilies<Person,Integer> implements Serializable {

	private Person person;
	private int idTypeRelation;
	public Person getPerson() {
		return person;
	}
	
	/**
	 * contrtuctor de mi clase
	 * @param person persona con la que se tiene alguna relacion
	 * @param idTypeRelation tipo de relacion que se tiene con la persona mencionada
	 */
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
