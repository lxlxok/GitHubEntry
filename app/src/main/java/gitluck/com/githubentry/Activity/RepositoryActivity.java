package gitluck.com.githubentry.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gitluck.com.githubentry.Adapter.MenuAdapter;
import gitluck.com.githubentry.Bean.ItemMenu;
import gitluck.com.githubentry.Fragment.CodeMainTabFragment;
import gitluck.com.githubentry.Fragment.FollowerMainTabFragment;
import gitluck.com.githubentry.Fragment.FollowingMainTabFragment;
import gitluck.com.githubentry.Fragment.IssuesMainTabFragment;
import gitluck.com.githubentry.Fragment.NewsMainTabFragment;
import gitluck.com.githubentry.Fragment.RepoMainTabFragment;
import gitluck.com.githubentry.R;


public class RepositoryActivity extends FragmentActivity {


    private ImageView mTabline;
    private int mTabline_size;

    private ViewPager viewPager;
    private TextView codeTextView;
    private TextView commitsTextView;
    private TextView issuesTextView;
    private List<Fragment> data;
    //Google建议，Fragement同ViewPager使用时，使用FragmentPagerAdapter适配器。
    private FragmentPagerAdapter fragmentPagerAdapter;

    private int currentPageIndex;

    private DrawerLayout drawerLayout;
    private List<ItemMenu> menuList;
    private ListView leftDrawer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TAG", "create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        Intent intent = getIntent();
        String RepositoryName = intent.getStringExtra("name");
        Log.e("TAGTAG", RepositoryName + RepositoryName);

        TextView tvTitle = (TextView) findViewById(R.id.id_top1_title);
        tvTitle.setText(RepositoryName);



        context = this;
        initTabline();
        initView();
    }


    private void initTabline() {
        mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        mTabline_size = displayMetrics.widthPixels / 3;
        ViewGroup.LayoutParams layoutParams = mTabline.getLayoutParams();
        layoutParams.width = mTabline_size;
        mTabline.setLayoutParams(layoutParams);
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        codeTextView = (TextView) findViewById(R.id.id_tv_code);
        commitsTextView = (TextView) findViewById(R.id.id_tv_commits);
        issuesTextView = (TextView) findViewById(R.id.id_tv_issues);


        /**
         * List<Fragment>
         * 利用之前定义的三个Fragment，更新数据源。
         */
        data = new ArrayList<Fragment>();
        CodeMainTabFragment tab01 = new CodeMainTabFragment();
        CodeMainTabFragment tab02 = new CodeMainTabFragment();
        IssuesMainTabFragment tab03 = new IssuesMainTabFragment();

        data.add(tab01);
        data.add(tab02);
        data.add(tab03);
        //data.add(tab04);

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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                        codeTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        commitsTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        issuesTextView.setTextColor(Color.parseColor("#008000"));
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
        codeTextView.setTextColor(Color.BLACK);
        commitsTextView.setTextColor(Color.BLACK);
        issuesTextView.setTextColor(Color.BLACK);
    }



/*

    public void aboutUser() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call<User> call = userService.authrizedUser("token "+token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    username = response.body().getLogin();
                    Realname = response.body().getName();
                    email = response.body().getEmail();
                    location = response.body().getLocation();
                    company = response.body().getCompany();
                    followerNUm = response.body().getFollowers();
                    followingNum = response.body().getFollowing();
                    reposNum = response.body().getPublicRepos();

                    Log.i(TAG, "response code is" + response.code());
                    Log.i(TAG, "response username is" + response.body().getLogin());

                } else {

                    Log.i(TAG, "response failed");
                    Log.i(TAG, "response code is" + response.code());

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }







    public void getUser() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call<User> call = userService.getUser("lxlxok");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "Body is" + response.body().getName());
                } else {

                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }



*/


    /*
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"resume" + token);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"destroy");

    }

    */

}