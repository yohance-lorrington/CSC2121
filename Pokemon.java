 import java.awt.image.BufferedImage;

public class Pokemon {
	
		private Integer id;
		private String name;
		private String type;
		private BufferedImage image;
		/**
		 * 
		 * @param idz
		 * @param namez
		 * @param typez
		 */
		public Pokemon(Integer idz,String namez,String typez){
			this.id = idz;
			this.name = namez;
			this.type = typez;
		}
		
		public Pokemon(){
			name = "ERROR";
			type = "NOT FATAL";
			id = -1;
		}
		/**
		 * 
		 * @return the name of the pokemon or null if the 
		 */
		public String getName(){
			return this.name;
		}
		/**
		 * 
		 * @return the type of the pokemon or null if the 
		 */
		public String getType(){
			return this.type;
		
		}
		/**
		 * 
		 * @return the pokemon's pokedex number 
		 */
		public Integer getID(){
			return this.id;
			
		}
		/**
		 * 
		 * @param someName
		 */
		public void setName(String someName){
			this.name = someName;
		}
		/**
		 * 
		 * @param someType
		 */
		public void setType(String someType){
			this.type = someType;
		}
		/**
		 * 
		 * @param someId
		 */
		public void setId(Integer someId){
			this.id = someId;
		}
		public void setImage(BufferedImage img){
			this.image = img;
		}
		public BufferedImage getImage(){
			return this.image;
		}

	
		public String toString(){
			String toreturn = id.toString()+"\t"+name+"\t"+type;
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
