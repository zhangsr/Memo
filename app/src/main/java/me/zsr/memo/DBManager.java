package me.zsr.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @description:
 * @author: Match
 * @date: 10/17/16
 */

public class DBManager {
    private static final String DB_NAME = "memo";
    private static DaoSession sDaoSession;

    public static void init(Context context) {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        sDaoSession = daoMaster.newSession();
    }

    public static MemoDao getMemoDao() {
        return sDaoSession.getMemoDao();
    }
}
