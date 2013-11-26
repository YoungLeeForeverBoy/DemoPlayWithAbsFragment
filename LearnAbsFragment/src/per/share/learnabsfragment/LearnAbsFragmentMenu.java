package per.share.learnabsfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class LearnAbsFragmentMenu extends SherlockFragmentActivity {
    TextView mContentTv;
    Fragment mMenuFragment;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_learn_abs_fragment);

        getSupportActionBar().setTitle("Fragment menu");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContentTv = (TextView)findViewById(R.id.content_tv);

        mMenuFragment = getSupportFragmentManager().findFragmentByTag("menu fragment");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(mMenuFragment == null) {
            mMenuFragment = new MenuFragment();
            ft.add(mMenuFragment, "menu fragment");
        }
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("menu a")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if("menu a".equals(item.getTitle())) {
            mContentTv.setText("click menu item : " + item.getTitle());
            return true;
        }

        switch(item.getItemId()) {
            case android.R.id.home: {
                finish();
            }break;
        }

        return false;
    }

    public class MenuFragment extends SherlockFragment {

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            menu.add(0, 1, 100, "menu b1")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
            menu.add(0, 2, 100, "menu b2")
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            super.onCreateOptionsMenu(menu, inflater);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch(item.getItemId()) {
                case 1: {
                    mContentTv.setText("click fragment menu item b1 ");
                    return true;
                }

                case 2: {
                    mContentTv.setText("click fragment menu item b2");
                    return true;
                }
            }

            return false;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }
        
    }
}
