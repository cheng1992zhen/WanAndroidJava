package com.example.wanandroidjava.module.wxarticle.presenter;

import com.example.wanandroidjava.event.ArticleDeleteEvent;
import com.example.wanandroidjava.event.CollectionEvent;
import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.main.model.ArticleBean;
import com.example.wanandroidjava.module.main.model.MainRequest;
import com.example.wanandroidjava.module.main.model.UserPageBean;
import com.example.wanandroidjava.module.mine.model.MineRequest;
import com.example.wanandroidjava.widget.CollectView;
import com.example.wanandroidjava.module.wxarticle.view.MineShareView;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.basic.ui.toast.ToastMaker;
import per.goweii.rxhttp.request.base.BaseBean;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/5/17
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class MineSharePresenter extends BasePresenter<MineShareView> {

    public void getMineShareArticleList(int page, boolean refresh) {
        MineRequest.getMineShareArticleList(getRxLife(), refresh, page, new RequestListener<UserPageBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, UserPageBean data) {
                if (isAttach()) {
                    getBaseView().getMineShareArticleListSuccess(code, data.getShareArticles());
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getMineShareArticleListFailed(code, msg);
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public void deleteMineShareArticle(ArticleBean item) {
        addToRxLife(MineRequest.deleteMineShareArticle(item.getId(), new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                ArticleDeleteEvent.postWithArticleId(item.getId());
            }

            @Override
            public void onFailed(int code, String msg) {
                ToastMaker.showShort(msg);
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        }));
    }

    public void collect(ArticleBean item, final CollectView v) {
        addToRxLife(MainRequest.collect(item.getId(), new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                item.setCollect(true);
                if (!v.isChecked()) {
                    v.toggle();
                }
                CollectionEvent.postCollectWithArticleId(item.getId());
            }

            @Override
            public void onFailed(int code, String msg) {
                if (v.isChecked()) {
                    v.toggle();
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        }));
    }

    public void uncollect(ArticleBean item, final CollectView v) {
        addToRxLife(MainRequest.uncollect(item.getId(), item.getOriginId(), new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                if (v.isChecked()) {
                    v.toggle();
                }
                CollectionEvent.postUncollect(item.getOriginId(), item.getId());
            }

            @Override
            public void onFailed(int code, String msg) {
                if (!v.isChecked()) {
                    v.toggle();
                }
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        }));
    }

}
