package com.example.wanandroidjava.module.wxarticle.presenter;

import com.example.wanandroidjava.event.CollectionEvent;
import com.example.wanandroidjava.http.RequestListener;
import com.example.wanandroidjava.module.main.model.ArticleBean;
import com.example.wanandroidjava.module.main.model.ArticleListBean;
import com.example.wanandroidjava.module.main.model.MainRequest;
import com.example.wanandroidjava.widget.CollectView;
import com.example.wanandroidjava.module.wxarticle.model.WxRequest;
import com.example.wanandroidjava.module.wxarticle.view.WxArticleView;

import per.goweii.basic.core.base.BasePresenter;
import per.goweii.rxhttp.request.base.BaseBean;
import per.goweii.rxhttp.request.exception.ExceptionHandle;


/**
 * @author CuiZhen
 * @date 2019/5/12
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class WxArticlePresenter extends BasePresenter<WxArticleView> {

    public void getWxArticleListCache(int id, int page) {
        WxRequest.getWxArticleListCache(id, page, new RequestListener<ArticleListBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, ArticleListBean data) {
                if (isAttach()) {
                    getBaseView().getWxArticleListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
            }

            @Override
            public void onError(ExceptionHandle handle) {
            }

            @Override
            public void onFinish() {
            }
        });
    }

    public void getWxArticleList(int id, int page, boolean refresh) {
        WxRequest.getWxArticleList(getRxLife(), refresh, id, page, new RequestListener<ArticleListBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, ArticleListBean data) {
                if (isAttach()) {
                    getBaseView().getWxArticleListSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getWxArticleListFailed(code, msg);
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

    public void getWxArticleListSearch(int id, int page, String key, boolean refresh){
        WxRequest.getWxArticleList(getRxLife(), refresh, id, page, key, new RequestListener<ArticleListBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, ArticleListBean data) {
                if (isAttach()) {
                    getBaseView().getWxArticleListSearchSuccess(code, data);
                }
            }

            @Override
            public void onFailed(int code, String msg) {
                if (isAttach()) {
                    getBaseView().getWxArticleListSearchFailed(code, msg);
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
        addToRxLife(MainRequest.uncollect(item.getId(), new RequestListener<BaseBean>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(int code, BaseBean data) {
                item.setCollect(false);
                if (v.isChecked()) {
                    v.toggle();
                }
                CollectionEvent.postUnCollectWithArticleId(item.getId());
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
