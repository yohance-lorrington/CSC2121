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
	private final String P_ID ="id";
	private final String P_NAME = "name";
	private final String P_TYPE = "type";
	private final String tablename = "TABLENAME";
	//private final String P_IMAGE = "image";
	//private final String 
	private Statement state;
	/**
	 *  Connects to the database and checks if there is a database already in existence.
	 *  If not it will create a database with the parameters(ID,NAME,TYPE);
	 *   
	 *  @throws SQLException
	 *  @throws ClassNotFoundException - if the class cannot be located
	 */
	public DataBaseHelper() throws SQLException,ClassNotFoundException{
		openConnection();	
		if(!doesDatabaseExists())createDatabase();
	}
	public boolean doesDatabaseExists()throws SQLException{
		DatabaseMetaData dbm =connection.getMetaData();
		ResultSet table = dbm.getTables(null, null, tablename, null);
		return table.next();
	}
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void openConnection()throws ClassNotFoundException,SQLException{
		Class.forName("org.hsqldb.jdbcDriver");
		this.connection = DriverManager.getConnection(connectionString);
	}
	/**
	 * 
	 * @throws SQLException
	 */
	public void closeConnection()throws SQLException{
		this.connection.close();
	}
	/**
	 * 
	 * @throws SQLException
	 */
	public void createDatabase()throws SQLException{
		String sql = "CREATE TABLE "+tablename+"("+P_ID+" INTEGER, "+P_NAME+" VARCHAR(24), "+P_TYPE+" VARCHAR(24));";
		state = connection.createStatement();
		state.executeQuery(sql);
	}
	/**
	 * 
	 * @param some
	 * @throws SQLException
	 * @see Pokemon
	 */
	public void insertPokemon(Pokemon some)throws SQLException{
		String sql = "INSERT INTO "+tablename+" VALUES("+some.getID()+", '"+some.getName()+"', '"+some.getType()+"');";
		state = connection.createStatement();
		state.executeQuery(sql);
		
	}
	/**
	 * 
	 * @throws SQLException
	 */
	public void removeDatabase()throws SQLException{
		String sql = "DROP TABLE "+tablename;
		state = connection.createStatement();
		state.executeQuery(sql);
		
	}
	/**
	 * 
	 * @param some
	 * @throws SQLException
	 * @see Pokemon
	 */
	public void deletePokemon(Pokemon some)throws SQLException{
		String sql = "DELETE FROM "+tablename+" WHERE "+P_ID+"="+some.getID()+";";
		state = connection.createStatement();
		state.executeQuery(sql);
	}
	/**
	 * 
	 * @param id
	 * @return 
	 * @throws SQLException
	 */
	public Pokemon getPokemon(Integer id)throws SQLException{
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_ID+"='"+id+"';";
		state = connection.createStatement();
		ResultSet r = state.executeQuery(sql);
	
		r.next();
		return new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE));
	}
	/**
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public Pokemon getPokemon(String name)throws SQLException{
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_NAME+"='"+name+"';";
		state = connection.createStatement();
		ResultSet r = state.executeQuery(sql);
		r.next();
		return new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE));
	}
	/**
	 * 
	 * @param typez
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Pokemon> getPokemonByType(String typez)throws SQLException{
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_TYPE+"='"+typez+"';";
		state = connection.createStatement();
		ResultSet r =state.executeQuery(sql);
	
		while(r.next()){
			pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE)));
		}
		return pokemons;
	}
	/**
	 * 
	 * @param typez
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Pokemon> getPokemonByType(Pokemon.TYPES typez)throws SQLException{
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_TYPE+"='"+typez.toString()+"';";
		state = connection.createStatement();
		ResultSet r =state.executeQuery(sql);
	
		while(r.next()){
			pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE)));
		}
		return pokemons;
	}
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Pokemon> gottaCatchEmAll()throws SQLException{
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+";";
		state = connection.createStatement();
		ResultSet r =state.executeQuery(sql);
		while(r.next()){
			pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE)));
		}
		return pokemons;
	}

}
