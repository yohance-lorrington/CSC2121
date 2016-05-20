//Class created to streamline the DB data Acquisition process by wrapping Type properties
public class Move {
	
	//Instantiate Class Variable
	private String typename;
	private String moveName;

	//Constructors
	Move(){
		this.typename = "null";
		this.moveName = "none";
	}

	Move(String name){
		this.typename = name;
	}

	Move(String type,String move){
		this.typename = type;
		this.moveName = move;
	}

	
	/**
	 * Utility Functions
	**/
	
	// Setter Methods
	public void setTypeName(String type){
		this.typename = type;
	}

	public void setMovesList(String move){
		this.moveName = move;
	}

	// Getter Methods
	public String getTypeName(){
		return this.typename;
	}

	public String getMove(){
		return this.moveName;
	}

	@Override
	public String toString(){
		String toreturn = typename+"\t"+moveName;
		return toreturn;
	}

}
