import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import java.awt.Color;


public class Frame {
	
	//Initialize JFrame, inputs, pokemon index, and Data Base object
	JFrame PokeFrame;
	String listen;
	String input;
    	int PI = 151;
	DataBaseHelper dbHelper;

	/*
	 * Create the application.
	 * Initialize the contents of the frame.
	 * The function is public so that the Frame can
	 * be declared before it is initialized.
	 * We need this because we run our GUI through the
	 * EventQueue.inkokeLater() method. If we define
	 * the frame inside of this function, then we
	 * can't interact with it from our main method
	 * in FillDataBase.
	 * We want to be able to do so in order to be
	 * able to close the GUI when the command line
	 * part of the program exits.
	 */
	public Frame(DataBaseHelper DBH) {
		dbHelper = DBH;
	}


	public void initialize() {
		
		////////////////////////////
	//-----	//  FRAME INITIALIZATION  //-----//
		////////////////////////////
		
		PokeFrame = new JFrame();
		PokeFrame.setResizable(false);
		PokeFrame.setTitle("Jetlir'sGUI");
		PokeFrame.setBounds(100, 100, 780, 620);
		PokeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PokeFrame.getContentPane().setLayout(null);
		
		////////////////////////////
	//-----	//      FRAME CONTENT     //-----//
		////////////////////////////
	
	// 16 Objects fill the JFrame; 13 Jlabels, 1 ImageIcon, 1 ScrollPane, 1 SearchField,
		
		
					/////////////////////////////////////
					// IMAGEICON INSIDE THE SCROLLPANE //
					/////////////////////////////////////
					
		/* Create Image Icon, attach giphy.gif to it, 
		Scale 4000 by 4000 for appropriate sized pokemon 
		attached to fill a MatteBordered box, Hide ScrollBars*/
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("giphy.gif").getImage().getScaledInstance(4000, 4000, Image.SCALE_DEFAULT));
		JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(new JLabel(imageIcon)); 
	    	scrollPane.getVerticalScrollBar().setValue(0);
		scrollPane.getHorizontalScrollBar().setValue(0);
		scrollPane.setBounds(109, 260, 159, 195);
		PokeFrame.getContentPane().add(scrollPane);
		
					////////////////////////////
					// NEVER CHANGING JLABELS //
					////////////////////////////
		
		//6 of the following JLabels wont ever change, and are always displayed
		//Basic JLabel constant string
		JLabel lblmoves = new JLabel("<html>Moves : <html>");
		lblmoves.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblmoves.setBounds(490, 406, 115, 70);
		PokeFrame.getContentPane().add(lblmoves);
		
		//Basic JLabel constant string
		JLabel lblspecies = new JLabel("<html>Species : <html>");
		lblspecies.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblspecies.setBounds(490, 320, 115, 70);
		PokeFrame.getContentPane().add(lblspecies);
		
		//Basic JLabel constant string
		JLabel PokemonType = new JLabel("<html>Pokemon Type : <html>");
		PokemonType.setFont(new Font("Tahoma", Font.BOLD, 20));
		PokemonType.setBounds(490, 234, 211, 70);
		PokeFrame.getContentPane().add(PokemonType);
		
		//Basic JLabel constant string
		JLabel Name = new JLabel("<html>Name : <html>");
		Name.setFont(new Font("Tahoma", Font.BOLD, 20));
		Name.setBounds(490, 178, 115, 70);
		PokeFrame.getContentPane().add(Name);
		
		//Basic JLabel constant string
		JLabel Title = new JLabel("Info");
		Title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		Title.setBounds(606, 162, 69, 20);
		PokeFrame.getContentPane().add(Title);
		
		//Basic JLabel constant string
		JLabel SearchBox = new JLabel("Search...");
		SearchBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		SearchBox.setBounds(38, 162, 159, 20);
		PokeFrame.getContentPane().add(SearchBox);
		
					/////////////////////////////
					// ALWAYS CHANGING JLABERLS//
					/////////////////////////////
		
		//4 of the following Jlabels always change after a new pokemon is entered
		//JLabel This will change its Move string of Pokemon depending on Pokemon
		JLabel FillMoves = new JLabel("<html><html>");
		FillMoves.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillMoves.setBounds(552, 447, 115, 70);
		PokeFrame.getContentPane().add(FillMoves);
		
		//JLabel This will change its Species string of Pokemon depending Pokemon 
		JLabel FillSpecies = new JLabel("<html><html>");
		FillSpecies.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillSpecies.setBounds(552, 361, 170, 70);
		PokeFrame.getContentPane().add(FillSpecies);
	
