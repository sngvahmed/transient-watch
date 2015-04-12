package transientwatch.com.transientwatch.Controller.DetailsController;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;


public class DetailsActivity extends ActionBarActivity {
    Transient trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        trans = (Transient) getIntent().getSerializableExtra("TransientItem");
        mapDataToView();
    }

    private void mapDataToView() {

        ((TextView)findViewById(R.id.item_name)).setText(trans.getName());
        ((TextView)findViewById(R.id.ra)).setText(trans.getRight_ascention());
        ((TextView)findViewById(R.id.dec)).setText(trans.getDeclination());
        ((TextView)findViewById(R.id.orpital_period)).setText(trans.getOrbital_period());
        ((TextView)findViewById(R.id.maxi_data)).setText(trans.getMAXI_data());
        ((TextView)findViewById(R.id.maxi_change_prob)).setText("prob change " + trans.getMAXI_prob_change());
        ((TextView)findViewById(R.id.maxi_avg)).setText("average flux " + trans.getMAXI_average_flux());
        ((TextView)findViewById(R.id.swift_data)).setText(trans.getSWIFT_BAT_data());
        ((TextView)findViewById(R.id.swift_change_prob)).setText("prob change " + trans.getSWIFT_BAT_prob_change());
        ((TextView)findViewById(R.id.swift_avg)).setText("average flux " + trans.getSWIFT_BAT_average_flux());
        ((TextView)findViewById(R.id.fermi_data)).setText("average flux " + trans.getFERMI_GBM_data());
        ((TextView)findViewById(R.id.fermi_change_prob)).setText("prob change " + trans.getFERMI_GBM_prob_change());
        ((TextView)findViewById(R.id.fermi_avg)).setText("average fermi " + trans.getFERMI_GBM_average_flux());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
}
