package gitluck.com.githubentry.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import gitluck.com.githubentry.Activity.MemberActivity;
import gitluck.com.githubentry.Adapter.AboutAdapter;
import gitluck.com.githubentry.Adapter.FollowerAdapter;
import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.Bean.ItemFollowers;
import gitluck.com.githubentry.R;
import gitluck.com.githubentry.UserActivity;

/**
 * Created by Administrator on 2/27/2016.
 */
public class FollowerMainTabFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG","显示好友");
        View view =  inflater.inflate(R.layout.tab03, container, false);
        ListView lv = (ListView) view.findViewById(R.id.id_lv_chat3);
        UserActivity.listFollowers.add(new ItemFollowers("https://avatars.githubusercontent.com/u/23469?v=3", "loading"));
        UserActivity.followerAdapter = new FollowerAdapter(this.getContext(), UserActivity.listFollowers, lv);
       lv.setAdapter(UserActivity.followerAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", String.valueOf(position));
                //Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MemberActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }



}