		//JLabel This will change its Name string of Pokemon depending Pokemon 
		JLabel FillName = new JLabel("<html><html>");
		FillName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillName.setBounds(586, 178, 115, 70);
		PokeFrame.getContentPane().add(FillName);
		
		//JLabel This will change its Type string of Pokemon depending Pokemon 
		JLabel FillPokemonType = new JLabel("<html><html>");
		FillPokemonType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillPokemonType.setBounds(552, 281, 211, 70);
		PokeFrame.getContentPane().add(FillPokemonType);
		
					/////////////////////////////
					//  ERROR MESSEGE JLABERL  //
					/////////////////////////////
		
		//JLabel This particular one  is hidden unless a wrong userinput is made
		JLabel lblInvalidPokemonTry = new JLabel();
		lblInvalidPokemonTry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInvalidPokemonTry.setBounds(83, 198, 273, 44);
		PokeFrame.getContentPane().add(lblInvalidPokemonTry);
		
					/////////////////////////////
					//       SEARCHFIELD       //
					/////////////////////////////
		
		//JSearchField, used for user to type pokemon name into it
		JTextField SearchField = new JTextField();
		SearchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SearchField.setBounds(168, 159, 170, 29);



		///////////////////////////////////////
	//-----	//    INTELIGENT SEARCHFIELD ACTION  //-----//
		///////////////////////////////////////
		
		/* This is a listener. It will listen for events that
		 are emmitted on the search field. When there is an
		 event that's emmitted, the 'actionPerformed' function
		 is executed.*/
		 
		 //add actionlistener
		SearchField.addActionListener(new ActionListener(){
		 
		 //initialize an action performed (triggered when user presses enter after search)
			public void actionPerformed(ActionEvent e) {
		 		
		 		//two string variables "listen" takes the user string input
		 	    	//"input" takes that string from listen, makes it fully lowercase to process in database
		 	    	//this also makes the pokemon seach NOT-CASE-SENSITIVE for less frustration for user
				listen = SearchField.getText();
		 		input = listen.toLowerCase();
				
				//Object to search for appropriate pokemon or move in Data Base class
		 		Pokemon pokeFound = dbHelper.getPokemon(input);
		 		Move moveFound = dbHelper.getMove(pokeFound.getType());
	
				// if pokemon name is not found in DataBase an error string is returned and triggers the first condition
	 			if(pokeFound.getName()=="ERROR"){
	 				
	 			//erase anything that was already written in searchfield for easy retyping
	 			SearchField.setText(null);
	 			
	 			//Show the Invalid pokemon JLabel to show the user that there was an input error
	 			lblInvalidPokemonTry.setText("<html>Invalid Pokemon, Try Again!\r\nex...(Ditto, Magikarp)\r\n<html>");
	 			}
	 			
	 			else{
	 			//fill the 4 empty JLabels with appropriate strings retrieved from the DataBase
	 			FillName.setText(pokeFound.getName().toUpperCase());
	 		 	FillPokemonType.setText(pokeFound.getType());
	 		 	FillMoves.setText(moveFound.getMove());
	 		 	FillSpecies.setText(pokeFound.getSpecies());
	 		 	
	 		 	//Hide InvalidPokemon JLabel if the First case was triggered previously
		 		lblInvalidPokemonTry.setText(null
		 		
		 		// Int PI will take the Pokemon Index number of the pokemon found
	 		 	PI = pokeFound.getID();
	 		 	/*The hidden scrollbars will move to the appropriate pokemon on the Image Icon 
	 		 	using the Pokemon index  which is adjusted to scale using the two formulas*/
		 		scrollPane.getHorizontalScrollBar().setValue((((PI-1)%25)*160));
		 		scrollPane.getVerticalScrollBar().setValue(((PI-1)/25)*200); 
		 		
		 		//Redraw the ScrollPane to the appropriate pokemon
		 		PokeFrame.getContentPane().add(scrollPane);
		 		
		 		//erase anything that was already written in searchfield for easy retyping
		 		SearchField.setText(null);
		 		}
		 	}	
		 });
		
					///////////////////////////////////////
					//         POKEDEX BACKGROUND        //
					///////////////////////////////////////
		
		//Add JLabel that has taken an image of the PokePic.png and make it the Frames Backgroubd
		 PokeFrame.getContentPane().add(SearchField);
		 SearchField.setColumns(10);
		 JLabel BackGround = new JLabel("");
		 BackGround.setIcon(new ImageIcon("PokePic.png"));
		 BackGround.setBounds(0, 0, 778, 584);
		 PokeFrame.getContentPane().add(BackGround); 
	}
}
