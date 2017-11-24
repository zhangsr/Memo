package me.zsr.memo;

import android.app.Application;

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
    }
}

