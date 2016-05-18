import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class pokedexDriver {
	public static void main(String[] args) {
		
		final DataBaseHelper dbHelper = new DataBaseHelper();
		try {
			final Frame window = new Frame(dbHelper);
			
		// Invokes the GUI on a different thread. This way
		// operations in each thread don't interfere with
		// the other thread.
		// Long database operations won't halt the GUI and
		// any long operations on the GUI won't halt the main
		// thread.
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.initialize();
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
			System.out.println("Enter number corresponding to selection:\n"
					+ "\t1:Print All\n"
					+ "\t2:Search by name\n"
					+ "\t3:Search by ID\n"
					+ "\t4:Search by Type\n"
					+ "\t5:Remove by ID\n"
					+ "\t6: Fill Pokemon DB\n"
					+ "\t7: Fill Moves DB\n"
					+ "\t8: Show Moves by Type\n"
					+ "\t9: Show Moves of Pokemon Name\n"
					+ "\t10:Show All Moves\n"
					+ "\t11:Drop DB\n"
					+ "\t-1:EXIT");

			// In case an integer is not entered.
			try {
				i = userInput.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Please enter an integer!");

				// Flush the last input from scanner.
				// Prevents infinite loop.
				userInput.next();
				continue;
			}
			
			switch (i) {
				case 1: System.out.println("ALL POKEMONS");
						for(Pokemon pokemon : dbHelper.gottaCatchEmAll()){
							System.out.println(pokemon);
						}
						break;
						
				case 2: System.out.println("Please enter a Pokemon: ");
						try {
							System.out.println(dbHelper.getPokemon(userInput.next()));
						} catch (java.lang.NullPointerException e) {
							System.out.println("Pokemon does not exist!");
						}
						break;
					
				case 3: 
						System.out.println("Please enter Pokemon ID: ");
						try {
							System.out.println(dbHelper.getPokemon(userInput.nextInt()));
						} catch (java.util.InputMismatchException e) {

						}
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
						for(Move move : dbHelper.getMoves(userInput.next())){
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

		window.PokeFrame.dispatchEvent(
				new WindowEvent(window.PokeFrame, WindowEvent.WINDOW_CLOSING)
		);
		userInput.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		dbHelper.closeConnection();
	}
}
