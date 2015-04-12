package transientwatch.com.transientwatch.Service;

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

    public static List<Transient> getData(){
        try{
            if(data == null){
                data = parser.getData();
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
