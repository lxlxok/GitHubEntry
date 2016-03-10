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
import gitluck.com.githubentry.Adapter.MyAdapter;
import gitluck.com.githubentry.Bean.ItemBean;
import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 2/27/2016.
 */
public class FollowingMainTabFragment extends Fragment {




        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.e("TAG","显示好友");
            View view =  inflater.inflate(R.layout.tab04, container, false);
            ListView lv = (ListView) view.findViewById(R.id.id_lv_chat4);
            lv.setAdapter(new MyAdapter(this.getContext(), getDatas1()));
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

        private List<ItemBean> getDatas1() {
            List<ItemBean> mDatas = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                mDatas.add(new ItemBean(
                        R.mipmap.ic_launcher,
                        "Title" + i,
                        "Content" + i
                ));
            }
            return mDatas;
        }





}
