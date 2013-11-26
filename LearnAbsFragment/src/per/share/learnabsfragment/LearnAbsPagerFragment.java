package per.share.learnabsfragment;

import per.share.learnabsfragment.util.LogUtil;
import per.share.learnabsfragment.util.Utils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class LearnAbsPagerFragment extends SherlockFragmentActivity {
    private static final int PAGE_NUM = 8;
    private static final String KEY_IMAGE_POS = "key_image_pos";
    MyPagerAdapter mAdapter;
    ViewPager mViewPager;
    static int[] imgs = Utils.imgs;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                finish();
            }break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_pager_fragment);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ViewPager");

        mViewPager = (ViewPager)findViewById(R.id.view_pager);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), PAGE_NUM);
        mViewPager.setAdapter(mAdapter);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private int pageCount = 0;

        public MyPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            pageCount = count;
        }

        @Override
        public int getCount() {
            return pageCount;
        }

        @Override
        public Fragment getItem(int position) {
            return ImageFragment.getInstance(position);
        }

    }

    public static class ImageFragment extends SherlockFragment {
        int num;

        public static ImageFragment getInstance(int pos) {
            ImageFragment imgf = new ImageFragment();
            Bundle b = new Bundle();
            b.putInt(KEY_IMAGE_POS, pos);
            imgf.setArguments(b);

            return imgf;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View contentView = inflater.inflate(R.layout.layout_image_pager,
                    container, false);
            ImageView iv = (ImageView)contentView.findViewById(R.id.picture_iv);
            iv.setImageResource(imgs[getImagePosition()]);
            LogUtil.Log("onCreateView(), image position = " + getImagePosition());

            return contentView;
        }

        public int getImagePosition() {
            return getArguments().getInt(KEY_IMAGE_POS);
        }
    }
}
