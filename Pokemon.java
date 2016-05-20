//Class created to streamline the DB data Acquisition process by wrapping Pokemon properties.
public class Pokemon {
	
	//Member Variables
	private Integer ID;
	private String Name;
	private String Type;
	private String Species;

	//Constructors
	public Pokemon(Integer idz,String namez,String typez, String speciez){
		this.ID = idz;
		this.Name = namez;
		this.Type = typez;
		this.Species = speciez;

	}
	
	//error state default Pokemon constructor
	public Pokemon(){
		Name = "ERROR";
		Type = "NOT FATAL";
		ID = -1;
		Species = "STILL NOT FATAL";
	}
	/**
	 * @return the name of the pokemon
	 */
	public String getName(){
		return this.Name;
	}
	/**
	 * @return the type of the pokemon
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
	 * @return the pokemon's species
	 */
	public String getSpecies(){
		return this.Species;
	}

	/**
	 * @return the pokemon's name
	 */
	public void setName(String someName){
		this.Name = someName;
	}
	/**
	 * @return the pokemon's Type
	 */
	public void setType(String someType){
		this.Type = someType;
	}
	/**
	 *@return the pokemon's ID
	 */
	public void setId(Integer someId){
		this.ID = someId;
	}
	/**
	 *@return the pokemon's Species
	 */
	public void getSpecies(String someSpecies){
		this.Species = someSpecies;
	}
	/**
	 * @return the pokemon's Abil
	 */

	public String toString(){
		String toreturn = ID.toString()+"\t"+Name+"\t"+Type+"\t"+Species;
		return toreturn;
	}
	

}
