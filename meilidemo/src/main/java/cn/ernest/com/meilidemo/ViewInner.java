package cn.ernest.com.meilidemo;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.hanks.htextview.fade.FadeTextView;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ernest.com.meilidemo.bean.MeiShowBean;
import cn.ernest.com.meilidemo.util.ImageUtil;

/**
 * Created by : youngkaaa on 2017/2/22.
 * Contact me : 645326280@qq.com
 */

public class ViewInner extends FrameLayout {
    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_IMG = "KEY_IMG";
    @BindView(R.id.daily_item_bg)
    ImageView dailyItemBg;
    @BindView(R.id.self_date)
    TextView selfDate;
    @BindView(R.id.week)
    TextView week;
    @BindView(R.id.daily_dailog_msg_layout)
    RelativeLayout dailyDailogMsgLayout;
    @BindView(R.id.msg)
    FadeTextView msg;
    @BindView(R.id.self_info)
    TextView selfInfo;
    @BindView(R.id.daily_item_content_layout)
    RelativeLayout dailyItemContentLayout;
    @BindView(R.id.point)
    View point;
    @BindView(R.id.like)
    ImageView like;
    @BindView(R.id.comment)
    ImageView comment;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.toolbarpanel)
    LinearLayout toolbarpanel;

    //    public MeiShowBean.DataBean mData = new MeiShowBean.DataBean();
    public MeiShowBean.DataBean mData;
    //    private MainActivity mainActivity;
    public RequestManager mImageLoader;

    private Context mContext;

    public ViewInner(@NonNull Context context) {
        super(context);
        init(context, null, 0);

    }

    public ViewInner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public ViewInner(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        View mRootView = View.inflate(context, R.layout.home_daily_list_item, null);
        ButterKnife.bind(this, mRootView);
        addView(mRootView);
    }


    public void refreshData(MeiShowBean.DataBean dataBean) {

        Logger.e("dataBean:" + dataBean.toString());
        mData = dataBean;
        if (dailyItemBg != null) {
            ImageUtil.load(getImageLoader(), dataBean.img_o, dailyItemBg);
            selfDate.setText(dataBean.date);
            week.setText(dataBean.week);
            selfInfo.setText(dataBean.label);
//            msg.animateText(dataBean.description);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        msg.animateText(mData.description);

    }

    public synchronized RequestManager getImageLoader() {
        if (mImageLoader == null)
            mImageLoader = Glide.with(mContext);
        return mImageLoader;
    }
}
