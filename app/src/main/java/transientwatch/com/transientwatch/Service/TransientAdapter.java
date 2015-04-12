package transientwatch.com.transientwatch.Service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import transientwatch.com.transientwatch.Controller.DetailsController.DetailsActivity;
import transientwatch.com.transientwatch.Controller.Notification.NotificationAlert;
import transientwatch.com.transientwatch.Controller.Notification.NotifyMessage;
import transientwatch.com.transientwatch.Model.NewsItem;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;

/**
* Created by sngv on 08/04/15.
*/
public class TransientAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Transient> aTransients;

    public TransientAdapter(Activity activity , List<Transient> transientList){
        aTransients = transientList;
        this.activity = activity;
    }

    public TransientAdapter(){}

    @Override
    public int getCount() {
        return aTransients.size();
    }

    @Override
    public Object getItem(int position) {
        return aTransients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.transient_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.transient_name);
        TextView ra = (TextView) convertView.findViewById(R.id.transient_ra);
        TextView dec = (TextView) convertView.findViewById(R.id.transient_dec);
        TextView op = (TextView) convertView.findViewById(R.id.transient_op);
        final Button follow = (Button) convertView.findViewById(R.id.follow);

        final int myPosition = position;

        Transient aTransient = aTransients.get(position);
        name.setText(aTransient.getName());
        ra.setText(aTransient.getRight_ascention());
        dec.setText(aTransient.getDeclination());
        op.setText(aTransient.getOrbital_period());

        if(aTransient.isFollowed()){
            follow.setText("Unfollow");
        }
        else {
            follow.setText("Follow");
        }


        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Transient selectedItem = TransientDataFetcher.getData().get(myPosition);
                System.out.println("Item Clicked");
                if (selectedItem.isFollowed()) {
                    selectedItem.setFollowed(false);
                    ((Button) v).setText("Follow");
                    String var = selectedItem.getName();
                    System.out.println("sngv :: size :: " + Integer.toString(TransientDataFetcher.getNews().size()));
                    Iterator<NewsItem> iterator = TransientDataFetcher.getNews().iterator();

                    while(iterator.hasNext()) {
                        NewsItem item = iterator.next();
                        System.out.println("sngv :: var :: " + var);
                        System.out.println("sngv :: item :: " + item.getName());
                        if(item.getName().equals(var)){
                            System.out.println("sngv :: found");
                            //TransientDataFetcher.getNews().remove(item);
                            iterator.remove();
                            System.out.println("sngv :: done remove");
                        }
                    }

                } else {
                    selectedItem.setFollowed(true);
                    ((Button) v).setText("Unfollow");
                    Transient newDataItem = selectedItem;
                    try {
                        final int NOTIFY_ME_ID=1337;
                        final NotificationManager mgr= (NotificationManager)v.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        Notification note=new Notification(R.drawable.ic_launcher,
                                selectedItem.getName() + " told you :D ",
                                System.currentTimeMillis());

                        for (Method method : newDataItem.getClass().getDeclaredMethods()) {
                            if (method.getName().startsWith("get")) {
                                Object value = method.invoke(newDataItem);
                                String valueStr = value == null ? "" : value.toString();
                                if (value != null && valueStr.length() > 0 && !valueStr.equals("NO DATA") && !method.getName().equals("getFollowed")) {
                                    method.setAccessible(true);
                                    NewsItem newsItem = new NewsItem();
                                    newsItem.setName(newDataItem.getName().replace("get", ""));
                                    newsItem.setChangedAttributeName(method.getName().toUpperCase());
                                    newsItem.setNewValue(value != null ? value.toString() : "");
                                    TransientDataFetcher.getNews().add(newsItem);
                                    // This pending intent will open after notification click
                                    PendingIntent i=PendingIntent.getActivity(v.getContext(), 0,
                                            new Intent(v.getContext(), DetailsActivity.class).putExtra("TransientItem" , newDataItem),
                                            0);
                                    note.setLatestEventInfo(v.getContext(), selectedItem.getName() + "told you :D ",
                                            newsItem.getChangedAttributeName() + "change by " + newsItem.getNewValue(), i);
                                    mgr.notify(NOTIFY_ME_ID, note);

                                }
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return convertView;
    }
}
