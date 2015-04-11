package transientwatch.com.transientwatch.Service;

import java.util.ArrayList;
import java.util.List;

import transientwatch.com.transientwatch.Model.TransientItem;

/**
 * Created by sngv on 11/04/15.
 */
public class TransientDataFetcher {
    public static List<TransientItem> getData(){
        ArrayList<TransientItem> items = new ArrayList<TransientItem>();
        for(int i = 0 ;i < 10 ; i++){
            TransientItem tmp = new TransientItem();
            tmp.setDec("item " + Integer.toString(i));
            tmp.setName("item " + Integer.toString(i));
            tmp.setOp("item " + Integer.toString(i));
            tmp.setRa("item " + Integer.toString(i));
            items.add(tmp);
        }
        return items;
    }
}
