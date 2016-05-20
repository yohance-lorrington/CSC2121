import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBaseHelper{

	private String connectionString ="jdbc:hsqldb:testdb,sa,";
	private Connection connection; 
	private final String P_ID ="ID";
	private final String P_NAME = "Name";
	private final String P_TYPE = "Type";
	private final String P_SPEC = "Species";
	private final String tablename = "PII"; //keep table names caps
	private final String movetable = "MII";
	private final String M_ID = "typeID";
	private final String M_ATTACK = "moveAttack";
	private Statement state;

	
	 /**
	 *  Connects to the database and checks if there is a database already in existence.
	 *  If not it will create a database with a table with the parameters(ID,NAME,TYPE,SPECIES) 
	 * and a second table with the parameters(ID,MOVE);
	 **/
	public DataBaseHelper(){
		openConnection();
		try {
			if (!doesPokemonDatabaseExists()) createDatabase();
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}

	}
	/**
	 * Checks if there is a database with the same name in existancisence. 
	 * If there is a database with the same name it will return true.
	 * Can throw a SQLException if there was not a connection made prior
	 * **/
	public boolean doesPokemonDatabaseExists() throws SQLException{
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(null, null, tablename, null);
		return rs.next();
	}

	/**
	 * Tries to open a connection to the database. 
	 * 
	 * **/
	public void openConnection(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			this.connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found!");
		} catch (SQLException e) {
			System.out.println("Connection Could Not Be Established!");
			e.printStackTrace();
		}	
	}

	/**
	 * Tries to close a connection to the database. 
	 * 
	 * **/
	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("No Connection Found!");
		}
	}
	
	/**
	 *  Tries to create two tables one with the parameters( ID INTEGER,NAME VARCHAR,TYPE VARCHAR, SPECIES VARCHAR)
	 * and another table with parameters (ID VARCHAR,MOVE VARCHAR)
	 * **/
	public void createDatabase(){
		String sql = "CREATE TABLE "+tablename+"("+P_ID+" INTEGER, "+P_NAME+" VARCHAR(24), "+P_TYPE+" VARCHAR(24), "+P_SPEC+" VARCHAR(24));"
				+"CREATE TABLE "+movetable+"("+M_ID+" VARCHAR(24), "+M_ATTACK +" VARCHAR(24));";
		
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
			
			
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
			
		}
	}

	/**
	 * Inserts a Pokemon object into the first table
	***/
	public void insertPokemon(Pokemon some){
		String sql = "INSERT INTO "+tablename+" VALUES("+some.getID()+", '"+some.getName()+"', '"+some.getType()+"', '"+some.getSpecies()+"');";
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
	}
	// Inserts a Type and Move into the second table
	public void insertTypeandMove(String name,String move){
		String sql = "INSERT INTO "+movetable+" VALUES('"+name+"', '"+move+"');";
		try{
			state = connection.createStatement();
			state.executeQuery(sql);
		}catch(SQLException e){
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		
		
	}

	/**
	 *  Drops both tables from the database unless there was not a connection made to
	 * the database
	 * **/
	public void removeDatabases(){
			try {
				if(doesPokemonDatabaseExists()){
					removePokemonDatabase();
					removeMoveDatabase();
				}
			} catch (SQLException e) {
				System.out.println("Connection Was Not Made");
				e.printStackTrace();
			}
		

	}
	//Helper function to drop the table with the pokemon infomation 
	private void removePokemonDatabase(){
		String sql = "DROP TABLE "+tablename;
		
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}		
	}
	//Helper function to drop the table with the move infomation 
	private void removeMoveDatabase(){
		String sql2 = "DROP TABLE "+movetable;
		try {
			state = connection.createStatement();
		} catch (SQLException e1) {
			System.out.println("Connection Was Not Made");
			e1.printStackTrace();
		}
		try {
			state.execute(sql2);
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		
	}
	/**
	 * removes the data assoicated with the pokemon with the given id
	 */
	public void deletePokemon(Integer id){
		String sql = "DELETE FROM "+tablename+" WHERE "+P_ID+"="+id+";";
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
	}
	/**
	 *  Returns a new Pokemon object that has the id passed
	 */
	public Pokemon getPokemon(Integer id){
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_ID+"="+id+";";
		Pokemon selected = new Pokemon();
		try {
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			
			// resultSet maintains cursor in a table of results.
			// The cursor initially points before the first record
			// of the table. So, if r.next() returns false, then
			// the result set is empty. If it is true, then 
			// the cursor moves to the first and only result
			// that is returned.
			if(r.next()) {
				selected = new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC));	
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made!");
			e.printStackTrace();
		}
		return selected;
	}
	/**
	 *  Returns a Pokemon by its name
	 */
	public Pokemon getPokemon(String name){
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_NAME+"='"+name+"';";
		Pokemon selected = new Pokemon();
		try {
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				selected = new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC));	
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made!");
			e.printStackTrace();
		}
		return selected;
	}
	/**
	 * Returns a List of Pokemon with a type given
	 */
	public ArrayList<Pokemon> getPokemonByType(String typez){
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_TYPE+"='"+typez+"';";
		try {
			state = connection.createStatement();
			ResultSet r =state.executeQuery(sql);
	
			while(r.next()){
				pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC)));
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}

		return pokemons;
	}
	/**
	 * Return all the Pokemons from the database
	 */
	public ArrayList<Pokemon> gottaCatchEmAll(){
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+";";

		try {
			state = connection.createStatement();
			ResultSet r =state.executeQuery(sql);
			while(r.next()){
				pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC)));
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		return pokemons;
	}
	/**
	 * 
	 * Return a move for a given type 
	 * 
	 * */
	public Move getMove(String typez){
		String sql = "SELECT * FROM "+movetable+" WHERE "+M_ID+"='"+typez+"';";
		Move move = new Move();
		
		try {
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			if (r.next()){
				move = new Move(r.getString(M_ID), r.getString(M_ATTACK));}
			} catch (SQLException e) {
				System.out.println("Type Not Found!");
				e.printStackTrace();
			}
			return move;
	}
	/**
	 * 
	 * Return a move for a given pokemon
	 * 
	 * */
	public ArrayList<Move> getMoves(Pokemon some){
		String sql = "SELECT * FROM "+movetable+" WHERE "+M_ID+"='"+some.getType()+"';";
		ArrayList<Move> movesList = new ArrayList<Move>();
		try{
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			while(r.next()){
				movesList.add(new Move(r.getString(M_ID),r.getString(M_ATTACK)));
			}
			
		}catch(SQLException e){
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		return movesList;
	}
	/**
	 * 
	 * Return a List of moves for a given type 
	 * 
	 * */
	public ArrayList<Move> getMoves(String typez){
		String sql = "SELECT * FROM "+movetable+" WHERE "+M_ID+"='"+typez+"';";
		ArrayList<Move> movesList = new ArrayList<Move>();
		try{
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			while(r.next()){
				movesList.add(new Move(r.getString(M_ID),r.getString(M_ATTACK)));
			}
			
		}catch(SQLException e){
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		return movesList;
	}
	/**
	 * 
	 * Return all the Moves from the database
	 * 
	 * **/
	public ArrayList<Move> getAllMoves() {
		String sql = "SELECT * FROM "+movetable+";";
		ArrayList<Move> movesList = new ArrayList<Move>();
		try{
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			while(r.next()){
				movesList.add(new Move(r.getString(M_ID),r.getString(M_ATTACK)));
			}
			
		}catch(SQLException e){
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		return movesList;
	}
}
