package per.share.learnabsfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class LearnAbsFragmentDialog extends SherlockFragmentActivity {
    public static final String KEY_TITLE = "title";

    TextView mContentTv;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_learn_abs_fragment);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dialog");

        mContentTv = (TextView)findViewById(R.id.content_tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.learn_abs_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                mContentTv.setText("click home button");
                finish();
            }break;

            case R.id.action_alert_dialog: {
                mContentTv.setText("click alert dlg");
                showAlertDialog();
            }break;

            case R.id.action_dialog: {
                mContentTv.setText("click dialog");
                showDialog();
            }break;
        }

        return true;
    }

    private void showAlertDialog() {
        DialogFragment dlgFragment = MyAlertDialogFragment.getInstance("This is a alert dialog");
        dlgFragment.show(getSupportFragmentManager(), "AlertDialog");
    }

    private void showDialog() {
        DialogFragment dlgFragment = MyDialogFragment.getInstance("This is a dialog");
        dlgFragment.show(getSupportFragmentManager(), "Dialog");
    }

    public static class MyAlertDialogFragment extends SherlockDialogFragment {

        private MyAlertDialogFragment() {}

        public static MyAlertDialogFragment getInstance(String title) {
            MyAlertDialogFragment fragment = new MyAlertDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TITLE, title);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String title = getArguments().getString(KEY_TITLE);
            AlertDialog dialog = new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "click positive button",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getActivity(), "click negative button",
                                    Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();

            return dialog;
        }
    }

    public static class MyDialogFragment extends SherlockDialogFragment {

        private MyDialogFragment() {}

        public static MyDialogFragment getInstance(String title) {
            MyDialogFragment fragment = new MyDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString(KEY_TITLE, title);
            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Dialog_MinWidth);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            String title = getArguments().getString(KEY_TITLE);
            View contentView = inflater.inflate(R.layout.layout_dialog, container, false);
            TextView contentTv = (TextView) contentView.findViewById(R.id.dialog_content_tv);
            contentTv.setText(title);
            Button okBtn = (Button)contentView.findViewById(R.id.dialog_ok_btn);
            okBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });

            return contentView;
        }
        
    }
}
