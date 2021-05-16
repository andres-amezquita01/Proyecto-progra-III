package model;

import java.io.Serializable;

/**
 * 
 * @author Darwin Vargas
 *clase que me crea las relaciones familiares de una persona dada con otra empleando grafos
 */
public class GraphFamily implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long idPersonOne;
	private RelationType relationType;
	private long idPersonTwo;
	
	
	/**
	 * contructor que me permite establecer una relacion familiar dada
	 * @param idPersonOne persona que va a tener una relacion familiar con alguien
	 * @param idPersonTwo persona numero dos la cual tendra una relacion familiar con la persona numero 1
	 * @param relationType tipo de relacion que va a tener la persona
	 */
	public GraphFamily(long idPersonOne,RelationType relationType ,long idPersonTwo) {
		super();
		this.idPersonOne = idPersonOne;
		this.idPersonTwo = idPersonTwo;
		this.relationType = relationType;
	}
	
	public GraphFamily() {
		
	}
	
	public long getIdPersonOne() {
		return idPersonOne;
	}
	public void setIdPersonOne(long idPersonOne) {
		this.idPersonOne = idPersonOne;
	}
	public long getIdPersonTwo() {
		return idPersonTwo;
	}
	public void setIdPersonTwo(long idPersonTwo) {
		this.idPersonTwo = idPersonTwo;
	}
	public RelationType getRelationType() {
		return relationType;
	}
	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}


	@Override
	public String toString() {
		return "GraphFamily [idPersonOne=" + idPersonOne + ", idPersonTwo=" + idPersonTwo + ", relationType="
				+ relationType + "]";
	}


	
	
	
}
