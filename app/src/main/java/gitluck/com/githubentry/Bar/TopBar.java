package gitluck.com.githubentry.Bar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gitluck.com.githubentry.R;

public class TopBar extends RelativeLayout {
    private Button leftButton,rightButton;
    private TextView tvTitle;
    //定义左边Button对应的属性
    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;
    private float leftTextSize;
    //定义右边Button对应的属性
    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;
    private float rightTextSize;
    //定义要显示标题文字的属性
    private float titleTextSize;
    private int titleTextColor;
    private String title;
    //定义布局方式
    private LayoutParams leftParams,rightParams,titleParams;
    //接口回调机制
    private topbarClickListener listener;
    public interface topbarClickListener{
        public void leftClick();
        public void rightClick();
    }
    public void setOnTopbarClickListener(topbarClickListener listener){
        this.listener = listener;
    }
    /**
     * 说明：@SuppressLint("NewApi"）屏蔽一切新api中才能使用的方法报的android lint错误
     *      @TargetApi() 只屏蔽某一新api中才能使用的方法报的android lint错误
     * @param context
     * @param attrs
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //步骤一：获取属性
        //获取自定义属性值的映射,存储在TypeArray中
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.Topbar);
        leftTextColor = typedArray.getColor(R.styleable.Topbar_leftTextColor, 0);
        leftBackground = typedArray.getDrawable(R.styleable.Topbar_leftBackground);
        leftText = typedArray.getString(R.styleable.Topbar_leftText);
        leftTextSize = typedArray.getDimension(R.styleable.Topbar_leftTextSize, 0);
        rightTextColor = typedArray.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = typedArray.getDrawable(R.styleable.Topbar_rightBackground);
        rightText = typedArray.getString(R.styleable.Topbar_rightText);
        rightTextSize = typedArray.getDimension(R.styleable.Topbar_rightTextSize, 0);
        titleTextColor = typedArray.getColor(R.styleable.Topbar_mytitleTextColor, 0);
        titleTextSize = typedArray.getDimension(R.styleable.Topbar_mytitleTextSize, 0);
        title = typedArray.getString(R.styleable.Topbar_mytitle);
        //TypeArray使用完后要记得回收，避免浪费资源和缓存可能出现的错误
        typedArray.recycle();
        //步骤二：获取控件（自定义View中的组合模式，让已有的控件与新的控件进行组合）
        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);
        //步骤三：设置控件属性
        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);
        leftButton.setTextSize(leftTextSize);
        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);
        rightButton.setTextSize(rightTextSize);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        //为了美观，另外设置文字的居中显示
        tvTitle.setGravity(Gravity.CENTER);
        //同时设置GroupView的背景色
        setBackgroundColor(0xFFF59563);
        //步骤四：添加控件到ViewGroup中
        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE); //这里的TRUE不是布尔值，而是常量
        addView(leftButton, leftParams); //添加设置好的控件
        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE); //这里的TRUE不是布尔值，而是常量
        addView(rightButton, rightParams);
        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(tvTitle,titleParams);
        //步骤五：实现自定义接口回调机制
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }
    /**
     * 功能：设置是否显示左按钮
     * @param flag
     */
    public void setLeftButtonIsVisiable(boolean flag){
        if (flag){
            leftButton.setVisibility(View.VISIBLE); //显示左按钮
        }else {
            leftButton.setVisibility(View.GONE); //隐藏左按钮
        }
    }
}