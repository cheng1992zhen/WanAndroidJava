package com.example.wanandroidjava.module.knowledge.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wanandroidjava.R;
import com.example.wanandroidjava.common.ScrollTop;
import com.example.wanandroidjava.module.knowledge.activity.KnowledgeArticleActivity;
import com.example.wanandroidjava.module.knowledge.adapter.KnowledgeAdapter;
import com.example.wanandroidjava.module.knowledge.presenter.KnowledgePresenter;
import com.example.wanandroidjava.module.knowledge.view.KnowledgeView;
import com.example.wanandroidjava.module.main.model.ChapterBean;
import com.example.wanandroidjava.utils.MultiStateUtils;
import com.example.wanandroidjava.utils.RvScrollTopUtils;
import com.kennyc.view.MultiStateView;

import java.util.List;

import butterknife.BindView;
import per.goweii.basic.core.base.BaseFragment;
import per.goweii.basic.ui.toast.ToastMaker;
import per.goweii.basic.utils.listener.SimpleListener;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class KnowledgeFragment extends BaseFragment<KnowledgePresenter> implements ScrollTop, KnowledgeView {

    @BindView(R.id.msv)
    MultiStateView msv;
    @BindView(R.id.rv)
    RecyclerView rv;

    private KnowledgeAdapter mAdapter;

    public static KnowledgeFragment create() {
        return new KnowledgeFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_knowledge_navigation_child;
    }

    @Nullable
    @Override
    protected KnowledgePresenter initPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new KnowledgeAdapter();
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnItemClickListener(new KnowledgeAdapter.OnItemClickListener() {
            @Override
            public void onClick(ChapterBean bean, int pos) {
                KnowledgeArticleActivity.start(getContext(), bean, pos);
            }
        });
        rv.setAdapter(mAdapter);
        MultiStateUtils.setEmptyAndErrorClick(msv, new SimpleListener() {
            @Override
            public void onResult() {
                MultiStateUtils.toLoading(msv);
                presenter.getKnowledgeList();
            }
        });
    }

    @Override
    protected void loadData() {
        MultiStateUtils.toLoading(msv);
        presenter.getKnowledgeListCache();
    }

    @Override
    public void onVisible(boolean isFirstVisible) {
        super.onVisible(isFirstVisible);
        if (isFirstVisible) {
            presenter.getKnowledgeList();
        }
    }

    @Override
    public void scrollTop() {
        if (isAdded() && !isDetached()) {
            RvScrollTopUtils.smoothScrollTop(rv);
        }
    }

    @Override
    public void getKnowledgeListSuccess(int code, List<ChapterBean> data) {
        mAdapter.setNewData(data);
        if (data == null || data.isEmpty()) {
            MultiStateUtils.toEmpty(msv);
        } else {
            MultiStateUtils.toContent(msv);
        }
    }

    @Override
    public void getKnowledgeListFail(int code, String msg) {
        ToastMaker.showShort(msg);
        MultiStateUtils.toError(msv);
    }
}
