package gitluck.com.githubentry.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import gitluck.com.githubentry.Adapter.AboutAdapter;
import gitluck.com.githubentry.Adapter.FollowerAdapter;
import gitluck.com.githubentry.Adapter.FollowingAdapter;
import gitluck.com.githubentry.Adapter.MenuAdapter;
import gitluck.com.githubentry.Adapter.ReposAdapter;
import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.Bean.ItemFollowers;
import gitluck.com.githubentry.Bean.ItemFollowing;
import gitluck.com.githubentry.Bean.ItemMenu;
import gitluck.com.githubentry.Bean.ItemRepos;
import gitluck.com.githubentry.Fragment.FollowerMainTabFragment;
import gitluck.com.githubentry.Fragment.FollowingMainTabFragment;
import gitluck.com.githubentry.Fragment.NewFollowerMainTabFragment;
import gitluck.com.githubentry.Fragment.NewFollowingMainTabFragment;
import gitluck.com.githubentry.Fragment.NewNewsMainTabFragment;
import gitluck.com.githubentry.Fragment.NewRepoMainTabFragment;
import gitluck.com.githubentry.Fragment.NewsMainTabFragment;
import gitluck.com.githubentry.Fragment.RepoMainTabFragment;
import gitluck.com.githubentry.Interface.GitHubClientUsers;
import gitluck.com.githubentry.R;
import gitluck.com.githubentry.ServiceGenerator;
import gitluck.com.githubentry.response.Repository;
import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MemberActivity extends FragmentActivity {


    private ImageView mTabline;
    private int mTabline_size;

    private ViewPager viewPager;
    private TextView newsTextView;
    private TextView repoTextView;
    private TextView followerTextView;
    private TextView followingTextView;
    private List<Fragment> data;
    //Google建议，Fragement同ViewPager使用时，使用FragmentPagerAdapter适配器。
    private FragmentPagerAdapter fragmentPagerAdapter;

    private int currentPageIndex;

    private DrawerLayout drawerLayout;
    private List<ItemMenu> menuList;
    private ListView leftDrawer;
    private Context context;
    public String userName;
    public String token;

    public static List<ItemAbout> newlistAbout = new ArrayList<>();
    public static AboutAdapter newaboutAdapter;

    public static List<ItemRepos> newlistRepos = new ArrayList<ItemRepos>();
    public static ReposAdapter newreposAdapter;

    public static List<ItemFollowing> newlistFollowings = new ArrayList<ItemFollowing>();
    public static FollowingAdapter newfollowingAdapter;

    public static List<ItemFollowers> newlistFollowers = new ArrayList<ItemFollowers>();
    public static FollowerAdapter newfollowerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TAG", "create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        userName = getIntent().getStringExtra("name");


        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        token = settings.getString("token", "");
        Log.i("TAGTAGTAG", "user token =" + token);
        getUser();

        context = this;
        initTabline();
        initView();
    }


    private void initTabline() {
        mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        mTabline_size = displayMetrics.widthPixels / 4;
        ViewGroup.LayoutParams layoutParams = mTabline.getLayoutParams();
        layoutParams.width = mTabline_size;
        mTabline.setLayoutParams(layoutParams);
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.id_viewpager);
        newsTextView = (TextView) findViewById(R.id.id_tv_news);
        repoTextView = (TextView) findViewById(R.id.id_tv_repository);
        followerTextView = (TextView) findViewById(R.id.id_tv_follower);
        followingTextView = (TextView) findViewById(R.id.id_tv_following);


        /**
         * List<Fragment>
         * 利用之前定义的三个Fragment，更新数据源。
         */
        data = new ArrayList<Fragment>();
        NewNewsMainTabFragment tab01 = new NewNewsMainTabFragment();
        NewRepoMainTabFragment tab02 = new NewRepoMainTabFragment();
        NewFollowerMainTabFragment tab03 = new NewFollowerMainTabFragment();
        NewFollowingMainTabFragment tab04 = new NewFollowingMainTabFragment();

        data.add(tab01);
        data.add(tab02);
        data.add(tab03);
        data.add(tab04);

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
                        newsTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        repoTextView.setTextColor(Color.parseColor("#008000"));
                        getRepos();
                        break;
                    case 2:
                        followerTextView.setTextColor(Color.parseColor("#008000"));
                        getFollower();
                        break;
                    case 3:
                        followingTextView.setTextColor(Color.parseColor("#008000"));
                        getFollowing();
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
        newsTextView.setTextColor(Color.BLACK);
        repoTextView.setTextColor(Color.BLACK);
        followerTextView.setTextColor(Color.BLACK);
        followingTextView.setTextColor(Color.BLACK);
    }


    public void getUser() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call<User> call = userService.getUser("token " + token, userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response) {
                if (response.isSuccess()) {

                    newlistAbout.clear();
                    newlistAbout.add(new ItemAbout(response.body().getAvatarUrl(), response.body().getLogin(), response.body().getEmail(), response.body().getLocation(), response.body().getCompany(), response.body().getFollowers(), response.body().getFollowing(), response.body().getPublicRepos()));
                    newaboutAdapter.notifyDataSetChanged();

                    Log.i("TAGTAGTAG", "response code is" + response.code());

                } else {

                    Log.i("TAGTAGTAG", "response failed");


                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    public void getRepos() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call <List<Repository>> call = userService.userRepos("token " + token, userName, "1");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Response<List<Repository>> response) {
                if (response.isSuccess()) {
                    Log.i("TAG", "response success code is" + response.code());

                    // clean the listRepos
                    newlistRepos.clear();

                    for (int i = 0; i < response.body().size(); i++) {
                        int resource = R.drawable.book_48;
                        //  Log.i(TAG, "login = " + response.body().get(i).getName());
                        //   Log.i(TAG, "login = " + response.body().get(i).getDescription());
                        //   Log.i(TAG, "login = " + response.body().get(i).getForksCount()); //int

                        if (response.body().get(i).getFork()) {
                            resource = R.drawable.fork_64;
                        }
                        if (response.body().get(i).getPrivate()) {
                            resource = R.drawable.lock_48;
                        }
                        //  Log.i(TAG, "resource = " + resource);


                        newlistRepos.add(new ItemRepos(resource, response.body().get(i).getFullName(), response.body().get(i).getLanguage(), response.body().get(i).getStargazersCount(), response.body().get(i).getWatchersCount()));
                        newreposAdapter.notifyDataSetChanged();

                    }
                } else {
                    Log.i("TAG", "response failed");
                    Log.i("TAG", "response failed code is" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }



    public void getFollower() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call <List<User>> call = userService.follower("token " + token, userName, "1");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response) {
                if (response.isSuccess()) {
                    Log.i("TAG", "response success code is" + response.code());
                    newlistFollowers.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        //Log.i(TAG, "login = " + response.body().get(i).getLogin());

                        // Log.i(TAG, "testURLof getfollower" + response.body().get(i).getAvatarUrl());
                        newlistFollowers.add(new ItemFollowers(response.body().get(i).getAvatarUrl(), response.body().get(i).getLogin()));
                        newfollowerAdapter.notifyDataSetChanged();

                    }
                } else {
                    Log.i("TAG", "response failed");
                    Log.i("TAG", "response failed code is" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void getFollowing() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call <List<User>> call = userService.follwering("token " + token, userName, "1");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response) {
                if (response.isSuccess()) {
                    newlistFollowings.clear();

                    for (int i = 0; i < response.body().size(); i++) {

                        newlistFollowings.add(new ItemFollowing(response.body().get(i).getAvatarUrl(), response.body().get(i).getLogin()));
                        newfollowingAdapter.notifyDataSetChanged();


                    }
                } else {
                    Log.i("TAG", "response failed");
                    Log.i("TAG", "response failed code is" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }


}
