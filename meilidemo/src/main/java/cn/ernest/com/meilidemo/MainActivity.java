package cn.ernest.com.meilidemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ernest.com.meilidemo.bean.MeiShowBean;
import cn.ernest.com.meilidemo.dao.ComPresenter;
import cn.ernest.com.meilidemo.vertical.LoopVerticalPagerAdapter;
import cn.ernest.com.meilidemo.vertical.RollVerticalPagerView;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    RollVerticalPagerView viewPager;
    @BindView(R.id.back_btn)
    ImageView backBtn;

    public RequestManager mImageLoader;
    private ArrayList<MeiShowBean.DataBean> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_daily_list);
        ButterKnife.bind(this);
        getDatas();

    }

    private void getDatas() {
        ComPresenter.getMeiShow().subscribe(new Action1<MeiShowBean>() {
            @Override
            public void call(MeiShowBean meiShowBean) {
                if (meiShowBean.success) {
                    processData(meiShowBean.data);
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
            }
        });
    }

    private void processData(List<MeiShowBean.DataBean> data) {
        mDatas = new ArrayList<>();
        mDatas.addAll(data);
        final ContentFragmentAdapter adapter = new ContentFragmentAdapter(viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setHintView(null);

        viewPager.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ViewInner currentView = ((ViewInner) adapter.findViewByPosition(position));
                currentView.animateDesc();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //If you setting other scroll mode, the scrolled fade is shown from either side of display.
//        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }


    public class ContentFragmentAdapter extends LoopVerticalPagerAdapter {
        public ContentFragmentAdapter(RollVerticalPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ViewInner inner = new ViewInner(MainActivity.this);
            inner.refreshData(mDatas.get(position));
            return inner;
        }

        @Override
        public int getRealCount() {

            return mDatas.size();
        }

    }


    public synchronized RequestManager getImageLoader() {
        if (mImageLoader == null)
            mImageLoader = Glide.with(this);
        return mImageLoader;
    }
}
