public class Move {

	private  String typename;
	private String moveName;
	
	Move(){
		this.typename = "null";
		this.moveName  ="none";
	}

	Move(String name){
		this.typename = name;
	}

	Move(String type,String move){
		this.typename = type;
		this.moveName = move;
	}

	public void setTypeName(String type){
		this.typename = type;
	}

	// TODO: Should this really be setMovesList?
	// considering that each of these objects
	// is a single move, not a list of moves?
	public void setMovesList(String move){
		this.moveName = move;
	}

	public String getTypeName(){
		return this.typename;
	}

	public String getMove(){
		return this.moveName;
	}

	public String toString(){
		String toreturn = typename+"\t"+moveName;
		return toreturn;
	}
	
	
	
	// This is a list of the types that
	// actually exist in th pokemon world.
	public enum TYPES{
		GRASS{
			public String toString(){
				return "Grass";
			}
		},
		FIRE{
			public String toString(){
				return "Fire";
			}
		},
		WATER{
			public String toString(){
				return "Water";
			}
		},
		BUG{
			public String toString(){
				return "Bug";
			}
		},
		NORMAL{
			public String toString(){
				return "Normal";
			}
		},
		POISON{
			public String toString(){
				return "Poison";
			}
		},
		ELECTRIC{
			public String toString(){
				return "Electric";
			}
		},
		GROUND{
			public String toString(){
				return "Ground";
			}
		},
		FAIRY{
			public String toString(){
				return "Fairy";
			}
		},
		FIGHTING{
			public String toString(){
				return "Fighting";
			}
		},
		ROCK{
			public String toString(){
				return "Rock";
			}
		},
		PSYCHIC{
			public String toString(){
				return "Psychic";
			}
		},
		GHOST{
			public String toString(){
				return "Ghost";
			}
		},
		ICE{
			public String toString(){
				return "Ice";
			}
		},
		DRAGON{
			public String toString(){
				return "Dragon";
			}
		}
	}

}
