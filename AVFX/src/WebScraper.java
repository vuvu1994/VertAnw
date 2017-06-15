import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;

/**
 * Created by Denni on 06.06.2017.
 */
public class WebScraper {

    private static String getFirstGoogleLink(String suche){
        Document doc;
        String googleSuche = "https://www.google.de/search?q=" + suche.replace(" ","+");
        try{
            doc = Jsoup.connect(googleSuche).userAgent("Mozilla").ignoreHttpErrors(true).timeout(10000).get();
            //System.out.println(doc);
            String Link = StringUtils.substringBetween(doc.toString(),"url?q=","&amp").replace("%3F","?").replace("%3D","=");
            return Link;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void getData(String Name){
        Document doc;
        try{
            File bildfile = new File("Cover/"+Name+".jpg");
            if (!bildfile.exists()) {
                System.out.println("Name:"+Name);
                String l = WebScraper.getFirstGoogleLink("imdb " + Name.toString());
                System.out.println("LinK: "+l);
                //bild download
                if (l.contains("imdb")) {
                    doc = Jsoup.connect(l).userAgent("Mozilla").ignoreHttpErrors(true).timeout(10000).get();
                    //System.out.println(doc);
                 String bild = StringUtils.substringBetween(doc.toString(), "src=\"https://images-na.ssl-images-amazon.com/images/M/", "\"");
                    bild = "https://images-na.ssl-images-amazon.com/images/M/" + bild;
                    Connection.Response resultImageResponse = Jsoup.connect(bild)
                            .ignoreContentType(true).execute();
                    FileOutputStream out = (new FileOutputStream(new File("Cover/" + Name + ".jpg")));
                    out.write(resultImageResponse.bodyAsBytes());
                    out.close();
                }
            }


        }
        catch (Exception e) {
            System.out.println("Konnte Bild nicht downloaden");
        }

    }


}
