package transientwatch.com.transientwatch.Controller.CategoryController;

import android.support.v4.app.FragmentManager;
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

import transientwatch.com.transientwatch.Controller.NavigationController.NavigationDrawerFragment;
import transientwatch.com.transientwatch.R;

public class CategoryFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "Category";

        public CategoryFragment() {
        }

        public static CategoryFragment newInstance(int sectionNumber) {
            CategoryFragment fragment = new CategoryFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_category, container, false);
            return rootView;
        }
    }