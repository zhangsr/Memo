package me.zsr.memo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {
    private static final String ID_WECHAT = "matchzsr";
    private static final String ID_ALIPAY = "275690559@qq.com";
//    private static final String URL_STORE = "http://fir.im/memox";
    private static final String URL_STORE = "https://www.coolapk.com/apk/168929";
    @Bind(R.id.back_btn)
    ImageButton mBackButton;
    @Bind(R.id.title_txt)
    TextView mTitleTextView;
    @Bind(R.id.version_name_txt)
    TextView mVersionNameTextView;
    @Bind(R.id.info_img)
    ImageView mInfoImageView;
    @Bind(R.id.author_img)
    ImageView mAuthorImageView;
    @Bind(R.id.bug_img)
    ImageView mBugImageView;
    @Bind(R.id.store_img)
    ImageView mStoreImageView;
    @Bind(R.id.wechat_img)
    ImageView mWechatImageView;
    @Bind(R.id.share_img)
    ImageView mShareImageView;
    @Bind(R.id.donate_img)
    ImageView mDonateImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        mTitleTextView.setText(getTitle());
        mVersionNameTextView.setText(BuildConfig.VERSION_NAME);
        mInfoImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
        mAuthorImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
        mBugImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
        mStoreImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
        mWechatImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
        mShareImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
        mDonateImageView.setColorFilter(getResources().getColor(R.color.main_grey_normal));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openUrl(String url) {
        IntentUtil.openUrl(this, url);
    }

    @OnClick({
            R.id.back_btn,
            R.id.wechat_layout,
            R.id.bug_layout,
            R.id.store_layout,
            R.id.share_layout,
            R.id.donate_layout,
    })
    public void layoutOnClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.wechat_layout: {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Wechat ID", ID_WECHAT);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.bug_layout:
                openUrl(URL_STORE);
                break;
            case R.id.store_layout:
                openUrl(URL_STORE);
                break;
            case R.id.share_layout:
                openUrl(URL_STORE);
                break;
            case R.id.donate_layout: {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("AliPay ID", ID_ALIPAY);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, R.string.alipay_account_copied, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
