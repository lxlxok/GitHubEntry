package gitluck.com.githubentry.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 2/27/2016.
 */
public class RepoMainTabFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG", "显示联系人");
        return inflater.inflate(R.layout.tab02, container, false);
    }
}
