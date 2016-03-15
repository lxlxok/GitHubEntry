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
import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 2/27/2016.
 */
public class IssuesMainTabFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG","显示聊天");
        View view =  inflater.inflate(R.layout.tab03_issues, container, false);
        ListView lv = (ListView) view.findViewById(R.id.id_lv_issues);


        lv.setAdapter(new AboutAdapter(getContext(), getDatas(), lv));
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

    private List<ItemAbout> getDatas() {
        List<ItemAbout> mDatas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDatas.add(new ItemAbout(
                    "https://avatars.githubusercontent.com/u/23469?v=3",
                    "Title" + i,
                    "Content" + i
            ));
        }
        return mDatas;
    }


}
