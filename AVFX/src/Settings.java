import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {

	static Properties prop = new Properties();
	static OutputStream output;
	static InputStream input;

	// Saving Properties and the changes in a XML File
	static void save() {

		try {
			output = new FileOutputStream("C:/Users/Vural/Desktop/f.xml");
			prop.storeToXML(output, null);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Loads Properties from a XML File
	static void load() {
		try {
			input = new FileInputStream("C:/Users/Vural/Desktop/f.xml");

			// load a properties file
			prop.loadFromXML(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// Creates a Property with default values if there is no XML File
	static void createProperties() {
		File file = new File("C:/Users/Vural/Desktop/Settings.xml");
		if (file.exists()) {

		} else {
			prop.setProperty("DatabasePath", "C:");
			prop.setProperty("MediaPath", "D:");
			prop.setProperty("width", "G");
			prop.setProperty("height", "600");
			prop.setProperty("Language", "400");
			prop.setProperty("design", "DBZ");

			save();

		}

	}

	static void setDatabasePath(String Db) {

		prop.setProperty("DatabasePath", Db);
		save();

	}

	static void getDatabasePath() {
		load();
		System.out.println(prop.getProperty("DatabasePath"));
	}

	static void setMediaPath(String Mp) {

		prop.setProperty("MediaPath", Mp);
		save();

	}

	static void getMediaPath() {
		load();
		System.out.println(prop.getProperty("MediaPath"));
	}

	static void setWidth(String sw) {

		prop.setProperty("width", sw);
		save();

	}

	static void getWidth() {
		load();
		System.out.println(prop.getProperty("width"));
	}

	static void setHeight(String sh) {

		prop.setProperty("height", sh);
		save();

	}

	static void getHeight() {
		load();
		System.out.println(prop.getProperty("height"));
	}

	static void setLanguage(String sl) {

		prop.setProperty("design", sl);
		save();

	}

	static void getLanguage() {
		load();
		System.out.println(prop.getProperty("Language"));
	}

	static void setDesign(String sd) {

		prop.setProperty("design", sd);
		save();

	}

	static void getDesign() {
		load();
		System.out.println(prop.getProperty("design"));
	}

	static void getAllProperties() {
		load();
		System.out.println(prop.getProperty("DatabasePath"));
		System.out.println(prop.getProperty("MediaPath"));
		System.out.println(prop.getProperty("width"));
		System.out.println(prop.getProperty("height"));
		System.out.println(prop.getProperty("Language"));
		System.out.println(prop.getProperty("design"));

	}
}