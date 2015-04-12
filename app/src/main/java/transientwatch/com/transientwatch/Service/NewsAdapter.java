package transientwatch.com.transientwatch.Service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;

import transientwatch.com.transientwatch.Model.NewsItem;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;

/**
* Created by sngv on 08/04/15.
*/
public class NewsAdapter extends ArrayAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsItem> newsItems;

    public NewsAdapter(Activity activity, List<NewsItem> newsItemList){
        super(activity, 0, newsItemList);

        newsItems = newsItemList;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.news_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.news_item_name);
        TextView attributeName = (TextView) convertView.findViewById(R.id.news_item_attribute_name);
        TextView attributeValue = (TextView) convertView.findViewById(R.id.news_item_attribute_value);

        NewsItem newsItem = newsItems.get(position);
        name.setText(newsItem.getName());
        attributeName.setText(newsItem.getChangedAttributeName());
        attributeValue.setText(newsItem.getNewValue());

        return convertView;
    }
}
