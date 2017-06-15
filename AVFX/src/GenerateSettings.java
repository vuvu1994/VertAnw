import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Vural on 07.06.2017.
 */
public class GenerateSettings {

    private Settings settings;
    private String path = "Settings/";
    String[] Namen;

    public void readfiles(){
        File file = new File(path);
        Namen = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isFile();
            }
        });
        System.out.println(Namen[0].toString());
    }
    // Erzeugt Einträge in dennen die Pfade stehen für die ListViews
    public void createEntries(){


        for(int i =0;i < Namen.length;i++){
            settings = new Settings(Namen[i].replace(".txt",""));
            settings.create();
            settings.draw();
            getLinks(i);

        }
    }
    // Liesst die einzelnen Verzeichnisse aus
    public void getLinks(int i){

        try (BufferedReader br = new BufferedReader(new FileReader(new File(path+Namen[i])))) {
            String line;
            while ((line = br.readLine()) != null) {
                settings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createMediaSetting(){
        try {
            File file = new File("Settings\\Audiothek.txt");
            File file2 = new File("Settings\\Mediathek.txt");
            File fileFolder = new File("Settings");
            if (fileFolder.isDirectory()){

            }else{
                fileFolder.mkdirs();
            }

            if (file.exists()) {

            } else {
                PrintWriter outputStream = new PrintWriter("Settings\\Audiothek.txt");
                outputStream.close();
                outputStream.flush();
            }
            if(file2.exists()){

            }else{
                PrintWriter outputStream = new PrintWriter("Settings\\Mediathek.txt");
                outputStream.close();
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
