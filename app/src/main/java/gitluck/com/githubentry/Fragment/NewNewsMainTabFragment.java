package gitluck.com.githubentry.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import gitluck.com.githubentry.Activity.MemberActivity;
import gitluck.com.githubentry.Adapter.AboutAdapter;
import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.R;
import gitluck.com.githubentry.UserActivity;

/**
 * Created by Administrator on 2/27/2016.
 */
public class NewNewsMainTabFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG","显示聊天");
        View view =  inflater.inflate(R.layout.tab01, container, false);
        ListView lv = (ListView) view.findViewById(R.id.id_lv_chat1);

        MemberActivity.newlistAbout.add(new ItemAbout("", "", "", "", "", 0, 0, 0));
        MemberActivity.newaboutAdapter = new AboutAdapter(this.getContext(), MemberActivity.newlistAbout, lv);
        lv.setAdapter(MemberActivity.newaboutAdapter);


        /*
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", String.valueOf(position));
                //Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MemberActivity.class);

                startActivity(intent);
            }
        });
        */
        return view;
    }


}
