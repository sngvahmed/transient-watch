package transientwatch.com.transientwatch.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.parser.EsaHTMLParser;
import transientwatch.com.transientwatch.parser.Parser;

/**
 * Created by sngv on 11/04/15.
 */
public class TransientDataFetcher {
    private static Parser parser = new EsaHTMLParser();

    public static List<Transient> getData() throws Exception{
        return parser.getData();
    }
}
