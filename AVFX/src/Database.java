
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.h2.jdbcx.JdbcConnectionPool;

public class Database {
	static Connection conn = null; 
	
	public static void getDatabase(){
	try {
		
		//Laden des Treibers
		Class.forName("org.h2.Driver");
		//Datenbank erzeugen und Verbindung zur Datenbank herstellen
		JdbcConnectionPool cp = JdbcConnectionPool.create(
	            "jdbc:h2:file:C:/Users/Denni/Desktop;", "", "");
		conn = cp.getConnection(); 

	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	//Rückgabe der Vorhanden Tabelle ( falls keine Vorhanden ist Fehlermeldung)
	public static void getTable() throws SQLException{
		DatabaseMetaData md = conn.getMetaData(); 
		ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
			System.out.println(rs.getString("TABLE_NAME"));
		}
	}
	//Erzeugen der Tabelle "Media" falls diese noch nicht angelegt wurde
	public static void createTable() throws SQLException{
		System.out.println("Erstelle Table");
	      Statement stmt = conn.createStatement();
	      
	      String sql = "CREATE TABLE IF NOT EXISTS \""+"Media"+ "\" " +
	                   "(Name VARCHAR(255) not NULL, " +
	                   " Dauer Real, " + 
	                   " aktuell REAL, " + 
	                   " PRIMARY KEY ( Name ))"; 

	      stmt.executeUpdate(sql);
	      stmt.close();
	      System.out.println("Fertig");
	      
	}

	//Hinzufügen aller Elemente die sich in dem Lokalen Verzeichnis befinden	
	public static void addtoMedia(String Name) throws SQLException{
		Statement stmt = conn.createStatement();
		ArrayList test = getAllMedia();
		//System.out.println("Erster Name: "+test.get(0));
		if (!test.toString().contains(Name)){
        String sql = "INSERT INTO \""+"Media"+"\" (NAME) " + "VALUES ('"+Name.replaceAll("'", "''")+"')";

        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("added");
		}
		
	}
	//Aktuallisieren der alle Audio/ Video Elemente in der Tabelle Media
	public static void updateaktuell(String Name,Double Min) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			
	        String sql = "UPDATE \""+"Media"+"\" SET aktuell= "+Min+" WHERE NAME='"+Name.replaceAll("'", "''")+"';";

	        stmt.executeUpdate(sql);
	        stmt.close();
	        System.out.println("Inserted records into the table...");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		//  Aktualisieren der Länger der Audio / Video Dateien
		public static void updateDauer(String Name,Double Max) {
			Statement stmt;
			try {
				stmt = conn.createStatement();
				
		        String sql = "UPDATE \""+Name+"\" SET Dauer= "+Max+" WHERE NAME='"+Name.replaceAll("'", "''")+"';";

		        stmt.executeUpdate(sql);
		        stmt.close();
		        System.out.println("Inserted records into the table...");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	//Rückgabe aller Audio / Video Elemente im Lokalen Verzeichnis um diese in dei ArrayListe zu Speichern 
	public static ArrayList getAllMedia() throws SQLException{
		ArrayList all= new ArrayList();
		Statement stmt = conn.createStatement();
		
        String sql = "SELECT NAME FROM \""+"Media"+"\"";

        ResultSet rs =stmt.executeQuery(sql);
       
        while (rs.next()) {
            all.add(rs.getString(1));
           
            System.out.println(rs.getString(1));
        }

       
        return all;
	}
	//Rückgabe des Namens der Aktuell ausgewählten Audio / Video Datei
	public static String getaktuell(String Name) throws SQLException{
		ArrayList all= new ArrayList();
		Statement stmt = conn.createStatement();
		
        String sql = "SELECT aktuell FROM \""+"Media"+"\" WHERE NAME='"+Name.replaceAll("'", "''")+"';";

        ResultSet rs =stmt.executeQuery(sql);
       
        while (rs.next()) {
            all.add(rs.getString(1));
           
            System.out.println(rs.getString(1));
        }

       if (all.get(0) ==null){
    	   return "1";
       }
        return all.get(0).toString();
	}
	////Rückgabe der Dauer der Aktuellen ausgewählten Audio / Video Datei
	public static String getDauer(String Name) throws SQLException{
		ArrayList all= new ArrayList();
		Statement stmt = conn.createStatement();
		
        String sql = "SELECT Dauer FROM \""+"Media"+"\" WHERE KAPITELNAME='"+Name.replaceAll("'", "''")+"';";

        ResultSet rs =stmt.executeQuery(sql);
       
        while (rs.next()) {
            all.add(rs.getString(1));
           
            System.out.println(rs.getString(1));
        }

       if (all.get(0) ==null){
    	   return "9999";
       }
        return all.get(0).toString();
	}

	
	

	

	
}
