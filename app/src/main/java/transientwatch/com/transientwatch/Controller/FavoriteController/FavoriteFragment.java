package transientwatch.com.transientwatch.Controller.FavoriteController;
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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import transientwatch.com.transientwatch.Controller.MainController.MainControllerFragment;
import transientwatch.com.transientwatch.Controller.NavigationController.NavigationDrawerFragment;
import transientwatch.com.transientwatch.Model.Transient;
import transientwatch.com.transientwatch.R;
import transientwatch.com.transientwatch.Service.TransientAdapter;

public class FavoriteFragment extends Fragment {
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

            return rootView;
        }
    }