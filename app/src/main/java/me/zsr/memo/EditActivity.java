package me.zsr.memo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class EditActivity extends AppCompatActivity {
    private EditText mEditText;
    private Memo mMemo;
    private String mOriginContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mEditText = (EditText) findViewById(R.id.edit_text);

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
