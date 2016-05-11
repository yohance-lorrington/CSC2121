import java.sql.SQLException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class FillDataBase {
	private BufferedReader input;
	private DataBaseHelper db;
	
	public static void main(String[] args) {
		
		DataBaseHelper dbHelper = new DataBaseHelper();
		
		Scanner userInput = new Scanner(System.in);
		int i = 0;
		while(i!=-1){
			System.out.println("\nWhat Would You like to do?");
			System.out.println("Enter number corresponding to selection:\n "
					+ "	1:Print All\n	2:Search by name\n	3:Search by ID\n"
					+ "	4:Search by Type\n	5:Remove by ID\n	6: Fill DB\n"
					+ "	7:Drop DB\n	-1:EXIT");
			i = userInput.nextInt();
			
			switch (i) {
				case 1: System.out.println("ALL POKEMONS");
						for(Pokemon pokemon : dbHelper.gottaCatchEmAll()){
							System.out.println(pokemon);
						}
						break;
						
				case 2: System.out.println("Please enter a Pokemon: ");
						System.out.println(dbHelper.getPokemon(userInput.next()));
						break;
					
				case 3: System.out.println("Please enter Pokemon ID: ");
						System.out.println(dbHelper.getPokemon(userInput.nextInt()));
						break;
						
				case 4: System.out.println("Please enter a Type: ");
						for (Pokemon pokemon : dbHelper.getPokemonByType(userInput.next())) {
							System.out.println(pokemon);
						}
						break;
						
				case 5: System.out.println("Please enter ID of Pokemon to remove: ");
						dbHelper.deletePokemon(userInput.nextInt());
						break;
						
				case 6: new FillDataBase();
						break;
						
				case 7: System.out.println("Dropping DaBase!");
						dbHelper.removeDatabase();
						break;

				}
		}

		dbHelper.closeConnection();
		

	}
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws EOFException
	 * @throws IOException
	 */
	public FillDataBase(){
		db = new DataBaseHelper();
		openFile("src/pokemonlist.txt");
		try {
			readRecords();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		closeFile();
		
	}
	/**
	 * 
	 * @param whatever
	 * @throws IOException
	 */
	private void openFile(String whatever){
			try {
				input = new BufferedReader(new FileReader( new File(whatever)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}			

	}
	/**
	 * 
	 * @throws IOException
	 */
	private void closeFile(){
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
	/**
	 * 
	 * @throws NumberFormatException 
	 * @throws SQLException
	 * @throws EOFException
	 * @throws IOException
	 */
	private void readRecords() throws NumberFormatException, IOException{
			if(db.gottaCatchEmAll().size()<1){
				String something;
				while((something = input.readLine()) != null){
					something=something.replaceAll("\\s+", " ");
					String[] stuff =something.split(" ");
					if(stuff[2].contains("'")){
						String some = stuff[2].replace("'", " ");
						stuff[2]= some;
					}
					db.insertPokemon(new Pokemon(Integer.valueOf(stuff[1]),stuff[2],stuff[3]));
				}
				db.closeConnection();
			}

	}
}
