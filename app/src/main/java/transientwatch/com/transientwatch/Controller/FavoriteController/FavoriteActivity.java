package transientwatch.com.transientwatch.Controller.FavoriteController;

import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import transientwatch.com.transientwatch.Controller.NavigationController.NavigationDrawerFragment;
import transientwatch.com.transientwatch.Model.NewsItem;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;
import transientwatch.com.transientwatch.Service.NewsAdapter;
import transientwatch.com.transientwatch.Service.TransientDataFetcher;


public class FavoriteActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.favorite_layout));
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private NewsAdapter newsAdapter;

        public static final int UPDATE_INTERVAL = 1000 * 60;

        private static final String ARG_SECTION_NUMBER = "Favorite";

        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
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
}
