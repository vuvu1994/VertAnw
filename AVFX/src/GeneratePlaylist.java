import java.io.*;

/**
 * Created by Denni on 04.06.2017.
 */
public class GeneratePlaylist {

    private Playlist pl;
private String path = "Playlist/";
String[] Namen;
    public void readfiles(){
        File file = new File(path);
        Namen = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isFile();
            }
        });

    }
    public void create(){

        if (!(Namen == null)) {
            for (int i = 0; i < Namen.length; i++) {
                pl = new Playlist(Namen[i].replace(".txt", ""));
                pl.create();
                pl.draw();
                getLinks(i);

            }
        }
        Playlist.setNavigationbar();



    }
    public void getLinks(int i){

        try (BufferedReader br = new BufferedReader(new FileReader(new File(path+Namen[i])))) {
            String line;
            while ((line = br.readLine()) != null) {
                pl.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
