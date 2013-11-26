package per.share.learnabsfragment;

import per.share.learnabsfragment.util.LogUtil;
import per.share.learnabsfragment.util.Utils;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuItem;

public class LearnAbsListFragment extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_list_fragment);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("List fragment");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                finish();
            }break;
        }

        return true;
    }

    public static class DetailActivity extends SherlockFragmentActivity {

        @Override
        protected void onCreate(Bundle arg0) {
            super.onCreate(arg0);
            setContentView(R.layout.activity_learn_abs_fragment);

            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("DetailActivity");

            if(getResources().getConfiguration().orientation
                    == Configuration.ORIENTATION_LANDSCAPE) {
                finish();
                return;
            }

            if(arg0 == null) {
                DetailFragment df = new DetailFragment();
                df.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(
                        android.R.id.content, df).commit();
            }
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch(item.getItemId()) {
                case android.R.id.home: {
                    finish();
                }break;
            }

            return true;
        }

    }

    public static class DetailFragment extends SherlockFragment {

        public static DetailFragment getInstance(int pos) {
            DetailFragment ft = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TitlesFragment.KEY_CUR_SELECT_POS, pos);
            ft.setArguments(bundle);

            return ft;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            if(container == null)
                return null;

            ScrollView sv = new ScrollView(getActivity());
            sv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            sv.setAlwaysDrawnWithCacheEnabled(true);
            TextView tv = new TextView(getActivity());
            tv.setTextAppearance(getActivity(), android.R.attr.textAppearanceLarge);
            tv.setPadding(5, 5, 5, 5);
            sv.addView(tv);
            tv.setText(Utils.arrays[getShowPosition()]);

            return sv;
        }

        public int getShowPosition() {
            return getArguments().getInt(TitlesFragment.KEY_CUR_SELECT_POS);
        }
    }

    public static class TitlesFragment extends SherlockListFragment {
        public static final String KEY_CUR_SELECT_POS = "key_cur_select_pos";
        private boolean isDualPanel = false;
        private int curSelectPos = 0;

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1, Utils.arrays));

            View detailFragment = getActivity().findViewById(R.id.detail_ft);
            isDualPanel = detailFragment != null &&
                    detailFragment.getVisibility() == View.VISIBLE;

            if(savedInstanceState != null) {
                curSelectPos = savedInstanceState.getInt(KEY_CUR_SELECT_POS, 0);
            }

            if(isDualPanel) {
                getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

                showDetail(curSelectPos);
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt(KEY_CUR_SELECT_POS, curSelectPos);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            showDetail(position);
        }

        private void showDetail(int position) {
            LogUtil.Log("showDetail(), position = " + position
                    + ", isDualPanel = " + isDualPanel);
            curSelectPos = position;
            if(isDualPanel) {
                getListView().setItemChecked(position, true);

                DetailFragment detailFragment =
                        (DetailFragment)getFragmentManager().findFragmentById(
                                R.id.detail_ft);
                if(detailFragment == null || detailFragment.getShowPosition() != position) {
                    detailFragment = DetailFragment.getInstance(position);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.detail_ft, detailFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            } else {
                Intent i = new Intent();
                i.setClass(getActivity(), DetailActivity.class);
                i.putExtra(KEY_CUR_SELECT_POS, position);
                startActivity(i);
            }
        }
    }
}
