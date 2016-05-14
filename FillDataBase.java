import java.sql.SQLException;
import java.util.Scanner;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FillDataBase {
	private BufferedReader input;
	private final DataBaseHelper db;
	public static final int FILLPOKEMONDATABASE =256;
	public static final int FILLMOVEDATABASE = 128;

	public static void main(String[] args) {
	
		final DataBaseHelper dbHelper = new DataBaseHelper();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame(dbHelper);
					window.PokeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Scanner userInput = new Scanner(System.in);
		int i = 0;
		while(i!=-1){
			System.out.println("\nWhat Would You like to do?");
			System.out.println("Enter number corresponding to selection:\n "
					+ "	1:Print All\n	2:Search by name\n	3:Search by ID\n"
					+ "	4:Search by Type\n	5:Remove by ID\n	6: Fill Pokemon DB\n"
					+ " 	7: Fill Moves DB\n 	8: Show Moves by Type\n 	9: Show Moves of Pokemon Name\n"
					+ " \t10:Show All Moves\n"
					+ "	11:Drop DB\n	-1:EXIT");
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
						
				case 6: new FillDataBase(FillDataBase.FILLPOKEMONDATABASE);
						break;
				case 7: new FillDataBase(FillDataBase.FILLMOVEDATABASE);
						break;
				case 8: System.out.println("Please enter a Type: ");
						for(Move move :dbHelper.getMoves(userInput.next())){
							System.out.println(move);
						}
						break; 
				case 9: System.out.println("Please enter a Pokemon Name: ");
						for(Move move : dbHelper.getMoves(dbHelper.getPokemon(userInput.next()))){
							System.out.println(move);
						}
						break;
				case 10: System.out.println("ALL MOVES");
						for(Move move : dbHelper.getAllMoves()){
							System.out.println(move);
						}
						break;
						
				case 11: System.out.println("Dropping DaBase!");
						dbHelper.removeDatabases();
						break;


			}
		}
		
		userInput.close();
		dbHelper.closeConnection();
		

	}
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws EOFException
	 * @throws IOException
	 */
	public FillDataBase(int whichDatabase){
		db = new DataBaseHelper();
		switch(whichDatabase){
			case FILLPOKEMONDATABASE:
				openFile("src/pokemonlist.txt");
				break;
			case FILLMOVEDATABASE:
				openFile("src/movelist.txt");
				break;
			default:
				input = null;
				break;
			
		}
		
		try {
			readRecords(whichDatabase);
		} catch (NumberFormatException e) {
			System.out.println("File Information Is In The Wrong Format!");
		} catch (IOException e) {
			System.out.println("File Cannoe Be Found!");
		}
		if(input == null)System.exit(-1);
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
				System.out.println("File Cannot Be Found!");
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
					System.out.println("No Opened Files Found!");
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
	private void readRecords(int caze) throws NumberFormatException, IOException{
		switch(caze){
			case FILLPOKEMONDATABASE:
				if(db.gottaCatchEmAll().size()<1){
					String something;
					while((something = input.readLine()) != null){
						something=something.replaceAll("\\s+", " ");
						String[] stuff =something.split(" ");
						if(stuff[2].contains("'")){
							String some = stuff[2].replace("'", " ");
							stuff[2]= some;
						}
						db.insertPokemon(new Pokemon(Integer.valueOf(stuff[1]),stuff[2].toLowerCase(),stuff[3],stuff[4],stuff[5]));
						
					}
					db.closeConnection();
				}
				break;
			case FILLMOVEDATABASE:
				if(db.getAllMoves().size()<1){
					String line;
					while((line = input.readLine()) != null){
						line=line.replaceAll("\\s+", " ");
						String[] stuff =line.split(" ");
						for(int i =0;i<stuff.length;++i){
							if(stuff.length == 3){
								db.insertTypeandMove(stuff[2], stuff[0]+" "+stuff[1]);
								break;
								
							}else{
								db.insertTypeandMove(stuff[1], stuff[0]);
								break;
							}
						}
						
					}
					db.closeConnection();
				}
				break;
		}


	}
}
