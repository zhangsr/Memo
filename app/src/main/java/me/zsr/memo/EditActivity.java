package me.zsr.memo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.avos.avoscloud.AVAnalytics;

import java.util.List;

public class EditActivity extends Activity {
    private Toolbar mToolbar;
    private EditText mEditText;
    private Memo mMemo;
    private String mOriginContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Long id = bundle.getLong(App.BUNDLE_KEY_MEMO_ID);
            mMemo = findMemoById(id);
            if (mMemo != null) {
                mOriginContent = mMemo.getContent();
                mEditText.setText(mOriginContent);
                mEditText.setSelection(mOriginContent.length());
            }
        }

    }

    private void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_edit);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_keyboard_backspace_white_24dp));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, getCurrentContent());
                        sendIntent.putExtra(Intent.EXTRA_TITLE, "Memo");
                        sendIntent.putExtra("source", App.PACKAGE_NAME);
                        sendIntent.setType("text/plain");
                        startActivity(sendIntent);
                        break;
                }
                return false;
            }
        });

        mEditText = findViewById(R.id.edit_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    private Memo findMemoById(long id) {
        List<Memo> list = DBManager.getMemoDao().queryBuilder().where(MemoDao.Properties.Id.eq(id)).list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        if (mMemo == null) {
            if (StringUtil.isEmpty(getCurrentContent())) {
                setResult(App.RESULT_CODE_NOTHING);
            } else {
                long time = System.currentTimeMillis();
                Memo memo = new Memo();
                memo.setContent(getCurrentContent());
                memo.setCreateTime(time);
                memo.setModifyTime(time);
                DBManager.getMemoDao().insert(memo);
                setResult(App.RESULT_CODE_MODIFIED);
            }
        } else {
            if (StringUtil.isEmpty(getCurrentContent())) {
                DBManager.getMemoDao().delete(mMemo);
                setResult(App.RESULT_CODE_MODIFIED);
            } else if (StringUtil.equals(mOriginContent, getCurrentContent())){
                setResult(App.RESULT_CODE_NOTHING);
            } else {
                long time = System.currentTimeMillis();
                mMemo.setContent(getCurrentContent());
                mMemo.setModifyTime(time);
                DBManager.getMemoDao().update(mMemo);
                setResult(App.RESULT_CODE_MODIFIED);
            }
        }
        super.onBackPressed();
    }

    private String getCurrentContent() {
        return mEditText.getText().toString();
    }
}
