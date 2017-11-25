package me.zsr.memo;

import android.app.Application;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;

/**
 * @description:
 * @author: Match
 * @date: 22/11/2017
 */

public class App extends Application {
    public static final int REQUEST_CODE_EDIT = 1;

    public static final int RESULT_CODE_NOTHING = 0;
    public static final int RESULT_CODE_MODIFIED = 1;

    public static final String BUNDLE_KEY_MEMO_ID = "memo_id";

    @Override
    public void onCreate() {
        super.onCreate();

        DBManager.init(this);
        AVOSCloud.initialize(this,"AnPt6gKYtOG6hwhyrNvh1v79-gzGzoHsz","3LYlfVKkXHQJ08h4J5K0yKCd");
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);
        AVAnalytics.enableCrashReport(this, true);
    }
}

