package transientwatch.com.transientwatch.Service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import transientwatch.com.transientwatch.Model.TransientItem;
import transientwatch.com.transientwatch.R;

/**
* Created by sngv on 08/04/15.
*/
public class TransientAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<TransientItem> transientItems;

    public TransientAdapter(Activity activity , List<TransientItem> cloudItem){
        transientItems = cloudItem;
        this.activity = activity;
    }

    public TransientAdapter(){}

    @Override
    public int getCount() {
        return transientItems.size();
    }

    @Override
    public Object getItem(int position) {
        return transientItems.get(position);
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
//        TextView
        TransientItem transientItem = transientItems.get(position);
        name.setText(transientItem.getName());
        ra.setText(transientItem.getRa());
        dec.setText(transientItem.getDec());
        op.setText(transientItem.getOp());

        return convertView;
    }
}
