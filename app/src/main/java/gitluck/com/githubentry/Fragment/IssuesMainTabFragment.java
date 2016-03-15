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
import gitluck.com.githubentry.Activity.RepositoryActivity;
import gitluck.com.githubentry.Adapter.AboutAdapter;
import gitluck.com.githubentry.Adapter.IssueAdapter;
import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.R;
import gitluck.com.githubentry.response.Repository;

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



        RepositoryActivity.issueAdapter = new IssueAdapter(this.getContext(), RepositoryActivity.listIssue);
        lv.setAdapter(RepositoryActivity.issueAdapter);

        /*

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TAG", String.valueOf(position));
                //Toast.makeText(getContext(), position, Toast.LENGTH_SHORT).show();
                // Use textView.text() as the information to the intent.
                TextView textView = (TextView) view.findViewById(R.id.id_repos_title);
                Log.e("TAGTAG", (String) textView.getText());
                Intent intent = new Intent(getActivity(), RepositoryActivity.class);
                intent.putExtra("name", (String) textView.getText());
                startActivity(intent);

        */


        return view;
    }


}
