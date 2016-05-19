
public class Pokemon {
	
	private Integer ID;
	private String Name;
	private String Type;
	private String Species;
	/**
	 * 
	 * @param idz
	 * @param namez
	 * @param typez
	 * @param abilz
	 * @param speciez
	 */
	public Pokemon(Integer idz,String namez,String typez, String speciez){
		this.ID = idz;
		this.Name = namez;
		this.Type = typez;
		this.Species = speciez;

	}
	
	public Pokemon(){
		Name = "ERROR";
		Type = "NOT FATAL";
		ID = -1;
		Species = "STILL NOT FATAL";
	}
	/**
	 * 
	 * @return the name of the pokemon or null if the 
	 */
	public String getName(){
		return this.Name;
	}
	/**
	 * 
	 * @return the type of the pokemon or null if the 
	 */
	public String getType(){
		return this.Type;
	
	}
	/**
	 * 
	 * @return the pokemon's pokedex number 
	 */
	public Integer getID(){
		return this.ID;
		
	}
	/**
	 * 
	 * @return the pokemon's species
	 */
	public String getSpecies(){
		return this.Species;
	}

	/**
	 * 
	 * @param someName
	 */
	public void setName(String someName){
		this.Name = someName;
	}
	/**
	 * 
	 * @param someType
	 */
	public void setType(String someType){
		this.Type = someType;
	}
	/**
	 * 
	 * @param someId
	 */
	public void setId(Integer someId){
		this.ID = someId;
	}
	/**
	 * 
	 * @param someSpecies
	 */
	public void getSpecies(String someSpecies){
		this.Species = someSpecies;
	}
	/**
	 * 
	 * @param someAbil
	 */

	public String toString(){
		String toreturn = ID.toString()+"\t"+Name+"\t"+Type+"\t"+Species;
		return toreturn;
	}
	

}
