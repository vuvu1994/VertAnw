import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.StandardCopyOption;

/**
 * Created by Denni on 14.06.2017.
 */
public class RadioStream extends Thread{
    String url;
    URLConnection urlConnection;
    InputStream input;
    OutputStream outputStream;
    File file;
    String name;
    public RadioStream(String value,String Name){
        url = value;
        name = Name;
    }
    public void run(){

        try {

            input = new URL(url).openStream();

                byte[] buffer = new byte[input.available()];
                input.read(buffer);

                File targetFile = new File("Radio/"+name+".mp3");
                java.nio.file.Files.copy(
                    input,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);

            IOUtils.closeQuietly(input);
           input.close();

            System.out.println("Closed");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void kill(){

        System.gc();
        Thread.currentThread().stop();

    }
}
