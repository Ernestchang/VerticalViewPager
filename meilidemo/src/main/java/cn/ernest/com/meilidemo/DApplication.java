package cn.ernest.com.meilidemo;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.LogPrintStyle;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Ernest on 2017/4/28.
 */

public class DApplication extends Application implements Thread.UncaughtExceptionHandler {


    private static DApplication instance;

    public static DApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
////
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);

        instance = this;

        Logger.initialize(
                new Settings()
                        .setStyle(new LogPrintStyle())
                        .isShowMethodLink(true)
                        .isShowThreadInfo(true)
                        .setMethodOffset(3)
                        .setLogPriority(BuildConfig.DEBUG ? Log.VERBOSE : Log.ASSERT)
        );

        Thread.setDefaultUncaughtExceptionHandler(this);
//        registerActivityLifecycleCallbacks(new LifeCircleHandler());


//        String crashInfo = DataManager.getCrashInfo();
//        if (!TextUtils.isEmpty(crashInfo)) {
//            Logger.e("errorString:" + crashInfo);
////            MobclickAgent.reportError(this, crashInfo);
//        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ByteArrayOutputStream baos = null;
        PrintStream pw = null;
        String errorString = null;
        try {
            baos = new ByteArrayOutputStream();
            pw = new PrintStream(baos);
            ex.printStackTrace(pw);
            errorString = new String(baos.toByteArray());
            Logger.e("errorString:" + errorString);
//            DataManager.setCrashInfo(errorString);
//            TipUtils.showTip(JApplication.getInstance(), R.string.tips_quit);

//            exitApplication();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                    pw = null;
                }
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
