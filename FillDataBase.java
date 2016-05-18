import java.sql.SQLException;
import java.util.Scanner;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.WindowEvent;

// TODO: Get the correct image to show up
//	on start up.

public class FillDataBase {


	private BufferedReader input;
	private final DataBaseHelper db;
	public static final int FILLPOKEMONDATABASE =256;
	public static final int FILLMOVEDATABASE = 128;

	
	
	//////////////////////////////////////////////////
	// Utility Functions : {{{
	//////////////////////////////////////////////////
	
	/**
	 * Fill one of two databases: move database, or pokemon database.
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
	 * Opens text files which contain data for databases.
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
	 * Closes the currently opened text file.
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
	 * Parses input file to place records in our databases.
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
					String line;
					while((line = input.readLine()) != null){
						line=line.replaceAll("\\s+", " ");
						String[] stuff =line.split(" ");
						int i=0;
						while(i<stuff.length){
							if(stuff.length == 3){
								db.insertTypeandMove(stuff[2], stuff[0]+" "+stuff[1]);
							}else{
								db.insertTypeandMove(stuff[1], stuff[0]);
							}
							++i;
						}
						
					}
					db.closeConnection();
				break;
		}
	}
}

	
