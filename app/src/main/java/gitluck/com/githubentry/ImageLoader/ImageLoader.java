package gitluck.com.githubentry.ImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import gitluck.com.githubentry.R;

/**
 * Created by Administrator on 3/5/2016.
 */
public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;
    // 创建LRU Cache
    private LruCache<String, Bitmap> mCaches;

    private String[] URLS;

    // 加载范围内的全部ImageView
    private ListView mListView;
    private Set<NewsAsyncTask> mTask;

    public ImageLoader(ListView listView, String[] urls) {
        mListView = listView;
        mTask = new HashSet<>();
        URLS = urls;
        Log.e("URL-Lenght", String.valueOf(URLS.length));
        // 获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // 设置缓存
        int cacheSize = maxMemory / 4;
        // 设置LRU的范围。
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 在每次存入缓存的时候调用
                return value.getByteCount();
            }
        };
    }
    // 将内容保存至LRU Cache
    public void addBitMapToCache(String url, Bitmap bitmap) {
        if (getBitMapFromCache(url) == null) {
            mCaches.put(url, bitmap);
        }
    }
    // 从LRU Cache中获取所需要的内容
    public Bitmap getBitMapFromCache(String url) {
        return mCaches.get(url);
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 在获取图片时，进行判断。查看该加载的图片是否是与当前ImageView绑定的URL的图片。
            // 解决由于ListView的缓存机制，导致显示不一致的问题。
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
    /**
     * 通过多线程的方式加载图片
     */
    public void showImageByThread(ImageView imageView, final String url) {

        mImageView = imageView;
        mUrl = url;

        new Thread() {

            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromURL(url);
                /**
                 * 在子线程中，将Bitmap以消息-Message的形式发送出去，让Handler-handleMessage进行处理
                 */
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }.start();
    }

    /**
     * 从url中获取Bitmap
     */
    public Bitmap getBitmapFromURL (String urlString) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            //return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {// 可能错误： 之前是 IOException e
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 通过异步加载的方式加载图片
     */
    public void showImageByAsyncTask (ImageView imageView, String url) {
        // 使用LRU Cache，提高加载速度
        // 从缓存中取出图片，如果没有，通过网络获取，并添加到LRU Cache中。如果图片存在，则直接使用。
        Bitmap bitmap = getBitMapFromCache(url);// 错误，原来是getBitMapFromUrl(url);
        if (bitmap == null) {
            // 给ListView的每一个Item都接受一张图片
            //new NewsAsyncTask(imageView, url).execute(url);
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            // 当前还在主线程，可以直接操作imageView
            imageView.setImageBitmap(bitmap);
        }

    }

    /**
     * 加载指定范围的所有图片
     * @param start
     * @param end
     */
    public void loadImages(int start, int end) {
        for (int i = start; i < end; i++) {
            String url = URLS[i];

            Bitmap bitmap = getBitMapFromCache(url);
            if (bitmap == null) {
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                // 将该task添加至任务栈，等待后续的处理。
                mTask.add(task);
            } else {
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void cancelAllTasks() {
        if (mTask != null) {
            for (NewsAsyncTask task : mTask) {
                task.cancel(false);
            }
        }
    }


    private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

        //private ImageView mImageView;
        private String mUrl;

        public NewsAsyncTask (String url) {
            //mImageView = imageView;
            mUrl = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            // 通过网络请求，获取图片。
            Bitmap bitmap = getBitmapFromURL(params[0]);
            if (bitmap != null) {
                // 将获取到的图片，添加到LRU Cache中。
                addBitMapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTask.remove(this);
        }
    }

}
