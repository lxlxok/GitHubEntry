package gitluck.com.githubentry;



import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import org.w3c.dom.Text;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ImageView mTabline;
    private int mTabline_size;

    private ViewPager viewPager;
    private TextView chatTextView;
    private TextView friendTextView;
    private TextView contactTextView;
    private List<Fragment> data;
    //Google建议，Fragement同ViewPager使用时，使用FragmentPagerAdapter适配器。
    private FragmentPagerAdapter fragmentPagerAdapter;

    private int currentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabline();
        initView();
        Log.e("TAG", "End of onCreate");
    }

    private void initTabline() {
        mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        mTabline_size = displayMetrics.widthPixels / 3;
        LayoutParams layoutParams = mTabline.getLayoutParams();
        layoutParams.width = mTabline_size;
        mTabline.setLayoutParams(layoutParams);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        chatTextView = (TextView) findViewById(R.id.id_tv_chat);
        friendTextView = (TextView) findViewById(R.id.id_tv_friend);
        contactTextView = (TextView) findViewById(R.id.id_tv_contact);

        /**
         * List<Fragment>
         * 利用之前定义的三个Fragment，更新数据源。
         */
        data = new ArrayList<Fragment>();
        ChatMainTabFragment tab01 = new ChatMainTabFragment();
        FriendMainTabFragment tab02 = new FriendMainTabFragment();
        ContactMainTabFragment tab03 = new ContactMainTabFragment();
        data.add(tab01);
        data.add(tab02);
        data.add(tab03);

        /**
         * 创建Adapter：
         * 这里使用FragmentPagerAdapter.
         * 通过重写getItem getCount方法，将该适配器同我们的数据源 data=List<Framgent> 建立了联系。
         */
        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return data.get(position);
            }

            @Override
            public int getCount() {
                return data.size();
            }
        };

        /**
         * 将适配器，添加进viewPager中。 viewPager可以看作是view的容器。
         * 通过该适配器，将viewPager（view）同数据源建立了联系。
         */
        viewPager.setAdapter(fragmentPagerAdapter);

        /**
         * 设置viewPager的监听，
         * 实现tabline的同步效果。
         */
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.e("TAG", position + ", " + positionOffset + ", " + positionOffsetPixels);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTabline.getLayoutParams();
                if (currentPageIndex == position) { // from left to right
                    layoutParams.leftMargin = (int)(currentPageIndex * mTabline_size + positionOffset * mTabline_size);
                } else if (currentPageIndex == position + 1) { // currentPageIndex > position means from right to left
                    layoutParams.leftMargin = (int)(currentPageIndex * mTabline_size + (positionOffset - 1) * mTabline_size);
                }
                mTabline.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        chatTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        friendTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        contactTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                }
                currentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected void resetTextView() {
        chatTextView.setTextColor(Color.BLACK);
        friendTextView.setTextColor(Color.BLACK);
        contactTextView.setTextColor(Color.BLACK);
    }

}
