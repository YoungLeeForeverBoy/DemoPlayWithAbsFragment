package per.share.learnabsfragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class LearnAbsFragment extends SherlockActivity {
    TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_abs_fragment);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Home");

        mContentTv = (TextView)findViewById(R.id.content_tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.learn_abs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                mContentTv.setText("click home button");
            }break;

            case R.id.action_dialog: {
                startActivity(new Intent(this, LearnAbsFragmentDialog.class));
            }break;

            case R.id.action_fragment_menu: {
                startActivity(new Intent(this, LearnAbsFragmentMenu.class));
            }break;

            case R.id.action_list_fragment: {
                startActivity(new Intent(this, LearnAbsListFragment.class));
            }break;

            case R.id.action_view_pager: {
                startActivity(new Intent(this, LearnAbsPagerFragment.class));
            }break;
        }

        return true;
    }

}
