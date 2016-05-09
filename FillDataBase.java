import java.sql.SQLException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class FillDataBase {
	private BufferedReader input;
	private DataBaseHelper db;
	
	public static void main(String[] args) {

		try {
			new FillDataBase();
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DataBaseHelper dbHelper = new DataBaseHelper();

		Pokemon p = dbHelper.getPokemon("Caterpie");
		System.out.println("JUST CATERPIE");
		System.out.println(p);
		ArrayList<Pokemon> p1 = dbHelper.getPokemonByType(Pokemon.TYPES.FIRE);
		System.out.println("JUST FIRE TYPES");
		for (Pokemon pokemon : p1) {
			System.out.println(pokemon);
		}
		ArrayList<Pokemon> p2=dbHelper.gottaCatchEmAll();
		System.out.println("ALL POKEMONS");
		for(Pokemon pokemon:p2){
			System.out.println(pokemon);
		}
		System.out.println("Pokemon # 83");
		Pokemon p3 = dbHelper.getPokemon(83);
		System.out.println(p3);
		
		dbHelper.closeConnection();
		

	}
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws EOFException
	 * @throws IOException
	 */
	public FillDataBase()throws ClassNotFoundException,SQLException,EOFException,IOException{
		db = new DataBaseHelper();
		openFile("src/pokemonlist.txt");
		readRecords();
		closeFile();
		
	}
	/**
	 * 
	 * @param whatever
	 * @throws IOException
	 */
	private void openFile(String whatever)throws IOException{
			input = new BufferedReader(new FileReader( new File(whatever)));			

	}
	/**
	 * 
	 * @throws IOException
	 */
	private void closeFile()throws IOException{
			if(input != null)input.close();

	}
	/**
	 * 
	 * @throws SQLException
	 * @throws EOFException
	 * @throws IOException
	 */
	private void readRecords()throws SQLException,EOFException,IOException{
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
