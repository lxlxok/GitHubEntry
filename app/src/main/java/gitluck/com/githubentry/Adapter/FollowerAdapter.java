package gitluck.com.githubentry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gitluck.com.githubentry.Bean.ItemFollowers;
import gitluck.com.githubentry.Bean.ItemRepos;
import gitluck.com.githubentry.R;

/**
 * Created by xiao on 3/10/16.
 */



public class FollowerAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<ItemFollowers> mDataList;

    public FollowerAdapter(Context context, List<ItemFollowers> mDataList) {
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
            convertView = mLayoutInflater.inflate(R.layout.item_list, null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.id_iv);
            viewHolder.title = (TextView) convertView.findViewById(R.id.id_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.id_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // get the Bean Object
        ItemFollowers itemFollowerss = mDataList.get(position);

        // set the data
        viewHolder.img.setImageResource(itemFollowerss.itemImageResid);
        viewHolder.title.setText(itemFollowerss.itemTitle);
        viewHolder.content.setText(itemFollowerss.itemContent);
        return convertView;
    }

    class ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView content;
    }
}
