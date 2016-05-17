import java.awt.EventQueue;
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

	JFrame PokeFrame;
	String listen;
	String input;
    int PI = 151 ;
	DataBaseHelper dbHelper;

	

	/**
	 * Create the application.
	 */
	public Frame(DataBaseHelper DBH) {
		dbHelper = DBH;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		PokeFrame = new JFrame();
		PokeFrame.setResizable(false);
		PokeFrame.setTitle("Jetlir'sGUI");
		PokeFrame.setBounds(100, 100, 780, 620);
		PokeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PokeFrame.getContentPane().setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("giphy.gif").getImage().getScaledInstance(4000, 4000, Image.SCALE_DEFAULT));
		final JScrollPane scrollPane = new JScrollPane();	
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPane.setViewportView(new JLabel(imageIcon)); 
	    	scrollPane.getVerticalScrollBar().setValue(0);
		 scrollPane.getHorizontalScrollBar().setValue(0);
		scrollPane.setBounds(109, 260, 159, 195);
		PokeFrame.getContentPane().add(scrollPane);
		
		final JLabel lblInvalidPokemonTry = new JLabel();
		lblInvalidPokemonTry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInvalidPokemonTry.setBounds(83, 198, 273, 44);
		PokeFrame.getContentPane().add(lblInvalidPokemonTry);
		
		final JLabel FillAbilities = new JLabel("<html><html>");
		FillAbilities.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillAbilities.setBounds(552, 447, 115, 70);
		PokeFrame.getContentPane().add(FillAbilities);
		
		final JLabel FillSpecies = new JLabel("<html><html>");
		FillSpecies.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillSpecies.setBounds(552, 361, 170, 70);
		PokeFrame.getContentPane().add(FillSpecies);
		
		JLabel lblabilities = new JLabel("<html>Abilities : <html>");
		lblabilities.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblabilities.setBounds(490, 406, 115, 70);
		PokeFrame.getContentPane().add(lblabilities);
		
		JLabel lblspecies = new JLabel("<html>Species : <html>");
		lblspecies.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblspecies.setBounds(490, 320, 115, 70);
		PokeFrame.getContentPane().add(lblspecies);
		
		JLabel PokemonType = new JLabel("<html>Pokemon Type : <html>");
		PokemonType.setFont(new Font("Tahoma", Font.BOLD, 20));
		PokemonType.setBounds(490, 234, 211, 70);
		PokeFrame.getContentPane().add(PokemonType);
		
		JLabel Name = new JLabel("<html>Name : <html>");
		Name.setFont(new Font("Tahoma", Font.BOLD, 20));
		Name.setBounds(490, 178, 115, 70);
		PokeFrame.getContentPane().add(Name);
		
		final JLabel FillName = new JLabel("<html><html>");
		FillName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillName.setBounds(586, 178, 115, 70);
		PokeFrame.getContentPane().add(FillName);
		
		JLabel Title = new JLabel("Info");
		Title.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		//lblInfo.setFont(new Font("Serif", Font.PLAIN, 14));
		Title.setBounds(606, 162, 69, 20);
		PokeFrame.getContentPane().add(Title);
		
		JLabel SearchBox = new JLabel("Search...");
		SearchBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		SearchBox.setBounds(38, 162, 159, 20);
		PokeFrame.getContentPane().add(SearchBox);
		
		final JLabel FillPokemonType = new JLabel("<html><html>");
		FillPokemonType.setFont(new Font("Tahoma", Font.PLAIN, 20));
		FillPokemonType.setBounds(552, 281, 211, 70);
		PokeFrame.getContentPane().add(FillPokemonType);

	    	final JTextField SearchField = new JTextField();
		SearchField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		SearchField.setBounds(168, 159, 170, 29);
		SearchField.addActionListener(new ActionListener(){
		 
			public void actionPerformed(ActionEvent e) {
		 	    
				listen = SearchField.getText();
		 		input = listen.toLowerCase();
				
		 		Pokemon pokeFound = dbHelper.getPokemon(input);
	 			if(pokeFound.getName()=="ERROR"){SearchField.setText(null);lblInvalidPokemonTry.setText("<html>Invalid Pokemon, Try Again!\r\nex...(Ditto, Magikarp)\r\n<html>");}
	 			
	 			else{
	 			FillName.setText(pokeFound.getName().toUpperCase());
	 		 	FillPokemonType.setText(pokeFound.getType());
	 		 	FillAbilities.setText(pokeFound.getAbil());
	 		 	FillSpecies.setText(pokeFound.getSpecies());
		 		lblInvalidPokemonTry.setText(null);
	 		 	PI = pokeFound.getID();
		 		scrollPane.getHorizontalScrollBar().setValue((((PI-1)%25)*160));
		 		scrollPane.getVerticalScrollBar().setValue(((PI-1)/25)*200); 
		 		PokeFrame.getContentPane().add(scrollPane);
		 	    System.out.println(pokeFound.getName());
		 		SearchField.setText(null);
		 		}
		 	}	
		 });
		
		 PokeFrame.getContentPane().add(SearchField);
		 SearchField.setColumns(10);
		 JLabel BackGround = new JLabel("");
		 BackGround.setIcon(new ImageIcon("PokePic.png"));
		 BackGround.setBounds(0, 0, 778, 584);
		 PokeFrame.getContentPane().add(BackGround); 
	}
}
