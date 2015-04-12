package transientwatch.com.transientwatch.Controller.MainController;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.security.Provider;
import java.util.List;

import transientwatch.com.transientwatch.Controller.DetailsController.DetailsActivity;
import transientwatch.com.transientwatch.Model.NewsItem;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;
import transientwatch.com.transientwatch.Controller.*;
import transientwatch.com.transientwatch.Service.TransientAdapter;
import transientwatch.com.transientwatch.Service.TransientDataFetcher;

public class MainControllerFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "Main";

    public static ListView transientItemListView;
    public static TransientAdapter transientAdapter;
    public static List<Transient> transientData;


    public static MainControllerFragment newInstance(int sectionNumber) {
        MainControllerFragment fragment = new MainControllerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public void updateListCustome(List<Transient> list) {
        List<Transient> CustometransientData = list;
        transientAdapter = new TransientAdapter(getActivity() , CustometransientData);
        transientItemListView.setAdapter(transientAdapter);
    }

    public void onClickListnerInit(){
        transientItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                System.out.println("WWWWWTF");
                final Transient selectedItem = transientData.get(position);
                Button follow = (Button) view.findViewById(R.id.follow);
                follow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("WTF");
                        if(selectedItem.isFollowed()){
                            selectedItem.setFollowed(false);
                            ((Button) v).setText("Follow");
                        }
                        else {
                            selectedItem.setFollowed(true);
                            ((Button) v).setText("Unfollow");
                            Transient newDataItem = transientData.get(position);
                            try{
                                for(Method method : newDataItem.getClass().getDeclaredMethods()){
                                    if(method.getName().startsWith("get")) {
                                        Object value = method.invoke(newDataItem);
                                        String valueStr = value == null ? "" : value.toString();

                                        if (value != null && valueStr.length() > 0 && !valueStr.equals("NO DATA") && !method.getName().equals("getFollowed")) {
                                            method.setAccessible(true);
                                            NewsItem newsItem = new NewsItem();
                                            newsItem.setName(newDataItem.getName().replace("get", ""));
                                            newsItem.setChangedAttributeName(method.getName().toUpperCase());
                                            newsItem.setNewValue(value != null ? value.toString() : "");
                                            TransientDataFetcher.getNews().add(newsItem);
                                        }
                                    }
                                }
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        };
                    }
                });
                Intent intent = new Intent(getActivity() , DetailsActivity.class);
                intent.putExtra("TransientItem" , selectedItem);
                startActivity(intent);
            }
        });
    }

    public MainControllerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        transientItemListView = (ListView) rootView.findViewById(R.id.transient_item_list);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transientData = TransientDataFetcher.getData();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(transientItemListView != null) {
                                transientAdapter = new TransientAdapter(getActivity() , transientData);
                                Toast.makeText(getActivity() , Integer.toString(transientData.size()) , Toast.LENGTH_LONG).show();
                                transientItemListView.setAdapter(transientAdapter);
                            }
                        }
                    });
                    System.out.println("Finished loading " + transientItemListView == null);

                } catch (Exception e) {
                    System.out.println("Exception " + e.toString());
                    //e.printStackTrace();
                }
            }
        }).start();

        onClickListnerInit();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}