package transientwatch.com.transientwatch.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import transientwatch.com.transientwatch.Model.NewsItem;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.parser.EsaHTMLParser;
import transientwatch.com.transientwatch.parser.Parser;

/**
 * Created by sngv on 11/04/15.
 */
public class TransientDataFetcher {
    private static Parser parser = new EsaHTMLParser();
    private static List<Transient> data;

    private static List<NewsItem> newsData;

    public static File cacheFolder;

    public static File cacheFile(){
        return new File(cacheFolder, "mycache.komalo");
    }

    public synchronized static List<Transient> getData(){

        try{
            if(data == null){
                try {
                    FileInputStream in =  new FileInputStream(cacheFile());
                    ObjectInputStream objIn = new ObjectInputStream(in);
                    data =  (List<Transient>) objIn.readObject();
                    objIn.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(data);

                if(data == null){
                    data = parser.getData();

                    try {
                        FileOutputStream outputStream =  new FileOutputStream(cacheFile());
                        ObjectOutputStream objOut = new ObjectOutputStream(outputStream);
                        objOut.writeObject(data);
                        objOut.close();
                        outputStream.close();
                        System.out.println("Success");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }

    public static List<Transient> getUpdatedDataNotCached() throws Exception{
        return parser.getData();
    }

    public static List<NewsItem> getNews(){
        if(newsData == null)
            newsData = new ArrayList<>();
        return newsData;
    }
}
