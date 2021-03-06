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
import android.widget.TextView;

import gitluck.com.githubentry.Activity.MemberActivity;
import gitluck.com.githubentry.Adapter.FollowerAdapter;
import gitluck.com.githubentry.Bean.ItemFollowers;
import gitluck.com.githubentry.R;
import gitluck.com.githubentry.UserActivity;

/**
 * Created by Administrator on 2/27/2016.
 */
public class NewFollowerMainTabFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG","显示好友");
        View view =  inflater.inflate(R.layout.tab03, container, false);
        ListView lv = (ListView) view.findViewById(R.id.id_lv_chat3);
        MemberActivity.newlistFollowers.add(new ItemFollowers("", ""));
        MemberActivity.newfollowerAdapter = new FollowerAdapter(this.getContext(), MemberActivity.newlistFollowers, lv);
       lv.setAdapter(MemberActivity.newfollowerAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", String.valueOf(position));
                //Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();

                // Use item_list.
                TextView textView = (TextView) view.findViewById(R.id.id_title);
                String title = (String) textView.getText();
                Log.e("TAGTAG", title);
                Intent intent = new Intent(getActivity(), MemberActivity.class);
                intent.putExtra("name", title);
                startActivity(intent);
            }
        });
        return view;
    }



}
