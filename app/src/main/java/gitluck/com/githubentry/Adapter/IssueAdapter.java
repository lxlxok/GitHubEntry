package gitluck.com.githubentry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gitluck.com.githubentry.Bean.ItemIssue;
import gitluck.com.githubentry.Bean.ItemRepos;
import gitluck.com.githubentry.R;

/**
 * Created by xiao on 3/9/16.
 */
public class IssueAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<ItemIssue> mDataList;

    public IssueAdapter(Context context, List<ItemIssue> mDataList) {
        mLayoutInflater =  LayoutInflater.from(context);
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_issue_list, null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.id_issue_iv);
            viewHolder.title = (TextView) convertView.findViewById(R.id.id_issue_title);
            viewHolder.author = (TextView) convertView.findViewById(R.id.id_issue_author);
            viewHolder.time = (TextView) convertView.findViewById(R.id.id_issue_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // get the Bean Object
        ItemIssue itemRepos = mDataList.get(position);

        // set the data
        viewHolder.img.setImageResource(itemRepos.itemImageResid);
        viewHolder.title.setText(itemRepos.itemTitle);
        viewHolder.author.setText(itemRepos.itemUser);
        viewHolder.time.setText(itemRepos.itemUpdatedTime);
        return convertView;
    }

    class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView author;
        public TextView time;

    }
}
