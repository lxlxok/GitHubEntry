package gitluck.com.githubentry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import gitluck.com.githubentry.Bean.ItemAbout;
import gitluck.com.githubentry.ImageLoader.ImageLoader;
import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 2/28/2016.
 */
public class AboutAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private LayoutInflater mLayoutInflater;
    private List<ItemAbout> mDataList;
    private ImageLoader mImageLoader;
    // 判断是否是第一次启动
    private boolean mFirstLoad;

    private int mStart, mEnd;
    private String[] URLS;

    public AboutAdapter(Context context, List<ItemAbout> mDataList, ListView listView) {
        mLayoutInflater =  LayoutInflater.from(context);
        this.mDataList = mDataList;
        URLS = new String[mDataList.size()];
        for (int i = 0; i < mDataList.size(); i++) {
            URLS[i] = mDataList.get(i).imgurl;
        }
        mFirstLoad = true;
        mImageLoader = new ImageLoader(listView, URLS);
        // 注册监听事件
        listView.setOnScrollListener(this);
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
            convertView = mLayoutInflater.inflate(R.layout.about_info_list, null);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.id_about_iv);
            viewHolder.aboutName = (TextView) convertView.findViewById(R.id.id_about_name);
            viewHolder.email = (TextView) convertView.findViewById(R.id.id_about_email);
            viewHolder.company = (TextView) convertView.findViewById(R.id.id_about_company);
            viewHolder.location = (TextView) convertView.findViewById(R.id.id_about_location);
            viewHolder.reposNum = (TextView) convertView.findViewById(R.id.id_repos_num);
            viewHolder.followerNum = (TextView) convertView.findViewById(R.id.id_follower_num);
            viewHolder.followingNum = (TextView) convertView.findViewById(R.id.id_following_num);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // get the Bean Object
        ItemAbout itemBean = mDataList.get(position);

        String url = itemBean.imgurl;
        viewHolder.img.setTag(url);
        // set the data
        //viewHolder.img.setImageResource(itemBean.itemImageResid);

        // 使用AsyncTask的方式，加载对应的图片
        mImageLoader.showImageByAsyncTask(viewHolder.img, url);
        viewHolder.aboutName.setText(itemBean.username);
        viewHolder.email.setText(itemBean.email);
        viewHolder.company.setText(itemBean.company);
        viewHolder.location.setText(itemBean.location);
        viewHolder.reposNum.setText(itemBean.reposNum);
        viewHolder.followerNum.setText(itemBean.followerNum);
        viewHolder.followingNum.setText(itemBean.followingNum);
        return convertView;
    }

    class ViewHolder {
        public ImageView img;
        public TextView aboutName;
        public TextView email;
        public TextView company;
        public TextView location;
        public TextView reposNum;
        public TextView followerNum;
        public TextView followingNum;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            // 加载可见项
            mImageLoader.loadImages(mStart, mEnd);
        } else {
            // 停止加载所有的下载任务
            mImageLoader.cancelAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        // 第一次加载时使用
        if (mFirstLoad && visibleItemCount > 0) {
            mImageLoader.loadImages(mStart, mEnd);
            mFirstLoad = false;
        }

    }

}
