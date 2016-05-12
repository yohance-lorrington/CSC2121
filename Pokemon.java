 import java.awt.image.BufferedImage;

public class Pokemon {
	
		private Integer ID;
		private String Name;
		private String Type;
		private String Abil;
		private String Species;
		private BufferedImage image;
		/**
		 * 
		 * @param idz
		 * @param namez
		 * @param typez
		 * @param abilz
		 * @param speciez
		 */
		public Pokemon(Integer idz,String namez,String typez,String abilz, String speciez){
			this.ID = idz;
			this.Name = namez;
			this.Type = typez;
			this.Species = speciez;
			this.Abil = abilz;
		}
		
		public Pokemon(){
			Name = "ERROR";
			Type = "NOT FATAL";
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
		 * @return the pokemon's ability 
		 */
		public String getAbil(){
			return this.Abil;
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
		public void getAbil(String someAbil){
			this.Abil = someAbil;
		}
		public void setImage(BufferedImage img){
			this.image = img;
		}
		public BufferedImage getImage(){
			return this.image;
		}

	
		public String toString(){
			String toreturn = ID.toString()+"\t"+Name+"\t"+Type;
			return toreturn;
		}
		
		
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
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
