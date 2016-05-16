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
	private final String P_ABIL = "Abilities";
	private final String tablename = "PII";//keep table names caps
	private final String movetable = "MII";
	private final String M_ID = "typeID";
	private final String M_ATTACK = "moveAttack";
	private Statement state;

	
	 /**
	 *  Connects to the database and checks if there is a database already in existence.
	 *  If not it will create a database with the parameters(ID,NAME,TYPE,SPECIES,ABILITIES);
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
	
	public boolean doesPokemonDatabaseExists() throws SQLException{
		DatabaseMetaData md = connection.getMetaData();
		ResultSet rs = md.getTables(null, null, tablename, null);
		return rs.next();
	}


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

	public void closeConnection(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("No Connection Found!");
		}
	}

	public void createDatabase(){
		String sql = "CREATE TABLE "+tablename+"("+P_ID+" INTEGER, "+P_NAME+" VARCHAR(24), "+P_TYPE+" VARCHAR(24), "+P_SPEC+" VARCHAR(24), "+P_ABIL+" VARCHAR(24));"
				+"CREATE TABLE "+movetable+"("+M_ID+" VARCHAR(24), "+M_ATTACK +" VARCHAR(24));";
		
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
			
			
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
			
		}
	}


	public void insertPokemon(Pokemon some){
		String sql = "INSERT INTO "+tablename+" VALUES("+some.getID()+", '"+some.getName()+"', '"+some.getType()+"', '"+some.getSpecies()+"', '"+some.getAbil()+"');";
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
	}
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
	 * 
	 */
	public Pokemon getPokemon(Integer id){
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_ID+"="+id+";";
		Pokemon selected = new Pokemon();
		try {
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				selected = new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL));	
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made!");
			e.printStackTrace();
		}
		return selected;
	}
	/**
	 * 
	 */
	public Pokemon getPokemon(String name){
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_NAME+"='"+name+"';";
		Pokemon selected = new Pokemon();
		try {
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				selected = new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL));	
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made!");
			e.printStackTrace();
		}
		return selected;
	}
	/**
	 * 
	 * @param typez
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Pokemon> getPokemonByType(String typez){
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_TYPE+"='"+typez+"';";
		try {
			state = connection.createStatement();
			ResultSet r =state.executeQuery(sql);
	
			while(r.next()){
				pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL)));
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}

		return pokemons;
	}
	/**
	 * 
	 * @param typez
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Pokemon> getPokemonByType(Move.TYPES typez){
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_TYPE+"='"+typez.toString()+"';";
		try {
			state = connection.createStatement();
			ResultSet r =state.executeQuery(sql);
			while(r.next()){
				pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL)));
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		return pokemons;
	}
	/**
	 */
	public ArrayList<Pokemon> gottaCatchEmAll(){
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+";";

		try {
			state = connection.createStatement();
			ResultSet r =state.executeQuery(sql);
			while(r.next()){
				pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL)));
			}
		} catch (SQLException e) {
			System.out.println("Connection Was Not Made");
			e.printStackTrace();
		}
		return pokemons;
	}
	
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
	public ArrayList<Move> getMoves(Move.TYPES typez){
		String sql = "SELECT * FROM "+movetable+" WHERE "+M_ID+"='"+typez.toString()+"';";
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
