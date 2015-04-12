package transientwatch.com.transientwatch.Controller.FavoriteController;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.List;

import transientwatch.com.transientwatch.Model.NewsItem;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;
import transientwatch.com.transientwatch.Service.NewsAdapter;
import transientwatch.com.transientwatch.Service.TransientDataFetcher;

/**
 * A placeholder fragment containing a simple view.
 */
public class FavoriteFragment extends Fragment {

    private NewsAdapter newsAdapter;

    public static final int UPDATE_INTERVAL = 1000 * 60;

    private static final String ARG_SECTION_NUMBER = "Favorite";

    public FavoriteFragment() {
    }
    public static FavoriteFragment newInstance(int sectionNumber) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        newsAdapter = new NewsAdapter(getActivity(), TransientDataFetcher.getNews());

        ListView newsListView = (ListView) rootView.findViewById(R.id.favorite_news_list);

        newsListView.setAdapter(newsAdapter);

        handler.postDelayed(updaterFunction, UPDATE_INTERVAL);

        return rootView;
    }
    final Handler handler = new Handler();
    Runnable updaterFunction = new Runnable() {

        @Override
        public void run() {
            try{
                List<Transient> oldData = TransientDataFetcher.getData();
                List<Transient> newData = TransientDataFetcher.getUpdatedDataNotCached();

                for(int i=0; i<oldData.size(); i++){
                    Transient oldDataItem = oldData.get(i);

                    for(int j=0; j<newData.size(); j++){
                        Transient newDataItem = newData.get(j);
                        if(oldDataItem.getName().equals(newDataItem.getName())){
                            for(Field field : newDataItem.getClass().getDeclaredFields()){
                                if(!field.get(oldDataItem).equals(field.get(newDataItem)) && !field.getName().equals("followed")){
                                    NewsItem newsItem = new NewsItem();
                                    newsItem.setName(newDataItem.getName());
                                    newsItem.setChangedAttributeName(field.getName().toUpperCase());
                                    newsItem.setNewValue(field.get(newDataItem).toString());
                                    TransientDataFetcher.getNews().add(newsItem);
                                    newsAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    }
                }


                handler.postDelayed(this, 1000);
            }
            catch (Exception e) {
                // TODO: handle exception
            }
            finally{
                //also call the same runnable
                handler.postDelayed(this, 1000);
            }
        }
    };
}