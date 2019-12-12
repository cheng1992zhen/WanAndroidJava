package com.example.wanandroidjava.project.fragment;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.example.wanandroidjava.R;
import com.example.wanandroidjava.common.Config;
import com.example.wanandroidjava.common.ScrollTop;
import com.example.wanandroidjava.event.ScrollTopEvent;
import com.example.wanandroidjava.module.main.model.ChapterBean;
import com.example.wanandroidjava.project.presenter.ProjectPresenter;
import com.example.wanandroidjava.project.view.ProjectView;
import com.example.wanandroidjava.utils.MagicIndicatorUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.List;

import butterknife.BindView;
import per.goweii.actionbarex.ActionBarEx;
import per.goweii.basic.core.adapter.MultiFragmentPagerAdapter;
import per.goweii.basic.core.base.BaseFragment;
import per.goweii.basic.ui.toast.ToastMaker;
import per.goweii.basic.utils.listener.SimpleCallback;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class ProjectFragment extends BaseFragment<ProjectPresenter> implements ScrollTop, ProjectView {

    @BindView(R.id.ab)
    ActionBarEx ab;
    @BindView(R.id.vp)
    ViewPager vp;

    private MultiFragmentPagerAdapter<ChapterBean, ProjectArticleFragment> mAdapter;
    private CommonNavigator mCommonNavigator;
    private long lastClickTime = 0L;
    private int lastClickPos = 0;

    public static ProjectFragment create() {
        return new ProjectFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_project;
    }

    @Nullable
    @Override
    protected ProjectPresenter initPresenter() {
        return new ProjectPresenter();
    }

    @Override
    protected void initView() {
        mAdapter = new MultiFragmentPagerAdapter<>(
                getChildFragmentManager(),
                new MultiFragmentPagerAdapter.FragmentCreator<ChapterBean, ProjectArticleFragment>() {
                    @Override
                    public ProjectArticleFragment create(ChapterBean data, int pos) {
                        return ProjectArticleFragment.create(data, pos);
                    }

                    @Override
                    public String getTitle(ChapterBean data) {
                        return data.getName();
                    }
                });
        vp.setAdapter(mAdapter);
        mCommonNavigator = MagicIndicatorUtils.commonNavigator((MagicIndicator) ab.getTitleBarChild(), vp, mAdapter, new SimpleCallback<Integer>() {
            @Override
            public void onResult(Integer data) {
                notifyScrollTop(data);
            }
        });
    }

    @Override
    protected void loadData() {
        presenter.getProjectChapters();
    }

    @Override
    public void onVisible(boolean isFirstVisible) {
        super.onVisible(isFirstVisible);
    }

    @Override
    public void scrollTop() {
        if (isAdded() && !isDetached()) {
            new ScrollTopEvent(ProjectArticleFragment.class, vp.getCurrentItem()).post();
        }
    }

    private void notifyScrollTop(int pos) {
        long currClickTime = System.currentTimeMillis();
        if (lastClickPos == pos && currClickTime - lastClickTime <= Config.SCROLL_TOP_DOUBLE_CLICK_DELAY) {
            new ScrollTopEvent(ProjectArticleFragment.class, vp.getCurrentItem()).post();
        }
        lastClickPos = pos;
        lastClickTime = currClickTime;
    }

    @Override
    public void getProjectChaptersSuccess(int code, List<ChapterBean> data) {
        mAdapter.setDataList(data);
        mCommonNavigator.notifyDataSetChanged();
    }

    @Override
    public void getProjectChaptersFailed(int code, String msg) {
        ToastMaker.showShort(msg);
    }
}
