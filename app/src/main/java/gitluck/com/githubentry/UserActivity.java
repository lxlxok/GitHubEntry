package gitluck.com.githubentry;


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

import gitluck.com.githubentry.Activity.MemberActivity;
import gitluck.com.githubentry.Adapter.FollowerAdapter;
import gitluck.com.githubentry.Adapter.FollowingAdapter;
import gitluck.com.githubentry.Adapter.MenuAdapter;
import gitluck.com.githubentry.Adapter.ReposAdapter;
import gitluck.com.githubentry.Bean.ItemFollowers;
import gitluck.com.githubentry.Bean.ItemFollowing;
import gitluck.com.githubentry.Bean.ItemMenu;
import gitluck.com.githubentry.Bean.ItemRepos;
import gitluck.com.githubentry.Fragment.FollowingMainTabFragment;
import gitluck.com.githubentry.Fragment.NewsMainTabFragment;
import gitluck.com.githubentry.Fragment.RepoMainTabFragment;
import gitluck.com.githubentry.Fragment.FollowerMainTabFragment;
import gitluck.com.githubentry.Interface.GitHubClientUsers;
import gitluck.com.githubentry.response.Repository;
import gitluck.com.githubentry.response.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class UserActivity extends FragmentActivity {
    public String token;
    public static final String TAG = "TAGTAG UserActivity";
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

    // the user related variable

    public String username;
    public String Realname;
    public String email;
    public String location;
    public String company;

    public int followerNUm;
    public int followingNum;
    public int reposNum;

    public static List<ItemRepos> listRepos = new ArrayList<ItemRepos>();
    public static ReposAdapter reposAdapter;

    public static List<ItemFollowing> listFollowings = new ArrayList<ItemFollowing>();
    public static FollowingAdapter followingAdapter;

    public static List<ItemFollowers> listFollowers = new ArrayList<ItemFollowers>();
    public static FollowerAdapter followerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(TAG,"create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        token = settings.getString("token", "");
        Log.i(TAG, "user token =" + token);
        aboutUser();

        context = this;
        initTabline();
        initLeftMenu();
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

    private void initLeftMenu() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (ListView) findViewById(R.id.left_drawer);
        menuList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            menuList.add(new ItemMenu(
                    R.mipmap.ic_launcher,
                    "Title" + i
            ));
        }
        MenuAdapter menuAdapter = new MenuAdapter(this, menuList);

        menuAdapter.setOnMenuClickLisetener(new MenuAdapter.OnMenuClickLisetener() {
            @Override
            public void onMenuClick(int position) {
                if (position != 3) {
                    Log.e("TAG", position + "sdfsfsfsdfsfsdfsdfsf");
                    Toast.makeText(getApplicationContext(), "Item Menu" + position, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(UserActivity.this, MemberActivity.class);
                    startActivity(intent);
                }
            }
        });

        leftDrawer.setAdapter(menuAdapter);
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
        NewsMainTabFragment tab01 = new NewsMainTabFragment();
        RepoMainTabFragment tab02 = new RepoMainTabFragment();
        FollowerMainTabFragment tab03 = new FollowerMainTabFragment();
        FollowingMainTabFragment tab04 = new FollowingMainTabFragment();

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



    public void getRepos() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call <List<Repository>> call = userService.userRepos("token " + token, username, "1");
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Response<List<Repository>> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "response success code is" + response.code());

                    // clean the listRepos
                    listRepos.clear();

                    for (int i = 0; i < response.body().size(); i++) {
                        Log.i(TAG, "login = " + response.body().get(i).getName());
                        Log.i(TAG, "login = " + response.body().get(i).getDescription());
                        Log.i(TAG, "login = " + response.body().get(i).getPrivate()); //boolean
                        Log.i(TAG, "login = " + response.body().get(i).getFork()); //boolean
                        Log.i(TAG, "login = " + response.body().get(i).getForksCount()); //int
                        Log.i(TAG, "login = " + response.body().get(i).getWatchersCount()); //int
                        Log.i(TAG, "login = " + response.body().get(i).getStargazersCount()); //int
                        Log.i(TAG, "login = " + response.body().get(i).getLanguage());

                        listRepos.add(new ItemRepos(R.mipmap.ic_launcher,response.body().get(i).getName(),response.body().get(i).getDescription()));
                        reposAdapter.notifyDataSetChanged();

                    }
                } else {
                    Log.i(TAG, "response failed");
                    Log.i(TAG, "response failed code is" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }



    public void getFollower() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call <List<User>> call = userService.follower("token " + token, username, "1");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response) {
                if (response.isSuccess()) {
                    Log.i(TAG, "response success code is" + response.code());
                    listFollowers.clear();
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.i(TAG, "login = " + response.body().get(i).getLogin());

                        Log.i(TAG, "testURLof getfollower" + response.body().get(i).getAvatarUrl());
                        listFollowers.add(new ItemFollowers(response.body().get(i).getAvatarUrl(), response.body().get(i).getLogin()));
                        followerAdapter.notifyDataSetChanged();

                    }
                } else {
                    Log.i(TAG, "response failed");
                    Log.i(TAG, "response failed code is" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public void getFollowing() {
        GitHubClientUsers userService = ServiceGenerator.createService(GitHubClientUsers.class);
        Call <List<User>> call = userService.follwering("token " + token, username, "1");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response) {
                if (response.isSuccess()) {
                    listFollowings.clear();
                    Log.i(TAG, "response success code is" + response.code());
                    for (int i = 0; i < response.body().size(); i++) {
                        Log.i(TAG, "login = " + response.body().get(i).getLogin());

                        Log.i(TAG, "testURLof getfollowing" + response.body().get(i).getAvatarUrl());
                        listFollowings.add(new ItemFollowing(response.body().get(i).getAvatarUrl(), response.body().get(i).getLogin()));
                        followingAdapter.notifyDataSetChanged();


                    }
                } else {
                    Log.i(TAG, "response failed");
                    Log.i(TAG, "response failed code is" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }





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
