package gitluck.com.githubentry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gitluck.com.githubentry.Bean.ItemMenu;
import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 3/3/2016.
 */
public class MenuAdapter extends BaseAdapter {

    private List<ItemMenu> menuList;
    private LayoutInflater layoutInflater;

    public MenuAdapter(Context context, List<ItemMenu> menuList) {
        this.menuList = menuList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_menu, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.id_imageView);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.id_textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemMenu itemMenu = menuList.get(position);
        viewHolder.imageView.setImageResource(itemMenu.itemImageResid);
        viewHolder.textView.setText(itemMenu.itemTitle);



        // 对TextView添加监听
        ((TextView)convertView.findViewById(R.id.id_textView)).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // 点击的效果，就是实现用户自定义的onMenuClick方法。
                onMenuClickLisetener.onMenuClick(position);
            }
        });




        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    /**
     * 设置监听事件
     * 通过接口回调技术实现
     */
    public interface OnMenuClickLisetener {
        //public void onMenuClick(int position, TextView textView);
        public void onMenuClick(int position);
    }

    private OnMenuClickLisetener onMenuClickLisetener;

    public void setOnMenuClickLisetener(OnMenuClickLisetener onMenuClickLisetener) {
        this.onMenuClickLisetener = onMenuClickLisetener;
    }
}
