package com.example.wanandroidjava.module.main.activity;


import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;

import com.example.wanandroidjava.R;
import com.example.wanandroidjava.module.main.dialog.CopiedLinkDialog;
import com.example.wanandroidjava.module.main.dialog.DownloadDialog;
import com.example.wanandroidjava.module.main.fragment.MainFragment;
import com.example.wanandroidjava.module.main.fragment.UserArticleFragment;
import com.example.wanandroidjava.module.main.model.UpdateBean;
import com.example.wanandroidjava.module.main.presenter.MainPresenter;
import com.example.wanandroidjava.module.main.view.MainView;
import com.example.wanandroidjava.utils.UpdateUtils;

import butterknife.BindView;
import per.goweii.anypermission.RequestListener;
import per.goweii.anypermission.RuntimeRequester;
import per.goweii.basic.core.adapter.FixedFragmentPagerAdapter;
import per.goweii.basic.core.base.BaseActivity;
import per.goweii.basic.core.permission.PermissionUtils;
import per.goweii.basic.ui.dialog.UpdateDialog;
import per.goweii.basic.utils.LogUtils;
import per.goweii.basic.utils.SPUtils;
import per.goweii.basic.utils.listener.SimpleListener;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, ViewPager.OnPageChangeListener {
    private static final int REQ_CODE_PERMISSION = 1;

    @BindView(R.id.vp)
    ViewPager vp;

    private FixedFragmentPagerAdapter mPagerAdapter;
    private RuntimeRequester mRuntimeRequester;
    private UpdateUtils mUpdateUtils;
    private String mLastCopyLink = "";
    private CopiedLinkDialog mCopiedLinkDialog = null;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        vp.postDelayed(new Runnable() {
            @Override
            public void run() {
                isNeedOpenLink();
            }
        }, 500L);
    }

    @Override
    public boolean swipeBackEnable() {
        return false;
    }

    @Override
    protected void initWindow() {
        super.initWindow();
        setTheme(R.style.AppTheme);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void updateSuccess(int code, UpdateBean data) {
        mUpdateUtils = UpdateUtils.newInstance();
        if (!mUpdateUtils.shouldUpdate(data.getVersion_code())) {
            return;
        }
        UpdateDialog.with(getContext())
                .setUrl(data.getUrl())
                .setUrlBackup(data.getUrl_backup())
                .setVersionCode(data.getVersion_code())
                .setVersionName(data.getVersion_name())
                .setForce(data.isForce())
                .setDescription(data.getDesc())
                .setTime(data.getTime())
                .setOnUpdateListener(new UpdateDialog.OnUpdateListener() {
                    @Override
                    public void onDownload(String url, String urlBackup, boolean isForce) {
                        download(data.getVersion_name(), url, urlBackup, isForce);
                    }

                    @Override
                    public void onIgnore(int versionCode) {
                        mUpdateUtils.ignore(versionCode);
                    }
                })
                .show();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        vp.addOnPageChangeListener(this);
        vp.setOffscreenPageLimit(1);
        mPagerAdapter = new FixedFragmentPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(mPagerAdapter);
        mPagerAdapter.setFragmentList(
                UserArticleFragment.create(),
                MainFragment.create()
        );
        vp.setCurrentItem(1);
        onPageSelected(vp.getCurrentItem());
        mLastCopyLink = SPUtils.getInstance().get("LastCopyLink", "");
    }

    @Override
    protected void loadData() {
        presenter.update();
    }

    public void openUserArticle() {
        vp.setCurrentItem(0);
    }

    private void isNeedOpenLink() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboardManager.getPrimaryClip();
        if (clip == null || clip.getItemCount() <= 0) {
            return;
        }
        for (int i = 0; i < clip.getItemCount(); i++) {
            ClipData.Item item = clip.getItemAt(i);
            LogUtils.i("WanApp", "" + item.toString());
        }
        ClipData.Item item = clip.getItemAt(0);
        if (TextUtils.isEmpty(item.getText())) {
            return;
        }
        String text = item.getText().toString();
        if (TextUtils.equals(mLastCopyLink, text)) {
            return;
        }
        Uri uri = Uri.parse(text);
        if (!TextUtils.equals(uri.getScheme(), "http") && !TextUtils.equals(uri.getScheme(), "https")) {
            return;
        }
        if (mCopiedLinkDialog == null) {
            mCopiedLinkDialog = new CopiedLinkDialog(vp, text, new SimpleListener() {
                @Override
                public void onResult() {
                    mLastCopyLink = text;
                    SPUtils.getInstance().save("LastCopyLink", mLastCopyLink);
                }
            });
        }
        if (!mCopiedLinkDialog.isShow()) {
            mCopiedLinkDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mRuntimeRequester != null) {
            mRuntimeRequester.onActivityResult(requestCode);
        }
    }

    private void download(final String versionName, final String url, final String urlBackup, final boolean isForce) {
        mRuntimeRequester = PermissionUtils.request(new RequestListener() {
            @Override
            public void onSuccess() {
                DownloadDialog.with(getActivity(), isForce, url, urlBackup, versionName);
            }

            @Override
            public void onFailed() {
            }
        }, getContext(), REQ_CODE_PERMISSION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }
}
