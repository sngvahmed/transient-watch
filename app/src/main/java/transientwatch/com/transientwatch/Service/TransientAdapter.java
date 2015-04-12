package transientwatch.com.transientwatch.Service;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

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
        Button follow = (Button) convertView.findViewById(R.id.follow);

        Transient aTransient = aTransients.get(position);
        name.setText(aTransient.getName());
        ra.setText(aTransient.getRight_ascention());
        dec.setText(aTransient.getDeclination());
        op.setText(aTransient.getOrbital_period());

        return convertView;
    }
}
