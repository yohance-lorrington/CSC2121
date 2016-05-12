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
	private final String tablename = "TABLENAME";
	//private final String P_IMAGE = "image";
	//private final String 
	private Statement state;
	
	 /**
	 *  Connects to the database and checks if there is a database already in existence.
	 *  If not it will create a database with the parameters(ID,NAME,TYPE);
	 **/
	public DataBaseHelper(){
		openConnection();
		try {
			if (!doesDatabaseExists()) createDatabase();
		} catch (SQLException e) {
			System.out.println("No Database Can Be Found!");
		}	
	}
	
	public boolean doesDatabaseExists()throws SQLException{
		DatabaseMetaData dbm =connection.getMetaData();
		ResultSet table = dbm.getTables(null, null, tablename, null);
		return table.next();
	}

	public void openConnection(){
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			this.connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not Found!");
		} catch (SQLException e) {
			System.out.println("Connection Could Not Be Established!");
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
		String sql = "CREATE TABLE "+tablename+"("+P_ID+" INTEGER, "+P_NAME+" VARCHAR(24), "+P_TYPE+" VARCHAR(24), "+P_SPEC+" VARCHAR(24), "+P_ABIL+" VARCHAR(24));";
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Database Already Exists!");
		}
	}

	public void insertPokemon(Pokemon some){
		String sql = "INSERT INTO "+tablename+" VALUES("+some.getID()+", '"+some.getName()+"', '"+some.getType()+"', '"+some.getSpecies()+"', '"+some.getAbil()+");";
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeDatabase(){
		String sql = "DROP TABLE "+tablename;
		try {
			state = connection.createStatement();
			state.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Database Does Not Exist!");
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
			System.out.println("Pokemon Is Not In Database!");
		}
	}
	/**
	 * 

	 */
	public Pokemon getPokemon(Integer id){
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_ID+"='"+id+"';";
		Pokemon selected = new Pokemon();
		try {
			state = connection.createStatement();
			ResultSet r = state.executeQuery(sql);
			if(r.next()) {
				selected = new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL));	
			}
		} catch (SQLException e) {
			System.out.println("Pokemon Does Not Exist!");
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
			System.out.println("Pokemon Does Not Exist!");
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
<<<<<<< HEAD
			e.printStackTrace();//Bad connection null statement
=======
			System.out.println("No Such Pokemon Type!");
>>>>>>> origin/master
		}

		return pokemons;
	}
	/**
	 * 
	 * @param typez
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Pokemon> getPokemonByType(Pokemon.TYPES typez){
		ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
		String sql = "SELECT * FROM "+tablename+" WHERE "+P_TYPE+"='"+typez.toString()+"';";
		try {
			state = connection.createStatement();
			ResultSet r =state.executeQuery(sql);
			while(r.next()){
				pokemons.add(new Pokemon(r.getInt(P_ID),r.getString(P_NAME),r.getString(P_TYPE),r.getString(P_SPEC),r.getString(P_ABIL)));
			}
		} catch (SQLException e) {
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
			e.printStackTrace();
		}
		return pokemons;
	}

}
