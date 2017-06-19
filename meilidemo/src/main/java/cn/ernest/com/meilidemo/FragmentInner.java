package cn.ernest.com.meilidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class FragmentInner extends Fragment {
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

    public View mRootView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("onCreate:");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.home_daily_list_item, container, false);
        ButterKnife.bind(this, mRootView);
        Logger.e("onCreateView:");

        return mRootView;
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
    public void onResume() {
        super.onResume();
        msg.animateText(mData.description);

    }

    public synchronized RequestManager getImageLoader() {
        if (mImageLoader == null)
            mImageLoader = Glide.with(getActivity());
        return mImageLoader;
    }
}
